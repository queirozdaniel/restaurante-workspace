FROM openjdk:11
VOLUME /tmp
ADD ./restaurante-oauth/target/restaurante-oauth-0.0.1-SNAPSHOT.jar restaurante-oauth.jar
ENTRYPOINT ["java","-jar","/restaurante-oauth.jar"]
