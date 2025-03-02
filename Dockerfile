# 1. OpenJDK 17을 기반 이미지 사용
FROM openjdk:17

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 소스 코드 전체 복사
COPY . .

# 4. Gradle 실행 권한 부여
RUN chmod +x gradlew || echo "Skipping chmod on Windows"

# 5. Gradle 빌드 실행 (JAR 생성)
RUN ./gradlew bootJar

# 6. JAR 파일 실행
CMD ["java", "-jar", "build/libs/web-0.0.1-SNAPSHOT.jar"]