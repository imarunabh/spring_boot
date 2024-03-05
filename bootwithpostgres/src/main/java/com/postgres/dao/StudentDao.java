package com.postgres.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	//create the table
	public void createTable() {
		var query="CREATE TABLE student(id serial PRIMARY KEY, name varchar(255)NOT NULL,city VARCHAR(255))";
		int update = this.jdbcTemplate.update(query);
		System.out.println(update);
		
	}
	
	//inserting data to db
	public void insertData(String name,String city) {
		
		String query="insert into student (name,city) values(?,?)";
		int update = this.jdbcTemplate.update(query,name,city);
		System.out.println(update+" rows affected");
		
		
	}

}
