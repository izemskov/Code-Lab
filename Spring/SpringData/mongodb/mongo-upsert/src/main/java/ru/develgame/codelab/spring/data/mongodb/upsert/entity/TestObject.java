package ru.develgame.codelab.spring.data.mongodb.upsert.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@Document("test_object")
public class TestObject {
    @Id
    private String id;
    private String name;
    private Date createdAt;
}
