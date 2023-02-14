package com.microservices.springkafkaproducer.repo;

import com.microservices.springkafkaproducer.DTO.UserDto;
import com.microservices.springkafkaproducer.bean.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserCRUD extends MongoRepository<User,String> {
}
