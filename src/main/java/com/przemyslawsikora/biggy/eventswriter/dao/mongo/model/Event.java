/*
 * Copyright (C) 2019 Przemyslaw Sikora
 */

package com.przemyslawsikora.biggy.eventswriter.dao.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "events")
public class Event {
    @Id
    private String id;

    @Field("schemaVersion")
    private String schemaVersion;

    @Field("object")
    private String object;

    @Field("time")
    private EventTime time;

    @Field("attributes")
    private Map<String, Object> attributes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public EventTime getTime() {
        return time;
    }

    public void setTime(EventTime time) {
        this.time = time;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
