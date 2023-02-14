package com.microservices.springkafkaproducer.DTO;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private String name;
    private double balance;
}
