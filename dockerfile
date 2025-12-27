# Official maintained Java image
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY target_dev/libs/AnnualReportMgmt-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
