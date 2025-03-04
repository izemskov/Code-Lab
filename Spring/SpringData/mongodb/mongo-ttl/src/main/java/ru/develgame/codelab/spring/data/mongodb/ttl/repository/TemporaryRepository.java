package ru.develgame.codelab.spring.data.mongodb.ttl.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.develgame.codelab.spring.data.mongodb.ttl.entity.TemporaryObject;

@Repository
public interface TemporaryRepository extends MongoRepository<TemporaryObject, String> {
}
