# Kafka + Opensearch

Sending data from Spring Boot application via Kafka to Opensearch

```ascii
+------------------+      +------------+      +----------------------+      +------------+
| Spring Boot App  | ---> |   Kafka    | ---> | Kafka Connect (Sink) | ---> | OpenSearch |
+------------------+      +------------+      +----------------------+      +------------+
```

1. Download opensearch connect plugin from https://github.com/Aiven-Open/opensearch-connector-for-apache-kafka and extract
to folder "kafka-connect-plugins" into project root folder 

2. Run docker compose:

```
docker compose up -d
```

3. Add sink connector

```
curl -X POST -H "Content-Type: application/json" --data @opensearch-sink-config.json http://localhost:8083/connectors
```

Check up connector
```
curl -X GET http://localhost:8083/connectors/opensearch-connector/status
```

4. Run application and check adding data by http://localhost:5601/