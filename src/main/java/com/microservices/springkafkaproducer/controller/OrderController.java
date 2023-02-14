package com.microservices.springkafkaproducer.controller;

import com.microservices.springkafkaproducer.bean.Order;
import com.microservices.springkafkaproducer.serviceinterface.InterfaceGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private InterfaceGeneric<Order> orderService;




    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        order=orderService.create(order);
        return order;
    }




    @GetMapping
    public List<Order> getOrders() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable String id) {
        return orderService.findById(id);
    }
    @PutMapping("/{id}")
    public Order update(@PathVariable Integer id ,@RequestBody Order order) {
        return orderService.updateYourEntity(order);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        orderService.delete(id);
    }



}

