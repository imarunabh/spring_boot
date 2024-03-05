package com.file.demo.fieldemo.service.impl;

import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.file.demo.fieldemo.service.FileService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class FileServiceImpl implements FileService  {
	
//	@Autowired
//	private FileService fileService;
//	
//	@Value("${project.image}")
//	private String path;
	
	
	@Override
	public String uploadImage(String path,MultipartFile file)  {
		//File name
		String name = file.getOriginalFilename();
		
		//generating random name
		String randomID = UUID.randomUUID().toString();
		String fileName1= randomID.concat(name.substring(name.lastIndexOf(".")));
		
		
		//Fullpath
		String filePath=path+File.separator+fileName1;
		
		
		//create folder if not created
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		//file copy
		
		try {
			Files.copy(file.getInputStream(), Paths.get(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return name;
		
	}

	@Override
	public InputStream getResource(String path, String fileName) {
		String fullPath = path + File.separator + fileName;
		InputStream is=null;
		try {
			is = new FileInputStream(fullPath);
			//db logic to return InputStream
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		return is;
	}
	
	

}
