package ru.develgame.codelab.spring.data.mongodb.ttl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Service;
import ru.develgame.codelab.spring.data.mongodb.ttl.entity.TemporaryObject;

import java.util.concurrent.TimeUnit;

@Service
public class MongoTTLIndexService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoTTLIndexService(@Qualifier("mongoTemplateDb") MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void createIndex() {
        if (!mongoTemplate.collectionExists("temporary_object")) {
            mongoTemplate.createCollection("temporary_object");

            mongoTemplate.indexOps(TemporaryObject.class).ensureIndex(
                    new Index()
                            .on(TemporaryObject.Fields.createdAt, Sort.Direction.ASC)
                            .expire(300, TimeUnit.SECONDS));
        }
    }
}
