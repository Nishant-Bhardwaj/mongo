package com.poc.mongo.model;


public class QueueConfig {

    private String queueNames;
    private String routing;

    public QueueConfig() {
    }

    public QueueConfig(String queueNames, String routing) {
        this.queueNames = queueNames;
        this.routing = routing;
    }

    public String getQueueNames() {
        return queueNames;
    }

    public void setQueueNames(String queueNames) {
        this.queueNames = queueNames;
    }

    public String getRouting() {
        return routing;
    }

    public void setRouting(String routing) {
        this.routing = routing;
    }
}
