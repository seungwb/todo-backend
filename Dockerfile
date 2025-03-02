# 1. OpenJDK 17을 기반 이미지로 사용
FROM openjdk:17

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. Gradle 빌드된 JAR 파일 복사 (정확한 파일명 확인 필수!)
COPY build/libs/web-0.0.1-SNAPSHOT.jar app.jar

# 4. JAR 파일 실행
CMD ["java", "-jar", "app.jar"]