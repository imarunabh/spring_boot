package com.file.demo.fieldemo.service;

import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface FileService {

	String uploadImage(String path,MultipartFile file);
	InputStream getResource(String path,String fileName);
	
}
