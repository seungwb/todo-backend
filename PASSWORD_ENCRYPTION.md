# 비밀번호 암호화 구현 설계 문서

## 현황 분석

현재 코드베이스를 확인한 결과, **비밀번호 암호화는 이미 구현되어 있습니다.**

- `AppConfig.java`에서 `BCryptPasswordEncoder` 빈이 등록되어 있음
- `AuthServiceImplement.java`에서 회원가입 시 `passwordEncoder.encode()`, 로그인 시 `passwordEncoder.matches()`를 올바르게 사용 중

---

## 구현 방식

### 사용 기술: BCrypt

BCrypt는 단방향 해시 함수로, 평문 비밀번호를 복호화할 수 없는 암호화된 문자열로 변환합니다.

**선택 이유:**
- 같은 평문이라도 매번 다른 해시값 생성 (salt 내장)
- Rainbow table 공격 방어
- Spring Security에서 공식 지원
- work factor 조절로 연산 비용 설정 가능 (기본값: 10)

---

## 현재 구현 코드 분석

### 1. BCryptPasswordEncoder 빈 등록

**파일:** `src/main/java/kr/co/tododeungjang/web/config/AppConfig.java`

```java
@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

`PasswordEncoder` 인터페이스 타입으로 빈을 등록하여, 추후 다른 암호화 방식으로 교체할 때 `AppConfig`만 수정하면 됩니다.

---

### 2. 회원가입 시 비밀번호 암호화

**파일:** `src/main/java/kr/co/tododeungjang/web/service/implement/AuthServiceImplement.java`

```java
// 회원가입 (signUp 메서드)
String password = dto.getPassword();
String encodedPassword = passwordEncoder.encode(password);  // 평문 → BCrypt 해시

MemberEntity memberEntity = MemberEntity.builder()
        .email(email)
        .phone(phone)
        .name(name)
        .password(encodedPassword)  // 암호화된 값만 DB에 저장
        .joinDate(joinDate)
        .build();

memberRepository.save(memberEntity);
```

`passwordEncoder.encode(password)`는 호출할 때마다 내부적으로 랜덤 salt를 생성하므로 동일한 비밀번호라도 저장값이 달라집니다.

---

### 3. 로그인 시 비밀번호 검증

```java
// 로그인 (signIn 메서드)
String password = dto.getPassword();              // 사용자가 입력한 평문
String encodedPassword = memberEntity.getPassword(); // DB에 저장된 BCrypt 해시

boolean isMatched = passwordEncoder.matches(password, encodedPassword);
if (!isMatched) {
    return SignInResponseDto.signInFailed();
}
```

`passwordEncoder.matches(rawPassword, encodedPassword)`는 평문을 복호화하는 것이 아니라, 입력값을 동일한 방식으로 해시하여 저장된 해시와 비교합니다.

---

## 전체 인증 흐름

```
[회원가입]
사용자 입력 (평문 PW)
    ↓
BCryptPasswordEncoder.encode()
    ↓
$2a$10$... 형태의 BCrypt 해시 생성
    ↓
DB에 암호화된 값 저장

[로그인]
사용자 입력 (평문 PW)
    ↓
DB에서 이메일로 회원 조회
    ↓
BCryptPasswordEncoder.matches(입력값, DB 해시값)
    ↓
일치 → JWT 토큰 발급
불일치 → signInFailed 응답
```

---

## DB 저장 형태

BCrypt로 암호화된 비밀번호는 아래와 같은 형태로 저장됩니다:

```
$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy
```

- `$2a$` : BCrypt 알고리즘 버전
- `10` : work factor (해시 반복 횟수, 2^10 = 1024회)
- 나머지 : salt(22자) + 해시값

---

## 추가 개선 고려 사항

현재 구현은 기본적인 보안 요건을 충족하지만, 아래 항목들을 추가적으로 고려할 수 있습니다.

### 1. 비밀번호 정책 강화

현재 `SignUpRequestDto`의 비밀번호 검증 조건이 8~20자 길이만 체크하고 있다면, 아래와 같은 조건 추가를 검토할 수 있습니다:

```java
// 예시: 영문 + 숫자 + 특수문자 조합 필수
@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,20}$",
         message = "비밀번호는 영문, 숫자, 특수문자를 포함한 8~20자여야 합니다.")
private String password;
```

### 2. BCrypt work factor 조정

서버 성능에 따라 work factor를 높여 보안 강도를 높일 수 있습니다 (값이 높을수록 연산 시간 증가):

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12); // 기본 10 → 12로 상향
}
```

### 3. 비밀번호 변경 기능

현재 비밀번호 변경 API가 없다면, 아래 흐름으로 추가를 고려할 수 있습니다:
1. 현재 비밀번호 확인 (`passwordEncoder.matches()`)
2. 새 비밀번호 암호화 후 저장 (`passwordEncoder.encode()`)

---

## 관련 파일 목록

| 파일 | 역할 |
|------|------|
| `config/AppConfig.java` | BCryptPasswordEncoder 빈 등록 |
| `config/WebSecurityConfig.java` | Spring Security 설정 (공개/인증 필요 경로 분리) |
| `service/implement/AuthServiceImplement.java` | 회원가입·로그인 비즈니스 로직 |
| `domain/dto/request/auth/SignUpRequestDto.java` | 회원가입 입력값 및 유효성 검증 |
| `domain/entity/MemberEntity.java` | 회원 엔티티 (password 필드 포함) |
