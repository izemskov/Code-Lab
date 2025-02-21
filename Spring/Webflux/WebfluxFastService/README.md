# Spring Boot reactive microservice

Get stream of UUIDs

```
curl --location 'http://localhost:8070/v1/uuid?count=1000'
```

Upload file (see app console)

```
curl --location 'http://localhost:8070/v1/uploader/upload' \
--form 'file=@"/Users/izemskov/Dropbox/Work/SC2/baneling.jpg"'
```