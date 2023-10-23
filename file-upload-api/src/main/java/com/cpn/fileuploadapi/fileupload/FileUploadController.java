package com.cpn.fileuploadapi.fileupload;

import com.cpn.fileuploadapi.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponse> handleFileUpload(@RequestParam("file") MultipartFile file) {
        storageService.store(file);
          return ResponseEntity.ok(new FileUploadResponse());
    }
}
