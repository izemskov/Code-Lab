package ru.develgame.codelab.spring.data.mongodb.upsert.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.develgame.codelab.spring.data.mongodb.upsert.entity.TestObject;

@Repository
public interface TestObjectRepository extends MongoRepository<TestObject, String>, TestObjectCustomRepository {
}
