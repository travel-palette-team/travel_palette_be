# 1. 빌드 단계
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# 권한 부여 후 빌드 진행
RUN chmod +x ./gradlew && ./gradlew build -x test

# 2. 실행 단계
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/build/libs/*-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dserver.port=8080", "app.jar"]