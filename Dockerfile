# 1. Eclipse Temurin 17 (openjdk:17 공식 후속 이미지)
FROM eclipse-temurin:17-jdk

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 필수 유틸리티 (`xargs` 포함) 설치
RUN apt-get update && apt-get install -y --no-install-recommends findutils && rm -rf /var/lib/apt/lists/*


COPY . .

EXPOSE 4000

# 5. Gradle 실행 권한 부여
RUN chmod +x gradlew

# 6. Gradle 빌드 실행 (JAR 생성)
RUN ./gradlew bootJar

# 7. JAR 파일 실행
CMD ["java", "-jar", "build/libs/web-0.0.1-SNAPSHOT.jar"]

