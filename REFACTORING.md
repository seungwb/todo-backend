# 백엔드 리팩토링 내역

## 1. API URL v2 버전 업그레이드
- **변경 파일**: `AuthController.java`, `TodoController.java`, `ScheduleController.java`, `MailController.java`
- **변경 내용**: 모든 `@RequestMapping` 경로에 `/v2` 추가
  - `/api/auth/**` → `/api/v2/auth/**`
  - `/api/todo/**` → `/api/v2/todo/**`
  - `/api/schedule/**` → `/api/v2/schedule/**`
  - `/api/mail/**` → `/api/v2/mail/**`

---

## 2. [Critical] 메일 인증번호 static 필드 → ConcurrentHashMap 변경
- **변경 파일**: `MailServiceImplement.java`, `VerifiedNumberRequestDto.java`
- **문제**: `private static String verificationNumber` 필드는 다중 사용자 환경에서 동시 요청 시 서로의 인증번호를 덮어쓰는 심각한 보안 버그
- **수정**: `ConcurrentHashMap<String, String> verificationMap` (email → 인증번호) 으로 교체
- **추가**: `VerifiedNumberRequestDto`에 `email` 필드 추가 (인증 확인 시 이메일로 조회)
- **추가**: `java.util.Random` → `java.security.SecureRandom` 교체 (OTP 보안 강화)
- **추가**: 인증 성공 후 `verificationMap.remove(email)` 호출하여 일회성 인증 보장

---

## 3. [Critical] 예외 발생 시 응답 미반환 버그 수정
- **변경 파일**: `MailServiceImplement.java`
- **문제**: 예외 catch 블록에서 `ResponseDto.databaseError()`의 반환값을 `return`하지 않아 예외 이후에도 `success()`를 반환
- **수정**: `return ResponseDto.databaseError();` 로 수정

---

## 4. [High] ScheduleServiceImplement NPE 방지
- **변경 파일**: `ScheduleServiceImplement.java`
- **문제**: `getSchedule`, `getTodaySchedule`, `getWeeklySchedule` 메서드에서 member null 체크 없이 `member.getId()` 호출 → NullPointerException
- **수정**: 모든 메서드에 `findMemberByEmail()` 헬퍼 적용 및 null 체크 추가
- **추가**: `getTodaySchedule`의 `today`, `getWeeklySchedule`의 `start`/`end` 파라미터 null 체크 추가

---

## 5. [High] HTTP 상태코드 수정 (401 → 404)
- **변경 파일**: `ResponseDto.java`
- **문제**: `notExistedTodo()`, `notExistedSchedule()` 가 `401 UNAUTHORIZED` 반환 — 리소스 없음은 `404 NOT_FOUND`가 올바름
- **수정**: `ResponseDto.notExistedTodo()` → `HttpStatus.NOT_FOUND`, `ResponseDto.notExistedSchedule()` → `HttpStatus.NOT_FOUND`

---

## 6. [High] Security 설정 — 스케줄 쓰기 엔드포인트 인증 처리
- **변경 파일**: `WebSecurityConfig.java`
- **문제**: `.requestMatchers("/api/schedule/**").permitAll()` 가 DELETE/POST/PUT 포함 모든 스케줄 엔드포인트를 인증 없이 허용
- **수정**: GET만 허용 (`HttpMethod.GET`), 나머지는 인증 필요
- **추가**: v2 경로에 맞게 `/api/v2/auth/**`, `/api/v2/mail/**`, `/api/v2/schedule` GET 만 허용

---

## 7. Response DTO 보일러플레이트 제거 (중복 코드 통합)
- **변경 파일**: `ResponseDto.java`, 모든 `todo/*.java`, `schedule/*.java` Response DTO
- **문제**: `notExistedUser()`, `notExistedTodo()`, `notExistedSchedule()` 메서드가 13개 파일에 동일하게 복붙됨
- **수정**: 공통 팩토리 메서드를 `ResponseDto`에 추가하고 모든 하위 DTO에서 제거
- **수정**: 변수명 오타 `reslut` → `result` 전체 수정

---

## 8. Entity → DTO 레이어 의존성 제거
- **변경 파일**: `TodoEntity.java`, `ScheduleEntity.java`, `TodoServiceImplement.java`, `ScheduleServiceImplement.java`
- **문제**: 엔티티(영속성 레이어)가 요청 DTO(표현 레이어)를 직접 import — 레이어 위반
- **수정**: 엔티티 메서드를 DTO가 아닌 순수 필드값을 받도록 변경
  - `TodoEntity.updateState(UpdateStateTodoRequestDto)` → `updateState(Boolean state)`
  - `TodoEntity.update(UpdateTodoRequestDto)` → `update(String title, String content)`
  - `ScheduleEntity.update(UpdateScheduleRequestDto)` → `update(String title, String content, String location, OffsetDateTime startDate, OffsetDateTime endDate)`

---

## 9. PasswordEncoder Spring Bean 관리
- **추가 파일**: `AppConfig.java`
- **변경 파일**: `AuthServiceImplement.java`
- **문제**: `private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder()` 가 Spring 컨텍스트 밖에서 직접 생성됨
- **수정**: `AppConfig.java`에서 `@Bean` 으로 등록하고 생성자 주입으로 변경

---

## 10. JwtProvider Repository 의존성 제거 + validate 메서드 통합
- **변경 파일**: `JwtProvider.java`, `AuthServiceImplement.java`
- **문제**: JwtProvider(유틸 레이어)가 MemberRepository, MemberRoleRepository에 의존하여 DB 쿼리 수행 — 역할 위반
- **수정**: `create(String email)` → `create(String email, String role)` 로 변경하여 role을 호출자(서비스)가 전달하도록
- **수정**: `validateEmail()`, `validateRole()` 에서 중복된 JWT 파싱 로직을 `private parseClaims()` 로 통합

---

## 11. MailService 인터페이스 생성
- **추가 파일**: `MailService.java` (인터페이스)
- **변경 파일**: `MailController.java`, `MailServiceImplement.java`
- **문제**: `MailController`가 `MailServiceImplement` 구체 클래스를 직접 주입 — DIP 위반
- **수정**: 다른 컨트롤러와 동일하게 인터페이스 의존으로 변경

---

## 12. System.out.println 제거
- **변경 파일**: `AuthServiceImplement.java`
- **문제**: 서비스 레이어에서 사용자 이메일을 `System.out.println`으로 출력 (개인정보 노출 위험)
- **수정**: SLF4J Logger로 교체 (`log.debug("로그인 email={}", email)`)

---

## 13. CORS 중복 설정 통합
- **변경 파일**: `WebSecurityConfig.java`, `CorsConfig.java`
- **문제**: `CorsConfig`(WebMvcConfigurer)와 `WebSecurityConfig`(Spring Security)에 CORS 설정 중복
- **수정**: `WebSecurityConfig`에 `CorsConfigurationSource` Bean으로 단일화, `CorsConfig` deprecated 처리

---

## 14. FailedAuthenticationEntryPoint 응답 코드 통일
- **변경 파일**: `WebSecurityConfig.java`
- **문제**: 하드코딩된 JSON 문자열 `{"code": "AF", ...}` 사용 — `ResponseCode` 체계와 불일치
- **수정**: `ResponseCode.AUTHORIZATION_FAILED`, `ResponseMessage.AUTHORIZATION_FAILED` 상수 추가 후 `ObjectMapper`로 직렬화

---

## 15. @NotBlank → @NotNull 수정
- **변경 파일**: `TodoListItem.java`, `ScheduleListItem.java`
- **문제**: `Long`, `Boolean`, `OffsetDateTime` 타입에 `@NotBlank` 적용 — `CharSequence`에만 유효하여 실제로 검증되지 않음
- **수정**: 해당 필드들을 `@NotNull`로 교체

---

## 16. Response DTO 패키지 이동 (@Deprecated 처리)
- **추가 파일**: `response/schedule/GetTodayScheduleResponseDto.java`, `response/schedule/GetWeeklyScheduleResponseDto.java`
- **변경 파일**: `response/GetTodayScheduleResponseDto.java`, `response/GetWeeklyScheduleResponseDto.java` (→ @Deprecated)
- **변경 파일**: `ScheduleService.java`, `ScheduleServiceImplement.java`, `ScheduleController.java`
- **문제**: 스케줄 관련 DTO 2개가 `response/` 루트에 위치, 나머지는 `response/schedule/` 에 위치하여 불일치
- **수정**: 올바른 패키지에 새 파일 생성, 서비스/인터페이스/컨트롤러 import를 새 패키지로 변경, 기존 파일 @Deprecated 처리

---

## 17. ScheduleServiceImplement 공통 헬퍼 메서드 추출
- **변경 파일**: `ScheduleServiceImplement.java`, `TodoServiceImplement.java`
- **문제**: 모든 서비스 메서드에서 동일한 email null 체크 + 유저 조회 패턴 반복
- **수정**: `findMemberByEmail(String email)` 헬퍼 메서드로 추출
- **추가 수정 (Schedule)**: `toScheduleListItems()` 헬퍼로 반복되는 스트림 변환 로직 추출

---

## 18. ScheduleController 불필요한 import 제거
- **변경 파일**: `ScheduleController.java`
- **문제**: `import org.apache.coyote.Response` (Tomcat 내부 클래스), `import java.time.OffsetDateTime` 미사용 import
- **수정**: 두 import 모두 제거

---

# 신규 기능 구현 (2026-04-01)

## 19. 비밀번호 재설정
이메일 인증 완료 후 새 비밀번호로 변경할 수 있는 기능. 인증 상태를 `VerificationStore` Bean으로 관리하고, `PUT /api/v2/auth/reset-password` 엔드포인트로 비밀번호 업데이트.

## 20. 공지사항
공지사항 CRUD 기능. 목록 조회·상세 조회(조회수 증가)·작성·수정·삭제를 제공하며, 작성·수정·삭제는 로그인한 사용자만 가능. `GET /api/v2/notice/**` 는 비로그인도 허용.

## 21. 마이페이지
회원정보 조회·수정(이름/전화번호), 비밀번호 변경, 회원 탈퇴 기능. 탈퇴 시 연관된 Todo·Schedule·MemberRole을 순서대로 삭제 후 회원 삭제.
