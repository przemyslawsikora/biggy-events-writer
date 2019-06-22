/*
 * Copyright (C) 2019 Przemyslaw Sikora
 */

package com.przemyslawsikora.biggy.eventswriter.dao.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EventTimeType {
    MOMENT("moment"),
    PERIOD_CLOSED_CLOSED("periodClosedClosed"),
    PERIOD_CLOSED_OPENED("periodClosedOpened");

    private String name;

    EventTimeType(String name) {
        this.name = name;
    }

    @JsonCreator
    public static EventTimeType fromString(String name) {
        for (EventTimeType eventTimeType : values()) {
            if (eventTimeType.name.equals(name)) {
                return eventTimeType;
            }
        }
        throw new IllegalArgumentException(String.format("Unrecognized event time type (%s)", name));
    }

    @Override
    @JsonValue
    public String toString() {
        return name;
    }
}
