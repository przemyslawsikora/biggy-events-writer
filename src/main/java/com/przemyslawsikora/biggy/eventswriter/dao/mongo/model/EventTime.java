/*
 * Copyright (C) 2019 Przemyslaw Sikora
 */

package com.przemyslawsikora.biggy.eventswriter.dao.mongo.model;

import com.przemyslawsikora.biggy.eventswriter.dao.model.EventTimeType;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public class EventTime {
    @Field("type")
    private EventTimeType type;

    @Field("startDate")
    private Date startDate;

    @Field("startDateOffset")
    private Integer startDateOffset;

    @Field("stopDate")
    private Date stopDate;

    @Field("stopDateOffset")
    private Integer stopDateOffset;

    public EventTimeType getType() {
        return type;
    }

    public void setType(EventTimeType type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getStartDateOffset() {
        return startDateOffset;
    }

    public void setStartDateOffset(Integer startDateOffset) {
        this.startDateOffset = startDateOffset;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public Integer getStopDateOffset() {
        return stopDateOffset;
    }

    public void setStopDateOffset(Integer stopDateOffset) {
        this.stopDateOffset = stopDateOffset;
    }
}
