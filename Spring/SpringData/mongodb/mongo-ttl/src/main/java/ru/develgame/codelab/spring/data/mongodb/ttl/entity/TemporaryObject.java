package ru.develgame.codelab.spring.data.mongodb.ttl.entity;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Data
@FieldNameConstants
@Document("temporary_object")
public class TemporaryObject {
    @Id
    private String id;

    private String name;

    //@Indexed(name = "ttl_index", expireAfterSeconds = 60)
    private Date createdAt;
}
