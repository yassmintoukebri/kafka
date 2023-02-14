package com.microservices.springkafkaproducer.serviceinterface;


import java.util.List;

public interface InterfaceGeneric<T> {

    T create(T data);
    List<T> getAll();
    T findById(String id);
    T updateYourEntity(T data);
    boolean delete(String id);

}
