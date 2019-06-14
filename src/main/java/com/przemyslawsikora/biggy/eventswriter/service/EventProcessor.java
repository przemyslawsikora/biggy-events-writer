/*
 * Copyright (C) 2019 Przemyslaw Sikora
 */

package com.przemyslawsikora.biggy.eventswriter.service;

import com.przemyslawsikora.biggy.eventswriter.dao.EventRepository;
import com.przemyslawsikora.biggy.eventswriter.dao.model.Event;
import com.przemyslawsikora.biggy.eventswriter.exception.UnsupportedDataSchema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Qualifier("com.przemyslawsikora.biggy.Event")
public class EventProcessor implements RecordProcessor {
    private EventRepository eventRepository;

    public EventProcessor(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void process(GenericRecord record) {
        if (!record.getSchema().getFullName().equals("com.przemyslawsikora.biggy.Event")) {
            throw new UnsupportedDataSchema(String.format("Cannot process record Event from schema %s",
                    record.getSchema().getFullName()));
        }
        if (record.getSchema().getProp("version").equals("1")) {
            processVersion1(record);
        } else {
            throw new UnsupportedDataSchema(String.format("Processor of Event in version %s not found",
                    record.getSchema().getProp("version")));
        }
    }

    @SuppressWarnings("unchecked")
    private void processVersion1(GenericRecord record) {
        Event event = new Event();
        event.setSchemaVersion("1");
        event.setDate(new Date((Long) record.get("date")));
        event.setDateOffset(ZoneOffset.of(record.get("dateOffset").toString()).getTotalSeconds() / 60);
        if (record.get("attributes") != null) {
            Map<String, Object> attributes = new HashMap<>();
            ((Map<Utf8, Object>) record.get("attributes")).forEach((k, v) ->
                    attributes.put(k.toString(), v instanceof Utf8 ? v.toString() : v));
            event.setAttributes(attributes);
        }
        eventRepository.save(event);
    }
}
