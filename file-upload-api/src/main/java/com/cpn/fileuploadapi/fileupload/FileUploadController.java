package com.cpn.fileuploadapi.fileupload;

import com.cpn.fileuploadapi.email.EmailService;
import com.cpn.fileuploadapi.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Controller
public class FileUploadController {

    @Autowired
    private StorageService storageService;
    @Autowired
    private EmailService emailService;
    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponse> handleFileUpload(@RequestParam("file") MultipartFile file,@RequestHeader Map<String, String> headers) {
        storageService.store(file);
        CompletableFuture.runAsync(()->{
            String email = headers.get("x-authenticated-userid");
            emailService.sendSimpleMessage(email,"Uploaded File Status",file.getName()+  " has beed uploaded.");
        });
          return ResponseEntity.ok(new FileUploadResponse());
    }

}
