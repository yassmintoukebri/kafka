package com.microservices.springkafkaproducer.exception;

import javax.swing.text.html.parser.Entity;

public class EntityDoesNotExistException extends RuntimeException{

    public EntityDoesNotExistException(String id){
        super("Entity " + id + " does not exist.");
    }
}
