FROM openjdk:11
VOLUME /tmp
EXPOSE 8761
ADD ./restaurante-eureka-server/target/restaurante-eureka-server-0.0.1-SNAPSHOT.jar restaurante-eureka-server.jar
ENTRYPOINT ["java","-jar","/restaurante-eureka-server.jar"]