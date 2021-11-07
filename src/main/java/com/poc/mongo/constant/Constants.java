package com.poc.mongo.constant;

import org.springframework.stereotype.Component;

@Component
public class Constants {

    public static final String STUDENT_QUEUE = "student_queue";
    public static final String ADMIN_QUEUE = "admin_queue";

    public static final String TOPIC_EXCHANGE = "exchange";
    public static final String STUDENT_ROUTING_KEY = "student_routing_Key";
    public static final String ADMIN_ROUTING_KEY = "admin_routing_Key";
}
