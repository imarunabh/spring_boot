package com.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jdbc.dao.UserDao;

@SpringBootApplication
public class BootjdbcexampleApplication implements CommandLineRunner{
	
	@Autowired
	private UserDao userDao;

	public static void main(String[] args) {
		SpringApplication.run(BootjdbcexampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.userDao.createTable());
		this.createUser();
		
		//creating user
		
		
	}
	
	public void createUser() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter user ID");
		Integer userID= Integer.parseInt(br.readLine());
		
		System.out.println("Enter user Name");
		String name = br.readLine();
		
		System.out.println("Enter user Age");
		Integer age= Integer.parseInt(br.readLine());
		
		System.out.println("Enter user City");
		String city = br.readLine();
		
		
		int i = this.userDao.insertUser(userID, name, age, city);
		System.out.println(i+ " user added");
		
		
		
	}
	

}
