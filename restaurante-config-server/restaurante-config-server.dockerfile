FROM openjdk:11
VOLUME /tmp
EXPOSE 8888
ADD ./restaurante-config-server/target/restaurante-config-server-0.0.1-SNAPSHOT.jar restaurante-config-server.jar
ENTRYPOINT ["java","-jar","/restaurante-config-server.jar"]
