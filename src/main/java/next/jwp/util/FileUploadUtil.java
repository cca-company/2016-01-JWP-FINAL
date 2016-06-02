package next.jwp.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component("FileUtil")
public class FileUploadUtil {
    private static final String uploadPath = "uploads/";
    
    public String saveFile(MultipartFile file, String root, String prefix){
        String originalFileName = file.getOriginalFilename();
        String fileName = prefix + "_" + getRandomFileName() + getFileExtension(originalFileName);
        uploadFile(file, root, fileName);
        return "/"+uploadPath + fileName;
    }
    
    private void uploadFile(MultipartFile file, String root, String fileName){
        BufferedOutputStream stream;
        try {
            stream = new BufferedOutputStream(
                    new FileOutputStream(new File(root + uploadPath + fileName)));
            FileCopyUtils.copy(file.getInputStream(), stream);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String getRandomFileName(){
        return Long.toString(System.currentTimeMillis());
    }
    
    private String getFileExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
