/*
 * Copyright (C) 2019 Przemyslaw Sikora
 */

package com.przemyslawsikora.biggy.eventswriter.service;

import org.apache.avro.generic.GenericRecord;
import org.springframework.stereotype.Component;

@Component
public interface RecordProcessor {
    void process(GenericRecord record);
}
