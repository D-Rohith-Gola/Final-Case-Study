FROM eclipse-temurin:17
WORKDIR /app
COPY target/transaction-server-1.0.0.jar app.jar
EXPOSE 8763
ENTRYPOINT ["java","-jar","app.jar"]
