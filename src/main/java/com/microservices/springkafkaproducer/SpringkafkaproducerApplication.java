package com.microservices.springkafkaproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
@EnableKafka
@EnableConfigurationProperties
public class SpringkafkaproducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringkafkaproducerApplication.class, args);
	}

}
