/*
 * Copyright (C) 2019 Przemyslaw Sikora
 */

package com.przemyslawsikora.biggy.eventswriter.service;

import com.przemyslawsikora.biggy.eventswriter.dao.elastic.EventElasticRepository;
import com.przemyslawsikora.biggy.eventswriter.dao.mongo.EventMongoRepository;
import com.przemyslawsikora.biggy.eventswriter.exception.UnsupportedDataSchema;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("com.przemyslawsikora.biggy.Event")
public class EventProcessor implements RecordProcessor {
    private EventMongoConverter eventMongoConverter;
    private EventMongoRepository eventMongoRepository;
    private EventElasticRepository eventElasticRepository;
    private EventElasticConverter eventElasticConverter;

    public EventProcessor(EventMongoConverter eventMongoConverter,
                          EventMongoRepository eventMongoRepository,
                          EventElasticRepository eventElasticRepository,
                          EventElasticConverter eventElasticConverter) {
        this.eventMongoConverter = eventMongoConverter;
        this.eventMongoRepository = eventMongoRepository;
        this.eventElasticRepository = eventElasticRepository;
        this.eventElasticConverter = eventElasticConverter;
    }

    @Override
    public void process(GenericRecord record) {
        if (!record.getSchema().getFullName().equals("com.przemyslawsikora.biggy.Event")) {
            throw new UnsupportedDataSchema(String.format("Cannot process record Event from schema %s",
                    record.getSchema().getFullName()));
        }
        eventMongoRepository.save(eventMongoConverter.convertFromEventRecord(record));
        eventElasticRepository.save(eventElasticConverter.convertFromEventRecord(record));
    }
}
