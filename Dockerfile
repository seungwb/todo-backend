# 1. OpenJDK 17을 기반 이미지 사용
FROM openjdk:17

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 소스 코드 전체 복사
COPY . .

# 4. Gradle 빌드 실행 (Render에서 JAR 생성)
RUN ./gradlew bootJar

# 5. JAR 파일 실행
CMD ["java", "-jar", "build/libs/web-0.0.1-SNAPSHOT.jar"]