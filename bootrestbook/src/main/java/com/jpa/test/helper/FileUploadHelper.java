package com.jpa.test.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
	
//	public final  String UPLOAD_DIR="C:\\Users\\Arunabh\\Documents\\"
//			+ "workspace-spring-tool-suite-4-4.20.0.RELEASE\\bootrestbook\\src"
//			+ "\\main\\resources\\static\\image";
	
	public final String UPLOAD_DIR= new ClassPathResource("static/image/").getFile().getAbsolutePath();
	
	public FileUploadHelper() throws IOException{
		
	}
	public boolean uploadFile(MultipartFile mulitpartFile) {
		boolean f = false;
		
		try {
			
//			InputStream is= mulitpartFile.getInputStream();
//			byte data[]=new byte[is.available()];
//			is.read(data);
//			
//			//write
//			FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+File.separator+mulitpartFile.getOriginalFilename());
//			fos.write(data);
//			fos.flush();
//			fos.close();
			Files.copy(mulitpartFile.getInputStream(),Paths.get(UPLOAD_DIR+File.separator+mulitpartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			f=true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return f;
		
	}
	

}
