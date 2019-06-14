/*
 * Copyright (C) 2019 Przemyslaw Sikora
 */

package com.przemyslawsikora.biggy.eventswriter.service;

import org.apache.avro.generic.GenericData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class KafkaConsumer {
    @Resource(name = "recordProcessorMap")
    private Map<String, RecordProcessor> recordProcessorMap;

    @KafkaListener(topics = "events")
    public void consume(@Payload ConsumerRecord<String, GenericData.Record> data){
        recordProcessorMap.get(data.value().getSchema().getFullName()).process(data.value());
    }
}
