# 1. OpenJDK 17을 기반 이미지 사용
FROM openjdk:17

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 필수 유틸리티 설치 (`xargs` 포함)
RUN apt-get update && apt-get install -y xargs

# 4. 소스 코드 전체 복사
COPY . .

# 5. Gradle 실행 권한 부여
RUN chmod +x gradlew

# 6. Gradle 빌드 실행 (JAR 생성)
RUN ./gradlew bootJar

# 7. JAR 파일 실행
CMD ["java", "-jar", "build/libs/web-0.0.1-SNAPSHOT.jar"]
