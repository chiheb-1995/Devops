FROM openjdk:8-jdk-alpine
MAINTAINER baeldung.com
COPY target/timesheet-devops-1.0.jar timesheet-devops-1.0.jar
ENTRYPOINT ["java","-jar","/timesheet-devops-1.0.jar"]
