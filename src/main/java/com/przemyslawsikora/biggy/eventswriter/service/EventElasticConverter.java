/*
 * Copyright (C) 2019 Przemyslaw Sikora
 */

package com.przemyslawsikora.biggy.eventswriter.service;

import com.przemyslawsikora.biggy.eventswriter.dao.elastic.model.Event;
import com.przemyslawsikora.biggy.eventswriter.dao.elastic.model.EventTime;
import com.przemyslawsikora.biggy.eventswriter.dao.model.EventTimeType;
import com.przemyslawsikora.biggy.eventswriter.exception.UnsupportedDataSchema;
import org.apache.avro.generic.GenericRecord;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
public class EventElasticConverter extends EventConverter {
    public Event convertFromEventRecord(GenericRecord record) {
        if (!record.getSchema().getProp("version").equals("1")) {
            throw new UnsupportedDataSchema(String.format("Processor of Event in version %s not found",
                    record.getSchema().getProp("version")));
        }
        return buildEventVer1(record);
    }

    private Event buildEventVer1(GenericRecord record) {
        var event = new Event();
        event.setSchemaVersion("1");
        event.setObject(record.get("object").toString());
        event.setTime(buildEventTime((GenericRecord) record.get("time")));
        event.setAttributes(buildEventAttributes(record));
        return event;
    }

    private EventTime buildEventTime(GenericRecord record) {
        EventTime eventTime = new EventTime();
        eventTime.setType(EventTimeType.fromString(record.get("type").toString()));
        eventTime.setStartDate(ZonedDateTime.ofInstant(Instant.ofEpochMilli((Long) record.get("startDate")),
                ZoneOffset.of(record.get("startDateOffset").toString())));
        switch (eventTime.getType()) {
            case MOMENT:
                eventTime.setStopDate(ZonedDateTime.ofInstant(Instant.ofEpochMilli((Long) record.get("startDate")),
                        ZoneOffset.of(record.get("startDateOffset").toString())));
                break;

            case PERIOD_CLOSED_CLOSED:
                eventTime.setStopDate(ZonedDateTime.ofInstant(Instant.ofEpochMilli((Long) record.get("stopDate")),
                        ZoneOffset.of(record.get("stopDateOffset").toString())));
                break;

            default:
                break;
        }
        return eventTime;
    }
}
