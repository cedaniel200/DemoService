FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace
COPY . .
RUN chmod +x gradlew && ./gradlew bootJar --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app
RUN useradd --system --uid 1001 appuser
COPY --from=build --chown=appuser:appuser /workspace/build/libs/*.jar app.jar
USER appuser
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
