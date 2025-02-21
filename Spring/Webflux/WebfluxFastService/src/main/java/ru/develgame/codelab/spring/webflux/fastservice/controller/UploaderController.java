package ru.develgame.codelab.spring.webflux.fastservice.controller;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/uploader")
public class UploaderController {
    @PostMapping("upload")
    public Mono<ResponseEntity> upload(@RequestPart FilePart file) {
        file.content().subscribe(new BaseSubscriber<>() {
            @Override
            protected void hookOnNext(DataBuffer value) {
                System.out.println("==== DATA BLOCK ====");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(value.readableByteCount());
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("==== COMPLETED ====");
            }
        });

        return Mono.just(ResponseEntity.ok().build());
    }
}
