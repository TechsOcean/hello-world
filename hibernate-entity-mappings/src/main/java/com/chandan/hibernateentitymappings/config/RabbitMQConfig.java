package com.chandan.hibernateentitymappings.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.system.JavaVersion;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@Configuration
public class RabbitMQConfig {

    public static final String FIRST_QUEUE = "first_queue";
    public static final String FIRST_EXCHANGE = "first_exchange";
    public static final String FIRST_ROUTING_KEY = "first_routing_key";

    public static final String FAILURE_TEST_QUEUE = "failure_test_queue";
    public static final String FAILURE_TEST_EXCHANGE = "failure_test_exchange";
    public static final String FAILURE_TEST_ROUTING_KEY = "failure_test_routing_key";

    @ConditionalOnMissingBean(Queue.class)
    @Bean
    public Queue queue() {
        return new Queue(FIRST_QUEUE);
    }

    @Bean
    @ConditionalOnJava(value = JavaVersion.EIGHT)
    public TopicExchange exchange() {
        return new TopicExchange(FIRST_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(FIRST_ROUTING_KEY);
    }

    @Bean
    public Queue failureQueue() {
        return new Queue(FAILURE_TEST_QUEUE);
    }

    @Bean
    public TopicExchange failureExchange() {
        return new TopicExchange(FAILURE_TEST_EXCHANGE);
    }

    @Bean
    public Binding bindingForFailures(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(failureQueue()).to(exchange).with(FAILURE_TEST_ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        System.out.println(" connectionFactory ==="+connectionFactory);
        final RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter());
        System.out.println(" amqpTemplate "+template);
        return template;
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
