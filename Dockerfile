FROM eclipse-temurin:17
WORKDIR /app
COPY target/api-gateway-1.0.0.jar app.jar
EXPOSE 8762
ENTRYPOINT ["java","-jar","app.jar"]
