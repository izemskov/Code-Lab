package ru.develgame.codelab.spring.data.mongodb.ttl.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClients;
import org.bson.types.Decimal128;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public abstract class BaseMongoDBConfiguration {
    @Value("${mongodb.connect.timeout:10000}")
    protected int connectTimeout;

    @Value("${mongodb.read.timeout:10000}")
    protected int readTimeout;

    @Value("${mongodb.server.selection.timeout:30000}")
    protected int serverSelectionTimeout;

    @Value("${spring.data.mongodb.secondary.preferred:false}")
    protected boolean secondaryPreferred;

    @Autowired
    protected ApplicationContext applicationContext;

    protected MongoTemplate getMongoTemplate(
            ConnectionString connectionString, String dbName,
            MongoMappingContext mongoMappingContext,
            MongoCustomConversions mongoCustomConversions,
            boolean secondaryPreferred) {
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyToSocketSettings(settings -> {
                    settings.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
                    settings.readTimeout(readTimeout, TimeUnit.MILLISECONDS);
                })
                .applyToClusterSettings(builder ->
                        builder.serverSelectionTimeout(serverSelectionTimeout, TimeUnit.MILLISECONDS))
                .applyConnectionString(connectionString)
                .writeConcern(WriteConcern.W1)
                .build();

        MongoDatabaseFactory factory = new SimpleMongoClientDatabaseFactory(MongoClients.create(mongoClientSettings), dbName);
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(factory), mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        converter.setCustomConversions(mongoCustomConversions);
        converter.afterPropertiesSet();

        MongoTemplate mongoTemplate = new MongoTemplate(factory, converter);
        if (secondaryPreferred) {
            mongoTemplate.setReadPreference(ReadPreference.secondaryPreferred());
        }
        return mongoTemplate;
    }

    protected MongoTemplate getMongoTemplate(
            ConnectionString connectionString, String dbName,
            MongoMappingContext mongoMappingContext,
            MongoCustomConversions mongoCustomConversions) {
        return getMongoTemplate(connectionString, dbName, mongoMappingContext, mongoCustomConversions, secondaryPreferred);
    }

    @Bean
    @ConditionalOnMissingBean
    public MongoMappingContext mongoMappingContext(MongoCustomConversions mongoCustomConversions) throws ClassNotFoundException {
        MongoMappingContext context = new MongoMappingContext();
        context.setInitialEntitySet(new EntityScanner(applicationContext)
                .scan(Document.class, Persistent.class));
        context.setSimpleTypeHolder(mongoCustomConversions.getSimpleTypeHolder());
        return context;
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new BigDecimalDecimal128Converter(),
                new Decimal128BigDecimalConverter(),
                new FloatDecimal128Converter(),
                new FloatBigDecimalConverter()
        ));
    }

    @WritingConverter
    private static class BigDecimalDecimal128Converter implements Converter<BigDecimal, Decimal128> {

        @Override
        public Decimal128 convert(BigDecimal source) {
            return new Decimal128(source);
        }
    }

    @WritingConverter
    private static class FloatDecimal128Converter implements Converter<Float, Decimal128> {

        @Override
        public Decimal128 convert(Float source) {
            return new Decimal128(BigDecimal.valueOf(source));
        }
    }

    @ReadingConverter
    private static class Decimal128BigDecimalConverter implements Converter<Decimal128, BigDecimal> {

        @Override
        public BigDecimal convert(Decimal128 source) {
            return source.bigDecimalValue();
        }
    }

    @ReadingConverter
    private static class FloatBigDecimalConverter implements Converter<Float, BigDecimal> {

        @Override
        public BigDecimal convert(Float source) {
            return BigDecimal.valueOf(source);
        }
    }
}
