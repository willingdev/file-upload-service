package com.cpn.fileuploadapi.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NfsStorageServiceTest {
    private StorageProperties properties = new StorageProperties();
    private NfsStorageService service;

    @BeforeEach
    public void init() {
        properties.setLocation("target/files/" + Math.abs(new Random().nextLong()));
        service = new NfsStorageService(properties);
        service.init();
    }

    @Test
    void store() {
        service.store(new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
                "Hello, World".getBytes()));
        assertThat(service.load("foo.txt")).exists();

    }
}