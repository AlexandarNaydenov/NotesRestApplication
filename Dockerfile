FROM openjdk:16-jdk-alpine
EXPOSE 12345
ADD ./target/NotesRestApplication-0.0.1-SNAPSHOT.jar NotesRestApp.jar
CMD ["java","-jar","NotesRestApp.jar"]