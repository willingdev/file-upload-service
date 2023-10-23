package com.cpn.fileuploadapi.storage;

import com.cpn.fileuploadapi.storage.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NfsStorageService implements StorageService {
    @Override
    public void store(MultipartFile file) {

    }
}
