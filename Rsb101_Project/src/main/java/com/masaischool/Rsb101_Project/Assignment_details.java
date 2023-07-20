package com.masaischool.Rsb101_Project;



import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity 
public class Assignment_details {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int Assignment_Id;
private String Assignment_Name;
private String Assignment_Type;
private LocalDate CreatedDate;
private LocalDate EndDate;
private int Total_marks;
private boolean Completed;
private int Submission ;

@ManyToOne
@JoinColumn(name="Instructor_Id")
private Instructor_details Ind;
@ManyToMany
private List<Student_details> student;

public Assignment_details() {
	
}


public Assignment_details(int assignment_Id, String assignment_Name, String assignment_Type, LocalDate createdDate,
		LocalDate endDate, int total_marks, boolean completed, Instructor_details ind,int Submission,List<Student_details> student) {
	super();
	Assignment_Id = assignment_Id;
	Assignment_Name = assignment_Name;
	Assignment_Type = assignment_Type;
	CreatedDate = createdDate;
	EndDate = endDate;
	Total_marks = total_marks;
	Completed = completed;
	Ind = ind;
	this.Submission =  Submission;
	this.student=student;
}


public String getAssignment_Name() {
	return Assignment_Name;
}


public List<Student_details> getStudent() {
	return student;
}


public void setStudent(List<Student_details> student) {
	this.student = student;
}


public void setAssignment_Name(String assignment_Name) {
	Assignment_Name = assignment_Name;
}


public String getAssignment_Type() {
	return Assignment_Type;
}


public void setAssignment_Type(String assignment_Type) {
	Assignment_Type = assignment_Type;
}


public LocalDate getCreatedDate() {
	return CreatedDate;
}


public void setCreatedDate(LocalDate createdDate) {
	CreatedDate = createdDate;
}


public LocalDate getEndDate() {
	return EndDate;
}


public void setEndDate(LocalDate endDate) {
	EndDate = endDate;
}


public int getTotal_marks() {
	return Total_marks;
}


public void setTotal_marks(int total_marks) {
	this.Total_marks = total_marks;
}
public int getSubmission() {
	return Submission;
}


public void setSubmission(int Submission) {
	this.Submission = Submission;
}

public boolean isCompleted() {
	return Completed;
}


public void setCompleted(boolean completed) {
	Completed = completed;
}


public Instructor_details getInd() {
	return Ind;
}


public void setInd(Instructor_details ind) {
	Ind = ind;
}


public int getAssignment_Id() {
	return Assignment_Id;
}


@Override
public String toString() {
	return "Assignment_details [Assignment_Id=" + Assignment_Id + ", Assignment_Name=" + Assignment_Name
			+ ", Assignment_Type=" + Assignment_Type + ", CreatedDate=" + CreatedDate + ", EndDate=" + EndDate
			+ ", Total_marks=" + Total_marks + ", Completed=" + Completed + ", Ind=" + Ind + "]";
}


}

