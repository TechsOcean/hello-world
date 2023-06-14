package com.chandan.hibernateentitymappings.controller;

import com.chandan.hibernateentitymappings.config.RabbitMQConfig;
import com.chandan.hibernateentitymappings.entities.Order;
import com.chandan.hibernateentitymappings.entities.OrderStatus;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController("/order")
public class RabbitMQController extends RabbitMQConfig {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {

        order.setOrderId(UUID.randomUUID().toString());
        //restraurant service
        // payment service
        System.out.println(" RabbitMQController==amqpTemplate== "+ amqpTemplate);
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "Order Placed successfully in" + restaurantName);
        amqpTemplate.convertAndSend(RabbitMQConfig.FIRST_EXCHANGE, RabbitMQConfig.FIRST_ROUTING_KEY, orderStatus);
        return "Success";
    }

    @RequestMapping("/fail/{failure}")
    public String bookFailedOrder(@RequestBody Order order, @PathVariable String failure) {
        order.setOrderId(UUID.randomUUID().toString());
        //restraurant service
        // payment service
        System.out.println(" /fail/{failure}RabbitMQController==amqpTemplate== \n"+ amqpTemplate);
        OrderStatus orderStatus = new OrderStatus(order, "FAILED", "Order Placed successfully in" + failure);
        amqpTemplate.convertAndSend(RabbitMQConfig.FIRST_EXCHANGE, RabbitMQConfig.FAILURE_TEST_ROUTING_KEY, orderStatus);
        return "failed";
    }

}
