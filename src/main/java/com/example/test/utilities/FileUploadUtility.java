package com.example.test.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadUtility
{
    private static final Logger log = LoggerFactory.getLogger(FileUploadUtility.class);
    private final String FILE_UPLOAD_PATH = "F:\\file_upload_path";

    public boolean uploadFile(MultipartFile multipartFile)
    {
        log.info("Inside FileUploadUtility :: uploadFile() ::");
        try{
            // old way to store files
            /*InputStream inputStream = multipartFile.getInputStream();
            byte[] data = inputStream.readAllBytes();
            FileOutputStream outputStream = new FileOutputStream(FILE_UPLOAD_PATH+"\\"+multipartFile.getOriginalFilename());
            outputStream.write(data);
            outputStream.flush();*/

            // new way to store files
            Files.copy(multipartFile.getInputStream(), Paths.get(FILE_UPLOAD_PATH+"\\"+multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            return true;

        }catch (Exception e){
            log.error("Exception occurred while writing file!\nException e = ",e);
            return false;
        }
    }

}
