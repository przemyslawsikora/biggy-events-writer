/*
 * Copyright (C) 2019 Przemyslaw Sikora
 */

package com.przemyslawsikora.biggy.eventswriter.service;

import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public abstract class EventConverter {

    @SuppressWarnings("unchecked")
    protected Map<String, Object> buildEventAttributes(GenericRecord record) {
        if (record.get("attributes") == null) {
            return Collections.emptyMap();
        }
        Map<String, Object> attributes = new HashMap<>();
        ((Map<Utf8, Object>) record.get("attributes")).forEach((k, v) ->
                attributes.put(k.toString(), convertAttributeValue(v)));
        return attributes;
    }

    @SuppressWarnings("unchecked")
    private Object convertAttributeValue(Object rawObject) {
        if (rawObject instanceof GenericData.Array) {
            return ((GenericData.Array) rawObject).stream().map(this::convertAttributeValue).collect(Collectors.toList());
        }
        if (rawObject instanceof Utf8) {
            return rawObject.toString();
        }
        if (rawObject instanceof ByteBuffer) {
            return ((ByteBuffer) rawObject).array();
        }
        return rawObject;
    }
}
