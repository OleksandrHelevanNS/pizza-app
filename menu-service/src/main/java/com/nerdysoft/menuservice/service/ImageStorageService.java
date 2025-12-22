package com.nerdysoft.menuservice.service;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface ImageStorageService {
    Mono<String> upload(FilePart file);
}
