package com.lcwd.orm.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="jpa_student")
public class Student {
	
	@Id
	private int studentId;
	private String studentName;
	private String about;
	
	@OneToOne(mappedBy="student")
	private Laptop laptop;

}
