FROM maven:3.6-openjdk-15 AS MAVEN_BUILD

MAINTAINER Alexandar Naydenov

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:16-jdk-alpine

WORKDIR /app

EXPOSE 12345

COPY --from=MAVEN_BUILD /build/target/NotesRestApplication-0.0.1-SNAPSHOT.jar /app/NotesRestApp.jar

ENTRYPOINT ["java","-jar","NotesRestApp.jar"]