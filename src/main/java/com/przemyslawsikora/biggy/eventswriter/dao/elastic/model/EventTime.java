/*
 * Copyright (C) 2019 Przemyslaw Sikora
 */

package com.przemyslawsikora.biggy.eventswriter.dao.elastic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.przemyslawsikora.biggy.eventswriter.dao.model.EventTimeType;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.ZonedDateTime;

public class EventTime {
    @Field(type = FieldType.Text)
    private EventTimeType type;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    private ZonedDateTime startDate;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
    private ZonedDateTime stopDate;

    public EventTimeType getType() {
        return type;
    }

    public void setType(EventTimeType type) {
        this.type = type;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getStopDate() {
        return stopDate;
    }

    public void setStopDate(ZonedDateTime stopDate) {
        this.stopDate = stopDate;
    }
}
