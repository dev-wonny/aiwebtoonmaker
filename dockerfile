# 1. Base Image 선택 (OpenJDK 17)
FROM openjdk:17-jdk-slim

# 2. 작업 디렉토리 생성
WORKDIR /app

# 3. Gradle 빌드 후 생성된 JAR 파일을 컨테이너로 복사
COPY build/libs/*.jar app.jar

# 4. 컨테이너에서 실행될 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]
