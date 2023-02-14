FROM openjdk:11
COPY target/springkafkaproducer-0.0.1-SNAPSHOT.jar producer-service1.jar
EXPOSE 9090
RUN bash -c "touch /producer-service1.jar"
ENTRYPOINT ["java","-jar","/producer-service1.jar"]