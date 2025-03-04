package ru.develgame.codelab.spring.data.mongodb.upsert.repository;

import ru.develgame.codelab.spring.data.mongodb.upsert.entity.TestObject;

import java.util.Date;

public interface TestObjectCustomRepository {
    TestObject createIfNotExists(String name, Date createdAt);
}
