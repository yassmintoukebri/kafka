package com.microservices.springkafkaproducer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.springkafkaproducer.bean.User;
import com.microservices.springkafkaproducer.enums.Enum;
import com.microservices.springkafkaproducer.exception.EntityDoesNotExistException;
import com.microservices.springkafkaproducer.repo.UserCRUD;
import com.microservices.springkafkaproducer.serviceinterface.InterfaceGeneric;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements InterfaceGeneric<User> {



    @Autowired
   private UserCRUD userCRUD;

    private Enum topicName = Enum.ORDER_TOPIC;



    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    ObjectMapper om=new ObjectMapper();





    @Override
    public User create(User user) {
        user =userCRUD.save(user);
        log.info("user created");
        // after saving order lets release msg for payment service
        String message= null;
        try {
            message = om.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(topicName.toString(),message);
        log.info("send message to Topic");
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users= (List<User>) userCRUD.findAll();
        return users;
    }


    @Override
    public User findById(String id) {
        return userCRUD.findById(id).orElseThrow(()-> new EntityDoesNotExistException(id));
    }


    @Override
    public User updateYourEntity(User user) {

        Optional<User> updatedEntityOptional = userCRUD.findById(user.getId());

        if (!updatedEntityOptional.isPresent()) {
            return null;
        }

        User updatedEntity = updatedEntityOptional.get();

        updatedEntity.setBalance(user.getBalance());
        updatedEntity.setName(user.getName());

        return userCRUD.save(updatedEntity);

    }

    @Override
    public boolean delete(String id) {

        Optional<User> entityOptional = userCRUD.findById(id);
        if (!entityOptional.isPresent()) {
            return false;
        }
        userCRUD.delete(entityOptional.get());
        return true;
    }
    }

