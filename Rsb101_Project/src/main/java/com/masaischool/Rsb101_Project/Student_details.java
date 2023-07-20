package com.masaischool.Rsb101_Project;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Student_details {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int Student_Id;
	    private String Student_Name;
	    private String Student_pass;
	    @Column(nullable = false)
	    private String Student_Course;
	    
	    @ManyToMany(mappedBy="student",cascade = CascadeType.ALL)
	    private List<Assignment_details> assignment;
	    
	    
	    public Student_details() {
	    	
	    }
		public Student_details(int student_Id, String student_Name, String student_pass, String student_Course,List<Assignment_details> assignment) {
			super();
			Student_Id = student_Id;
			Student_Name = student_Name;
			Student_pass = student_pass;
			Student_Course = student_Course;
			this.assignment=assignment;
		}
		
		public List<Assignment_details> getAssignment() {
			return assignment;
		}
		public void setAssignment(List<Assignment_details> assignment) {
			this.assignment = assignment;
		}
		public String getStudent_Name() {
			return Student_Name;
		}
		public void setStudent_Name(String student_Name) {
			Student_Name = student_Name;
		}
		public String getStudent_pass() {
			return Student_pass;
		}
		public void setStudent_pass(String student_pass) {
			Student_pass = student_pass;
		}
		public String getStudent_Course() {
			return Student_Course;
		}
		public void setStudent_Course(String student_Course) {
			Student_Course = student_Course;
		}
		public int getStudent_Id() {
			return Student_Id;
		}
		@Override
		public String toString() {
			return "Student_details [Student_Id=" + Student_Id + ", Student_Name=" + Student_Name + ", Student_pass="
					+ Student_pass + ", Student_Course=" + Student_Course + "]";
		}
		
	    
	 
	   
	   
	}

