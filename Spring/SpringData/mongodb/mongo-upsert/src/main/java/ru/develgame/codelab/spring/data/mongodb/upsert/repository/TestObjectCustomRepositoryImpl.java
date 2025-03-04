package ru.develgame.codelab.spring.data.mongodb.upsert.repository;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import ru.develgame.codelab.spring.data.mongodb.upsert.entity.TestObject;

import java.util.Date;

@Component
public class TestObjectCustomRepositoryImpl implements TestObjectCustomRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public TestObjectCustomRepositoryImpl(@Qualifier("mongoTemplateDb") MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public TestObject createIfNotExists(String name, Date createdAt) {
        Query query = new Query(Criteria.where("name").is(name));

        Update update = new Update()
                .setOnInsert("createdAt", createdAt)
                .setOnInsert("name", name);

        UpdateResult upsert = mongoTemplate.upsert(query, update, TestObject.class);
        if (upsert.getUpsertedId() == null) {
            throw new RuntimeException("Already exists");
        }
        return mongoTemplate.findById(upsert.getUpsertedId(), TestObject.class);
    }
}
