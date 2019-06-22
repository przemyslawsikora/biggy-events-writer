/*
 * Copyright (C) 2019 Przemyslaw Sikora
 */

package com.przemyslawsikora.biggy.eventswriter.dao.mongo;

import com.przemyslawsikora.biggy.eventswriter.dao.mongo.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventMongoRepository extends MongoRepository<Event, String> {
}
