FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
EXPOSE 5678
ADD /target/discovery-0.0.1-SNAPSHOT.jar discovery.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/discovery.jar"]