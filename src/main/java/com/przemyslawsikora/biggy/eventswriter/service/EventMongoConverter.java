/*
 * Copyright (C) 2019 Przemyslaw Sikora
 */

package com.przemyslawsikora.biggy.eventswriter.service;

import com.przemyslawsikora.biggy.eventswriter.dao.model.EventTimeType;
import com.przemyslawsikora.biggy.eventswriter.dao.mongo.model.Event;
import com.przemyslawsikora.biggy.eventswriter.dao.mongo.model.EventTime;
import com.przemyslawsikora.biggy.eventswriter.exception.UnsupportedDataSchema;
import org.apache.avro.generic.GenericRecord;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.Date;

@Service
public class EventMongoConverter extends EventConverter {
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
        eventTime.setStartDate(new Date((Long) record.get("startDate")));
        eventTime.setStartDateOffset(ZoneOffset.of(record.get("startDateOffset").toString()).getTotalSeconds() / 60);
        switch (eventTime.getType()) {
            case MOMENT:
                eventTime.setStopDate(new Date((Long) record.get("startDate")));
                eventTime.setStopDateOffset(ZoneOffset.of(record.get("startDateOffset").toString()).getTotalSeconds() / 60);
                break;

            case PERIOD_CLOSED_CLOSED:
                eventTime.setStopDate(new Date((Long) record.get("stopDate")));
                eventTime.setStopDateOffset(ZoneOffset.of(record.get("stopDateOffset").toString()).getTotalSeconds() / 60);
                break;

            default:
                break;
        }
        return eventTime;
    }
}
