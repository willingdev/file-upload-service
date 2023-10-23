package com.cpn.fileuploadapi.fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    @PostMapping("/")
    public FileUploadResponse handleFileUpload(@RequestParam("file") MultipartFile file) {

        return new FileUploadResponse();
    }
}
