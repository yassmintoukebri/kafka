package com.microservices.springkafkaproducer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.springkafkaproducer.bean.Order;
import com.microservices.springkafkaproducer.enums.Enum;
import com.microservices.springkafkaproducer.exception.EntityDoesNotExistException;
import com.microservices.springkafkaproducer.repo.OrderCRUD;
import com.microservices.springkafkaproducer.serviceinterface.InterfaceGeneric;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class OrderService implements InterfaceGeneric<Order> {

    @Autowired
    private OrderCRUD orderCRUD;

    private Enum topicName=Enum.ORDER_TOPIC;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    ObjectMapper om=new ObjectMapper();


    @Override
    public Order create(Order order) {
        order =orderCRUD.save(order);
        log.info("CREATED");
        // after saving order lets release msg for payment service
        String message= null;
        try {
            message = om.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(topicName.toString(),message);
        log.info("send message to Topic");
        return order;
    }

    @Override
    public List<Order> getAll() {
        return  orderCRUD.findAll();
    }




    @Override
    public Order findById(String id) {
        return orderCRUD.findById(id).orElseThrow(()-> new  EntityDoesNotExistException(id));
    }




    @Override
    public Order updateYourEntity(Order order) {
        Optional<Order> updatedEntityOptional = orderCRUD.findById(order.getId());

        if (!updatedEntityOptional.isPresent()) {
            return null;
        }

        Order updatedEntity = updatedEntityOptional.get();

        updatedEntity.setUserId(order.getUserId());
        updatedEntity.setOrderAmount(order.getOrderAmount());
        updatedEntity.setStatus(order.getStatus());

        return orderCRUD.save(updatedEntity);
    }


    @Override
    public boolean delete(String id) {

        Optional<Order> entityOptional = orderCRUD.findById(id);
        if (!entityOptional.isPresent()) {
            return false;
        }

        orderCRUD.delete(entityOptional.get());
        return true;
    }
}





