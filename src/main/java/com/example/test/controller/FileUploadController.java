package com.example.test.controller;

import com.example.test.utilities.FileUploadUtility;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FileUploadController
{
    @Autowired
    private FileUploadUtility fileUploadUtility;
    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @PostMapping("/file-upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        log.info(" :: Inside uploadFile :: ");
        if(file==null || file.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Kindly upload a File!");
        }
        log.info(":: file-content-type ::"+file.getContentType());
        List<String> allowedContentType = new ArrayList<>();
        allowedContentType.add("image/png");
        allowedContentType.add("image/jpeg");
        allowedContentType.add("image/jpg");
        if(!allowedContentType.contains(file.getContentType())){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid file extension!\nJPEG/JPG/PNG files are allowed.");
        }
        log.info(":: File Size (in KB)::"+file.getSize()/1024.0);

        boolean isSuccess = fileUploadUtility.uploadFile(file);
        if(isSuccess){
            return ResponseEntity.status(HttpStatus.OK).body("File has been successfully uploaded!!!");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("File upload - FAILED");
        }

    }

}
