package com.poc.mongo.config;

import com.poc.mongo.constant.Constants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    @Bean
    public Queue studentQueue(){
        return new Queue(Constants.STUDENT_QUEUE);
    }

    @Bean
    public Queue adminQueue(){
        return new Queue(Constants.ADMIN_QUEUE);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(Constants.TOPIC_EXCHANGE);
    }

    @Bean
    public Binding studentBinding(Queue studentQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(studentQueue).to(topicExchange).with(Constants.STUDENT_ROUTING_KEY);
    }

    @Bean
    public Binding adminBinding(Queue adminQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(adminQueue).to(topicExchange).with(Constants.ADMIN_ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
