package com.poc.mongo.service;

import com.poc.mongo.constant.Constants;
import com.poc.mongo.model.Student;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public String produceMessageToStudentQueue(Student student){

        rabbitTemplate.convertAndSend(Constants.TOPIC_EXCHANGE, Constants.STUDENT_ROUTING_KEY, student);
        return "Published Student "+ student.getName() +" to Student Queue.";
    }

    public String produceMessageToAdminQueue(Student student){

        rabbitTemplate.convertAndSend(Constants.TOPIC_EXCHANGE, Constants.ADMIN_ROUTING_KEY, student);
        return "Published Student "+ student.getName() +" to Admin Queue.";
    }

}
