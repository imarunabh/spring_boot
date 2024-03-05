package com.lcwd.root.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcwd.root.model.User;


@RestController
@RequestMapping("/users")
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private ObjectMapper mapper;
	
	@PostMapping
	public ResponseEntity<?> addUserInformation(@RequestParam("file")  MultipartFile file,@RequestParam("userData") String userData){
		
		this.logger.info("add user request");
		logger.info("File information{}",file.getOriginalFilename());
		//logger.info("user : {}",userData);
		
		//converting String into JSON
		User user = null;
		try {
			user=mapper.readValue(userData, User.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
		}
		
		//file save
		//user save
		this.logger.info("User data is : {}",user);
		return ResponseEntity.ok("done");
		
	}

}
