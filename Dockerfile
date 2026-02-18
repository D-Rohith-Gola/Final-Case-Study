FROM eclipse-temurin:17
WORKDIR /app
COPY target/settlement-service-1.0.0.jar app.jar
EXPOSE 8765
ENTRYPOINT ["java","-jar","app.jar"]
