FROM openjdk:11
VOLUME /tmp
ADD ./restaurante-user/target/restaurante-user-0.0.1-SNAPSHOT.jar restaurante-user.jar
ENTRYPOINT ["java","-jar","/restaurante-user.jar"]