# 투두등장 (backend)

## 프로젝트 개요
- 프로젝트 명: 투두등장 (Backend)
- 설명:
  - 일정 및 할 일 관리 API 제공
  - 사용자 인증 빛 JWT 보안
  - PostgreSQL을 활용한 데이터 저장
- 주요 기능:
  - 일정 관리 API (CRUD)
  - 할 일 관리 API (CRUD)
  - JWT 기반 사용자 인증
  - 예외 처리 및 보안 필터 적용

## 기술 스택
- 개발 환경

![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white)
- 패키지 관리

![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)

- 사용 기술

![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)

## 프로젝트 구조
```bash
backend/
│── src/main/java/kr/co/tododeungjang/web
│   ├── common/          # 공통 응답 코드 및 메시지
│   ├── config/          # 설정 관련 파일 (보안, CORS 등)
│   ├── controller/      # API 컨트롤러
│   ├── domain/          # JPA 엔티티 및 DTO
│   ├── exception/       # 예외 처리 관련 클래스
│   ├── filter/          # JWT 및 인증 필터
│   ├── provider/        # JWT Provider
│   ├── repository/      # 데이터 접근 계층 (JPA Repository)
│   ├── service/         # 비즈니스 로직
│── src/main/resources/
│   ├── application.properties  # 환경 변수 설정 파일
│
│── build.gradle         # Gradle 빌드 파일
│── settings.gradle      # 프로젝트 설정 파일
```

## 주요 기능 설명
- 사용자 인증 (JWT)
  - 회원가입 및 로그인 API 제공
  - JWT 토큰을 이용한 인증 처리
  - `Spring Security` 와 `JWT`를 활용한 인증 및 권한 관리
- 일정 관리 API
  - 일정 추가 (POST /api/schedule)
  - 일정 조회 (GET /api/schedule)
  - 일정 수정 (PUT /api/schedule/{id})
  - 일정 삭제 (DELETE /api/schedule/{id})
  - 오늘의 일정 조회 (GET /api/schedule/today?today={today})
  - 이번주 일정 조회 (GET /api/schedule/weekly?start={start}&end={end})
- 할 일 관리 API
  - 할 일 추가 (POST /api/todo)
  - 할 일 조회 (GET /api/todo)
  - 할 일 수정 (PUT /api/todo/{id})
  - 할 일 상태 수정 (PUT /api/todo/toggle/{id})
  - 할 일 삭제 (DELETE /api/todo/{id})

- [API 명세서 참조](https://seungwb.github.io/%EA%B0%9C%EC%9D%B8-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8/%ED%88%AC%EB%91%90%EB%93%B1%EC%9E%A5/REST-API-%EB%AA%85%EC%84%B8%EC%84%9C) 
