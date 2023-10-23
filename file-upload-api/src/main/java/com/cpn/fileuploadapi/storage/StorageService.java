package com.cpn.fileuploadapi.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void store(MultipartFile file);

}
