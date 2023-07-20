package com.masaischool.Rsb101_Project;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Instructor_details {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Instructor_Id;
    private String Instructor_Name;
    private  String Instructor_pass;
    private String Instructor_Course;
    
   @OneToMany
   private List<Assignment_details> list= new ArrayList();

   public Instructor_details() {
	   
   }

public Instructor_details(int instructor_Id, String instructor_Name, String instructor_pass, String instructor_Course,
		List<Assignment_details> list) {
	super();
	Instructor_Id = instructor_Id;
	Instructor_Name = instructor_Name;
	Instructor_pass = instructor_pass;
	Instructor_Course = instructor_Course;
	this.list = list;
}


public String getInstructor_Name() {
	return Instructor_Name;
}

public void setInstructor_Name(String instructor_Name) {
	Instructor_Name = instructor_Name;
}

public String getInstructor_pass() {
	return Instructor_pass;
}

public void setInstructor_pass(String instructor_pass) {
	Instructor_pass = instructor_pass;
}

public String getInstructor_Course() {
	return Instructor_Course;
}

public void setInstructor_Course(String instructor_Course) {
	Instructor_Course = instructor_Course;
}

public List<Assignment_details> getList() {
	return list;
}

public void setList(List<Assignment_details> list) {
	this.list = list;
}

public int getInstructor_Id() {
	return Instructor_Id;
}

@Override
public String toString() {
	return "Instructor_details [Instructor_Id=" + Instructor_Id + ", Instructor_Name=" + Instructor_Name
			+ ", Instructor_pass=" + Instructor_pass + ", Instructor_Course=" + Instructor_Course + ", list=" + list
			+ "]";
}
   
   
}