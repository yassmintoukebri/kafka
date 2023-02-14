package com.microservices.springkafkaproducer.bean;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("user1")
@Data
@Builder

public class User implements Serializable {

    @Id
    private String id;
    private String name;
    private double balance;


}
