# JavaEE Rest-api service


## Get all objects

```
curl --location 'http://localhost:8080/service-rest-api-1.0.0/resources/sc2units'
```

## Add new unit

```
curl --location 'http://localhost:8080/service-rest-api-1.0.0/resources/sc2units' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Queen",
    "attack": 5.0,
    "defense": 5.0
}'
```