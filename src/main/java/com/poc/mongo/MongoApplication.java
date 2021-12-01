package com.poc.mongo;

import com.poc.mongo.config.MessagingConfig;
import com.poc.mongo.constant.Constants;
import com.poc.mongo.model.QueueConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MongoApplication {

	@Autowired
	MessagingConfig messagingConfig;

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}

	@PostConstruct
	public void configuration(){
		List<QueueConfig> configList = Arrays.asList(
				new QueueConfig(Constants.STUDENT_QUEUE, Constants.STUDENT_ROUTING_KEY),
				new QueueConfig(Constants.ADMIN_QUEUE, Constants.ADMIN_ROUTING_KEY)
		);

		configList.forEach(x-> messagingConfig.configBinding(x.getQueueNames(), x.getRouting()));
	}

}
