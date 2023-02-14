package com.microservices.springkafkaproducer.repo;

import com.microservices.springkafkaproducer.bean.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderCRUD extends MongoRepository<Order,String> {



}
