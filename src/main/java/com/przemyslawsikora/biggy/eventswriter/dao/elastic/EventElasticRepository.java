/*
 * Copyright (C) 2019 Przemyslaw Sikora
 */

package com.przemyslawsikora.biggy.eventswriter.dao.elastic;

import com.przemyslawsikora.biggy.eventswriter.dao.elastic.model.Event;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventElasticRepository extends ElasticsearchRepository<Event, String> {
}
