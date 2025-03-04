package ru.develgame.codelab.spring.data.mongodb.upsert.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.develgame.codelab.spring.data.mongodb.upsert.dto.TestDto;
import ru.develgame.codelab.spring.data.mongodb.upsert.entity.TestObject;
import ru.develgame.codelab.spring.data.mongodb.upsert.repository.TestObjectRepository;

import java.util.Date;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final TestObjectRepository testObjectRepository;

    @PostMapping
    public ResponseEntity<TestDto> create(@RequestBody TestDto testDto) {
        TestObject testObject = testObjectRepository.createIfNotExists(testDto.name(), new Date());
        return ResponseEntity.ok(TestDto.builder().name(testObject.getName()).build());
    }
}
