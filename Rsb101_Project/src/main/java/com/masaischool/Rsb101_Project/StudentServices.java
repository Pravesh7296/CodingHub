package com.masaischool.Rsb101_Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.internal.build.AllowSysOut;

public class StudentServices {
	
//////////////////////////color//////////////
public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_YELLOW = "\u001B[33m";
public static final String ANSI_GREEN = "\u001B[32m";
public static final String ANSI_PURPLE = "\u001B[34m";
public static final String ANSI_BLUE = "\u001B[35m";
public static final String ANSI_RED = "\u001B[31m";
/////////////////////////////////////////////////////////

////////////////////////Student SignUp///////////////////////////////////////
static void StudentSignUp(String user,String password,String Course) {
EntityManager em = App.getEntMn();
EntityTransaction ts=em.getTransaction();

try {
Query que =em.createQuery("Select a from Student_details a");
int count=0;
List<Student_details> add = que.getResultList();
for(Student_details ad : add) {
if(ad.getStudent_Name().equals(user)) {
count++;
}
}
if(count==1) {
System.out.println(ANSI_YELLOW+user+ANSI_BLUE+"  You already Registered please Login"+ANSI_RESET);
}else {
Student_details ad = new Student_details();
ad.setStudent_Name(password);
ad.setStudent_pass(password);
ad.setStudent_Course(Course);
ts.begin();

em.persist(ad);
ts.commit();
System.out.println(ANSI_BLUE+"Thankyou " +ANSI_YELLOW+user+ANSI_BLUE+ " For SignUp"+ANSI_RESET);
}

} catch (Exception e) {
System.out.println(ANSI_RED+e.getMessage());
}finally {
em.close();
}
}



//////////////////////////////StudentLogin/////////////////////////////////////	

static void StudentLogIn(String user,String password) {
EntityManager em = App.getEntMn();
EntityTransaction ts = em.getTransaction();

try {
ts.begin();
Query que = em.createQuery("Select a from Student_details a where Student_Name=:adname");
que.setParameter("adname",user);

Student_details ad = (Student_details) que.getSingleResult();
if(ad.getStudent_Name().equals(user) && ad.getStudent_pass().equals(password)) {
System.out.println(ANSI_BLUE+"Login Successfully"+ANSI_RESET); 


StudentFun(user,ad.getStudent_Id());
}else {
System.out.println(ANSI_RED+"Enter Right Details"+ANSI_RESET);
}

ts.commit();
} catch (Exception e) {
System.out.println(ANSI_RED+"Student Not Found"+ANSI_RESET);
}finally {
em.close();
}
}
///////////////////////////////////Student Function///////////////////////////
static void StudentFun(String name,int User_Id) {
Scanner sc = new Scanner(System.in);
int choice;
do {
System.out.println(ANSI_BLUE+" ---------------------------");
System.out.println(" |   Welcome : "+ANSI_YELLOW+name+ANSI_BLUE+"      |");
System.out.println(" ---------------------------"+ANSI_RESET);
System.out.println(ANSI_YELLOW+"ENTER : 1 FOR view All assignment and their Status");
System.out.println("ENTER : 2 FOR  Done your Assginment");
System.out.println("ENTER : 0 FOR LogOut from Student Account"+ANSI_RESET);
choice=sc.nextInt();
sc.nextLine();
switch(choice) {
case 1:
viewAll_Assignment(sc,User_Id);
break;
case 2:
Done_Assignment(sc,User_Id);
break;

case 0:
System.out.println(ANSI_BLUE+"thankyou Admin"+ANSI_RESET);
break;
default:
System.out.println(ANSI_RED+"Enter valid Selection"+ANSI_RESET);
}

}while(choice!=0);

}
////////////////////////////VeiwAll Assignment////////////////////////////////////////
static void viewAll_Assignment(Scanner sc,int User_Id){
EntityManager em = App.getEntMn();
EntityTransaction ts  = em.getTransaction();

try {
ts.begin();
   Query InsIds = em.createQuery("Select r from Instructor_details r where Instructor_Course=:course");
   InsIds.setParameter("course",em.find(Student_details.class,User_Id).getStudent_Course());
   List<Instructor_details> Ins = InsIds.getResultList();
   List<Assignment_details> ls = new ArrayList();
   for(Instructor_details ins : Ins) {
	   for(Assignment_details as : ins.getList()) {
		   ls.add(as);
	   }
   }
    
if(ls.size()==0) {
System.out.println(ANSI_RED+"Not Found ! "+ANSI_BLUE+"(No Assignments issued)"+ANSI_RESET);
}else {
	List<Assignment_details> sdList = em.find(Student_details.class,User_Id).getAssignment();
	System.out.println(ANSI_YELLOW+"Your Solved Assignment"+ANSI_RESET);
	sdList.stream().forEach(rd->System.out.println(ANSI_GREEN+"Assignment_Type : "+ANSI_YELLOW+rd.getAssignment_Type()+ANSI_GREEN+" Assignment_Id : "+ANSI_YELLOW+rd.getAssignment_Id()+ANSI_GREEN+" Assignment_Name  : "+ANSI_YELLOW+rd.getAssignment_Name()+ANSI_GREEN+"  Total_marks of Assignment : "+ANSI_YELLOW+rd.getTotal_marks()+ANSI_GREEN+"  Status of Assignment : "+ANSI_RED+"Completed"+ANSI_RESET));
	System.out.println(ANSI_YELLOW+"Your Unsolved Assignment"+ANSI_RESET);

	for(Assignment_details rd : ls){
		boolean flag=true;
		for(Assignment_details sd : sdList) {
			if(rd.getAssignment_Id()==sd.getAssignment_Id()) {
				flag =false;
				break;
			}
		}
		if(flag==true) {
			System.out.println(ANSI_GREEN+"Assignment_Type : "+ANSI_YELLOW+rd.getAssignment_Type()+ANSI_GREEN+" Assignment_Id : "+ANSI_YELLOW+rd.getAssignment_Id()+ANSI_GREEN+" Assignment_Name  : "+ANSI_YELLOW+rd.getAssignment_Name()+ANSI_GREEN+"  Total_marks of Assignment : "+ANSI_YELLOW+rd.getTotal_marks()+ANSI_GREEN+"  Status of Assignment : "+ANSI_RED+"Not Completed"+ANSI_RESET);
	
		}
}
}
ts.commit();

} catch (Exception e) {
System.out.println(ANSI_RED+e.getMessage()+ANSI_RESET);
}finally {
em.close();
}
}    
////////////////////////////Done Your Assignment////////////////////////////////////////
static void Done_Assignment(Scanner sc,int User_Id){
EntityManager em = App.getEntMn();
EntityTransaction ts  = em.getTransaction();

try {
ts.begin();
Query InsIds = em.createQuery("Select r from Instructor_details r where Instructor_Course=:course");
InsIds.setParameter("course",em.find(Student_details.class,User_Id).getStudent_Course());
List<Instructor_details> Ins = InsIds.getResultList();
List<Assignment_details> ls = new ArrayList();
for(Instructor_details ins : Ins) {
	   for(Assignment_details as : ins.getList()) {
		   ls.add(as);
	   }
}
if(ls.size()==0) {
System.out.println(ANSI_RED+"Not Found ! "+ANSI_BLUE+"(No Assignments issued)"+ANSI_RESET);
}else {

  System.out.println(ANSI_YELLOW+"Enter Assignment Id"+ANSI_RESET);
  int id = sc.nextInt();
  
 if(ls.stream().anyMatch(t-> (id)==(t.getAssignment_Id()))) {
	Student_details st = em.find(Student_details.class,User_Id);
	Assignment_details ad = em.find(Assignment_details.class,id);
	List<Student_details> als = ad.getStudent();
    
	if(st.getAssignment().stream().anyMatch(t->(id)==(t.getAssignment_Id()))) {
	  System.out.println(ANSI_GREEN+"You already Sloved Assignment "+ANSI_RESET);
	}else {
	List<Assignment_details> stList = st.getAssignment();
	stList.add(ad);
	st.setAssignment(stList);
	em.persist(st);
	als.add(st);
	ad.setStudent(als);
	int sub =ad.getSubmission();
	ad.setSubmission(sub+1);
	em.persist(ad);
	ts.commit();
	System.out.println(ANSI_GREEN+"Assignment Submitted"+ANSI_RESET);
	}
 }else {
	System.out.println(ANSI_RED+"Please Enter Right id"+ANSI_RESET);
 }
}


} catch (Exception e) {
System.out.println(ANSI_RED+e.getMessage()+ANSI_RESET);
}finally {
em.close();
}
}
}
