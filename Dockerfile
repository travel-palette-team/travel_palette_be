# 1. 빌드 단계 (Java 21 JDK)
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod +x ./gradlew
RUN ./gradlew build -x test --no-daemon

# 2. 실행 단계 (Java 21 JRE)
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/build/libs/*-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dserver.port=8080", "app.jar"]