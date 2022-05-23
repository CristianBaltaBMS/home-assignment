FROM openjdk:17
COPY ./build/libs/home-assignment-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "home-assignment-0.0.1-SNAPSHOT.jar"]
