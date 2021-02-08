FROM openjdk:11
VOLUME /tmp
ADD ./restaurante-system/target/restaurante-system-0.0.1-SNAPSHOT.jar restaurante-system.jar
ENTRYPOINT ["java","-jar","/restaurante-system.jar"]