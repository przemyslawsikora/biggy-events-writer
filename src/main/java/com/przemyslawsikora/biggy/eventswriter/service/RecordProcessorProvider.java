/*
 * Copyright (C) 2019 Przemyslaw Sikora
 */

package com.przemyslawsikora.biggy.eventswriter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class RecordProcessorProvider {
    private Set<RecordProcessor> recordProcessors;

    @Autowired
    public RecordProcessorProvider(Set<RecordProcessor> recordProcessors) {
        this.recordProcessors = recordProcessors;
    }

    @Bean(name = "recordProcessorMap")
    public Map<String, RecordProcessor> provideRecordProcessorMap() {
        return recordProcessors.stream().collect(Collectors.toMap(
                ep -> ep.getClass().getDeclaredAnnotation(Qualifier.class).value(), Function.identity()));
    }
}
