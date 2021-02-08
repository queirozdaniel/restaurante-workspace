FROM openjdk:11
VOLUME /tmp
EXPOSE 8765
ADD ./restaurante-api-gateway-zuul/target/restaurante-api-gateway-zuul-0.0.1-SNAPSHOT.jar restaurante-api-gateway-zuul.jar
ENTRYPOINT ["java","-jar","/restaurante-api-gateway-zuul.jar"]