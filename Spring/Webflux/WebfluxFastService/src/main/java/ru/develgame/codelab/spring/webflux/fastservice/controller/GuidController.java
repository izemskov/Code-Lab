package ru.develgame.codelab.spring.webflux.fastservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("v1/uuid")
public class GuidController {
    @GetMapping
    public ResponseEntity<Flux<String>> fetchAll(@RequestParam Integer count) {
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(Flux.<String>generate(sink -> {
                    String uuid = UUID.randomUUID().toString();
                    log.info(uuid);
                    sink.next(uuid);
                }).take(count));
    }
}
