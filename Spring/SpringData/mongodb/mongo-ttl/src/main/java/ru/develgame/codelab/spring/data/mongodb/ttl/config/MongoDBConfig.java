package ru.develgame.codelab.spring.data.mongodb.ttl.config;

import com.mongodb.ConnectionString;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "ru.develgame.codelab.spring.data.mongodb.ttl.repository",
        mongoTemplateRef = "mongoTemplateDb"
)
@RequiredArgsConstructor
public class MongoDBConfig extends BaseMongoDBConfiguration {

    private final MongoProperties mongoProperties;

    @Bean("mongoTemplateDb")
    public MongoTemplate mongoTemplateGcDb() throws ClassNotFoundException {
        MongoCustomConversions mongoCustomConversions = mongoCustomConversions();

        StringBuilder connectionString = new StringBuilder();
        connectionString.append("mongodb://");
        if (mongoProperties.user() != null && !mongoProperties.user().isEmpty()
                && mongoProperties.pass() != null && !mongoProperties.pass().isEmpty()) {
            connectionString.append(mongoProperties.user());
            connectionString.append(":");
            connectionString.append(mongoProperties.pass());
            connectionString.append("@");
        }
        connectionString.append(mongoProperties.host());
        connectionString.append("/");
        connectionString.append(mongoProperties.database());
        if (mongoProperties.extraArgs() != null && !mongoProperties.extraArgs().isEmpty()) {
            connectionString.append("?");
            connectionString.append(mongoProperties.extraArgs());
        }

        return this.getMongoTemplate(
                new ConnectionString(connectionString.toString()),
                mongoProperties.database(),
                mongoMappingContext(mongoCustomConversions),
                mongoCustomConversions,
                true
        );
    }

    @Bean("mongoTransactionManagerDb")
    public MongoTransactionManager mongoTransactionManageGcDb(
            @Qualifier("mongoTemplateDb") MongoTemplate mongoTemplateBdcDb) {
        return new MongoTransactionManager(mongoTemplateBdcDb.getMongoDatabaseFactory());
    }
}
