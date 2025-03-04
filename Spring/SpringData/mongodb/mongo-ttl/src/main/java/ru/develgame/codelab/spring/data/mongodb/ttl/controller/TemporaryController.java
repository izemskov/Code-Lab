package ru.develgame.codelab.spring.data.mongodb.ttl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.develgame.codelab.spring.data.mongodb.ttl.dto.TemporaryDto;
import ru.develgame.codelab.spring.data.mongodb.ttl.entity.TemporaryObject;
import ru.develgame.codelab.spring.data.mongodb.ttl.repository.TemporaryRepository;

import java.time.Instant;
import java.util.Date;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TemporaryController {
    private final TemporaryRepository temporaryRepository;

    @PostMapping
    public ResponseEntity<TemporaryDto> create(@RequestBody TemporaryDto temporaryDto) {
        TemporaryObject temporaryObject = new TemporaryObject();
        temporaryObject.setName(temporaryDto.name());
        temporaryObject.setCreatedAt(new Date());
        temporaryObject = temporaryRepository.save(temporaryObject);

        return ResponseEntity.ok(TemporaryDto.builder()
                .id(temporaryObject.getId())
                .name(temporaryObject.getName())
                .build());
    }
}
