/*
 * Copyright (C) 2019 Przemyslaw Sikora
 */

package com.przemyslawsikora.biggy.eventswriter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.przemyslawsikora.biggy.eventswriter.dao.elastic")
@EnableMongoRepositories(basePackages = "com.przemyslawsikora.biggy.eventswriter.dao.mongo")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
