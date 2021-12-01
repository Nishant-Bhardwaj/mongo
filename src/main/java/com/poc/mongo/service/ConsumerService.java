package com.poc.mongo.service;

import com.poc.mongo.constant.Constants;
import com.poc.mongo.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ConsumerService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @RabbitListener(queues = Constants.ADMIN_QUEUE)
    public void consumeMessagesFromAdminQueue(Student student) throws Exception {

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Message Received, would you like to receive(Y/N)");
            String action = scanner.nextLine();

            if(action.equalsIgnoreCase("N"))
                throw new Exception("Do not consume");

            logger.info("Student Consumed from Admin Queue: "+ student);
        }catch (Exception e){
            logger.error("Exception "+ e + " , sending message back to Queue");
            rabbitTemplate.convertAndSend(Constants.TOPIC_EXCHANGE, Constants.ADMIN_ROUTING_KEY, student);
        }

    }

    @RabbitListener(queues = Constants.STUDENT_QUEUE)
    public void consumeMessagesFromStudentQueue(Student student){
        logger.info("Core:: Student Consumed from Student Queue: "+ student);
    }
}
