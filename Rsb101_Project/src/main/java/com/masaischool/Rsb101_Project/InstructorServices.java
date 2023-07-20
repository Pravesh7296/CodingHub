package com.masaischool.Rsb101_Project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class InstructorServices {
	
//////////////////////////color//////////////
public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_YELLOW = "\u001B[33m";
public static final String ANSI_GREEN = "\u001B[32m";
public static final String ANSI_PURPLE = "\u001B[34m";
public static final String ANSI_BLUE = "\u001B[35m";
public static final String ANSI_RED = "\u001B[31m";
/////////////////////////////////////////////////////////
	
////////////////////////Instructor SignUp///////////////////////////////////////
static void InstructorSignUp(String user,String password,String Course) {
EntityManager em = App.getEntMn();
EntityTransaction ts=em.getTransaction();

try {
Query que =em.createQuery("Select a from Instructor_details a");
int count=0;
List<Instructor_details> add = que.getResultList();
for(Instructor_details ad : add) {
if(ad.getInstructor_Name().equals(user)) {
count++;
}
}
if(count==1) {
System.out.println(ANSI_YELLOW+user+ANSI_BLUE+"  You already Registered please Login"+ANSI_RESET);
}else {
Instructor_details ad = new Instructor_details();
ad.setInstructor_Name(user);
ad.setInstructor_pass(password);
ad.setInstructor_Course(Course);
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



//////////////////////////////InstructorLogin/////////////////////////////////////	

static void InstructorLogIn(String user,String password) {
EntityManager em = App.getEntMn();
EntityTransaction ts = em.getTransaction();

try {
ts.begin();
Query que = em.createQuery("Select a from Instructor_details a where Instructor_Name=:adname");
que.setParameter("adname",user);

Instructor_details ad = (Instructor_details) que.getSingleResult();
if(ad.getInstructor_Name().equals(user) && ad.getInstructor_pass().equals(password)) {
System.out.println(ANSI_BLUE+"Login Successfully"+ANSI_RESET); 
InstructorFun(user,ad.getInstructor_Id());
}else {
System.out.println(ANSI_RED+"Enter Right Details"+ANSI_RESET);
}

ts.commit();
} catch (Exception e) {
System.out.println(ANSI_RED+"Admin Not Found"+ANSI_RESET);
}finally {
em.close();
}
}
///////////////////////////////////Instructor's Function///////////////////////////
static void InstructorFun(String name,int User_Id) {
Scanner sc = new Scanner(System.in);
int choice;
do {
System.out.println(ANSI_BLUE+" ---------------------------");
System.out.println(" |   Welcome : "+ANSI_YELLOW+name+ANSI_BLUE+"      |");
System.out.println(" ---------------------------"+ANSI_RESET);
System.out.println(ANSI_YELLOW+"ENTER : 1 FOR Add new assignment");
System.out.println("ENTER : 2 FOR Update assignment");
System.out.println("ENTER : 3 FOR Delete assignment");
System.out.println("ENTER : 4 FOR View assignment and their progress");
System.out.println("ENTER : 0 FOR LogOut from Admin Account"+ANSI_RESET);
choice=sc.nextInt();
sc.nextLine();
switch(choice) {
case 1:
AddNewAssignment(sc,User_Id);
break;
case 2:
UpdateAssignment(sc,User_Id);
break;
case 3:
DeleteAssignment(sc,User_Id);
break;
case 4:
ViewProgress(sc,User_Id);
break;
case 0:
System.out.println(ANSI_BLUE+"thankyou Admin"+ANSI_RESET);
break;
default:
System.out.println(ANSI_RED+"Enter valid Selection"+ANSI_RESET);
}

}while(choice!=0);

}
/////////////////////////////AddNewAssignment/////////////////////////////////////
static void AddNewAssignment(Scanner sc,int User_Id) {
	
	
EntityManager em = App.getEntMn();
EntityTransaction ts  = em.getTransaction();

System.out.println(ANSI_BLUE+"Enter Assginment_Name"+ANSI_RESET);
String Assignment_name = sc.next();
sc.nextLine();

System.out.println(ANSI_BLUE+"Enter Assignment_Type"+ANSI_RESET);
String Assignment_Type = sc.nextLine();
System.out.println(ANSI_BLUE+"Enter End_Date (YYYY-MM-YY) "+ANSI_RESET);
LocalDate End_Date =LocalDate.parse(sc.next());

System.out.println(ANSI_BLUE+"Enter Total_marks"+ANSI_RESET);
int Total_marks = sc.nextInt();
sc.nextLine();
boolean Completed =false;
LocalDate CreatedDate = LocalDate.now();
try {


ts.begin();
Assignment_details rd = new Assignment_details();
rd.setAssignment_Name(Assignment_name);
rd.setAssignment_Type(Assignment_Type);
rd.setCompleted(Completed);
rd.setCreatedDate(CreatedDate);
rd.setEndDate(End_Date);
rd.setTotal_marks(Total_marks);


if(End_Date.compareTo(CreatedDate)>0) {
	em.persist(rd);
	Instructor_details ad = em.find(Instructor_details.class,User_Id);

	List<Assignment_details> ls = ad.getList();
	
	
	
	ls.add(rd);
	ad.setList(ls);

	em.persist(ad);
	rd.setInd(ad);
	em.persist(rd);
	ts.commit();

	System.out.println(ANSI_BLUE+"Assignment_added"+ANSI_RESET);
	}else {
	System.out.println(ANSI_RED+"END_DATE must be bigger than Created DATE"+ANSI_RESET);
}



} catch (Exception e) {
System.out.println(ANSI_RED+e.getMessage()+ANSI_RESET);
}finally {
em.close();
}
}  
////////////////////////Update Assignment////////////////////////////////////////
static void UpdateAssignment(Scanner sc,int User_Id){
EntityManager em = App.getEntMn();
EntityTransaction ts  = em.getTransaction();

System.out.println(ANSI_BLUE+"Enter Assignment_Id"+ANSI_RESET);
int Assignment_Id = sc.nextInt();
sc.nextLine();
Query que = em.createQuery("Select r from Assignment_details r where Assignment_Id=:rId and Instructor_Id=:insId");
que.setParameter("rId",Assignment_Id);
que.setParameter("insId",User_Id);
try {
	Assignment_details rd = (Assignment_details) que.getSingleResult();

	System.out.println(ANSI_YELLOW+"ENTER : 1 FOR Update Name");
	System.out.println("ENTER : 2 FOR Update Total_marks"); 
	System.out.println("ENTER : 3 FOR Update Assignment_Type"); 
	System.out.println("ENTER : 4 FOR Update End_Date"+ANSI_RESET); 
	int ch;
	try {
	ch = sc.nextInt();
	} catch (Exception e) {
	ch=5;

	}

	sc.nextLine();
	if(ch==1){
	System.out.println(ANSI_BLUE+"Enter New Name"+ANSI_RESET); 
	String name = sc.next();
	rd.setAssignment_Name(name);
	}else if(ch==2) {
	System.out.println(ANSI_BLUE+"Enter Total_marks"+ANSI_RESET); 
	int Total_marks = sc.nextInt();
	sc.nextLine();
	rd.setTotal_marks(Total_marks);
	}else if (ch==3) {
	System.out.println(ANSI_BLUE+"Enter Assignment_Type"+ANSI_RESET); 
	String Assignment_Type = sc.nextLine();
	rd.setAssignment_Type(Assignment_Type);
	}else if (ch==4) {
		System.out.println(ANSI_BLUE+"Enter End_Date (YYYY-MM-DD) "+ANSI_RESET); 
		LocalDate End_Date= LocalDate.parse(sc.next());
		
		if((End_Date.compareTo(LocalDate.now())>0)) {
		rd.setEndDate(End_Date);
		}else {
			System.out.println(ANSI_RED+"End Date must be bigger than today date"+ANSI_RESET);
		}
	}

	else {
	System.out.println(ANSI_RED+"Enter right Selection"+ANSI_RESET);
	}

	try {
	ts.begin();
	em.persist(rd);
	
	ts.commit();
	System.out.println(ANSI_BLUE+"Assignment updated successfully"+ANSI_RESET);
	} catch (Exception e) {
	System.out.println(ANSI_RED+e.getMessage()+ANSI_RESET);
	}finally {
	em.close();
	}
} catch (Exception e) {
	// TODO: handle exception
	System.out.println(ANSI_RED+"Not found Assignment !"+ANSI_RESET);
}

}

////////////////////////Delete Assignment////////////////////////////////////////
static void DeleteAssignment(Scanner sc,int User_Id){
EntityManager em = App.getEntMn();
EntityTransaction ts  = em.getTransaction();

System.out.println(ANSI_BLUE+"Enter Assignment_Id"+ANSI_RESET);
int Assignment_Id = sc.nextInt();
sc.nextLine();
try {
ts.begin();


Query que = em.createNativeQuery("Delete from Instructor_details_Assignment_details where List_Assignment_Id=:rId and Instructor_details_Instructor_Id=:insId");
que.setParameter("rId",Assignment_Id);
que.setParameter("insId",User_Id);

Query qu = em.createNativeQuery("Delete from Assignment_details where Assignment_Id=:rId");
qu.setParameter("rId",Assignment_Id);



if(que.executeUpdate()>0 && qu.executeUpdate()>0) {
System.out.println(ANSI_RED+"Assignment Deleted"+ANSI_RESET);
}else {
System.out.println(ANSI_RED+"somethingWent wrong"+ANSI_RESET);
}
ts.commit();

} catch (Exception e) {
System.out.println(ANSI_RED+e.getMessage()+ANSI_RESET);
}finally {
em.close();
}
}      
////////////////////////////VeiwProgress////////////////////////////////////////
static void ViewProgress(Scanner sc,int User_Id){
EntityManager em = App.getEntMn();
EntityTransaction ts  = em.getTransaction();

try {
ts.begin();
Query que = em.createQuery("Select r from Assignment_details r where Instructor_Id=:id");
que.setParameter("id",User_Id);
List<Assignment_details> ls = que.getResultList();
if(ls.size()==0) {
System.out.println(ANSI_RED+"Not Found ! "+ANSI_BLUE+"(please Add Assignments)"+ANSI_RESET);
}else {
for(Assignment_details rd : ls) {
System.err.println(ANSI_GREEN+"Assignment_Id : "+ANSI_YELLOW+rd.getAssignment_Id()+ANSI_GREEN+" Assignment_Name  : "+ANSI_YELLOW+rd.getAssignment_Name()+ANSI_GREEN+"  Submission of Assignment : "+ANSI_RED+rd.getSubmission()+ANSI_RESET);
}
}
ts.commit();

} catch (Exception e) {
System.out.println(ANSI_RED+e.getMessage()+ANSI_RESET);
}finally {
em.close();
}
}      

}   


