package com.nerdysoft.menuservice.service.impl;

import com.nerdysoft.menuservice.service.ImageStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageStorageServiceImpl implements ImageStorageService {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Value("${aws.s3.region}")
    private String region;

    @Override
    public Mono<String> upload(FilePart file) {
        String key = "pizzas/" + UUID.randomUUID() + "-" + file.filename();
        log.info("Preparing to upload file '{}' to S3 bucket '{}'", file.filename(), bucket);

        return DataBufferUtils.join(file.content())
                .flatMap(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer);

                    return Mono.fromCallable(() -> {
                        PutObjectRequest request = PutObjectRequest.builder()
                                .bucket(bucket)
                                .key(key)
                                .contentType(Objects.requireNonNull(file.headers().getContentType()).toString())
                                .build();

                        s3Client.putObject(request, RequestBody.fromBytes(bytes));

                        String url = "https://" + bucket + ".s3." + region + ".amazonaws.com/" + key;
                        log.info("File '{}' uploaded successfully to S3 at URL: {}", file.filename(), url);

                        return url;
                    }).subscribeOn(Schedulers.boundedElastic());
                });
    }
}
