package com.masaischool.Rsb101_Project;

import java.util.*;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.persistence.*;

import org.hibernate.internal.build.AllowSysOut;

public class Admin_Services {
//////////////////////////color//////////////
public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_YELLOW = "\u001B[33m";
public static final String ANSI_GREEN = "\u001B[32m";
public static final String ANSI_PURPLE = "\u001B[34m";
public static final String ANSI_BLUE = "\u001B[35m";
public static final String ANSI_RED = "\u001B[31m";
/////////////////////////////////////////////////////////


////////////////////////Admin LogIn///////////////////////////////////////
	static void AdminLogIn(String user,String password) {
		EntityManager em = App.getEntMn();
		EntityTransaction ts=em.getTransaction();
		
		try {
		 
			if(user.equals("Admin") && password.equals("12345")) {
				System.out.println(ANSI_BLUE+"Thankyou " +ANSI_YELLOW+user+ANSI_BLUE+ " For LogIn"+ANSI_RESET);
                  Admin_Services.AdminFun();
			}else {
				System.out.println(ANSI_YELLOW+"Enter right Credential"+ANSI_RESET);

			}
			
		} catch (Exception e) {
			System.out.println(ANSI_RED+e.getMessage());
		}finally {
			em.close();
		}
		
		
		
	}
	

///////////////////////////////////Admin's Function///////////////////////////
   static void AdminFun() {
         Scanner sc = new Scanner(System.in);
	   int choice;
	   do {
     System.out.println(ANSI_BLUE+" ---------------------------");
		      System.out.println(" |   Welcome : "+ANSI_YELLOW+"Admin"+ANSI_BLUE+"      |");
		      System.out.println(" ---------------------------"+ANSI_RESET);
		 	  System.out.println(ANSI_YELLOW+"ENTER : 1 FOR view All Instructors");
		 	  System.out.println("ENTER : 2 FOR View All Students");
		 	  System.out.println("ENTER : 3 FOR Delete Instructor");
		 	  System.out.println("ENTER : 4 FOR Delete Student");
		 	  System.out.println("ENTER : 5 FOR View All Assignment");
		 	  System.out.println("ENTER : 0 FOR LogOut from Admin Account"+ANSI_RESET);
		 	 choice=sc.nextInt();
		 	 sc.nextLine();
		 	 switch(choice) {
		 	 case 1:
		 		 View_Instructor(sc);
		 		 break;
		 	 case 2:
		 		 View_Students(sc);
		 		 break;
		 	 case 3:
		 		 Delete_Instructor(sc);
		 		 break;
		 	 case 4:
		 		Delete_Students(sc);
		 		 break;
		 	 case 5:
			 		viewAll_Assignment(sc);
			 		 break;
		 	 case 0:
		 		 System.out.println(ANSI_BLUE+"thankyou Admin"+ANSI_RESET);
		 		 break;
		 		 default:
		 			 System.out.println(ANSI_RED+"Enter valid Selection"+ANSI_RESET);
		 	 }
		 	 
	   }while(choice!=0);
	 
   }
/////////////////////////////view All Instructor/////////////////////////////////////
   static void View_Instructor(Scanner sc) {
	 EntityManager em = App.getEntMn();
	 EntityTransaction ts  = em.getTransaction();
	 Query query = em.createQuery("Select a from Instructor_details a");
	 List<Instructor_details>  ls =query.getResultList();
	 if(ls.size()==0) {
		 System.out.println(ANSI_RED+"No Instructor Available !"+ANSI_RED);
	 }else {
	 ls.stream().forEach(t->System.out.println(ANSI_YELLOW+"Instructor Id : "+t.getInstructor_Id()+" || Instructor Name : "+t.getInstructor_Name()+"Instructor Course : ||"+t.getInstructor_Course()+ANSI_RESET));
	 }
   }  
  /////////////////////////View All Assignment/////////////////////
   static void viewAll_Assignment(Scanner sc) {
	 EntityManager em = App.getEntMn();
	 EntityTransaction ts  = em.getTransaction();
	 Query query = em.createQuery("Select a from Assignment_details a");
	 List<Assignment_details>  ls =query.getResultList();
	 if(ls.size()==0) {
		 System.out.println(ANSI_RED+"No Assignment Available !"+ANSI_RED);
	 }else {
	 ls.stream().forEach(t->System.out.println(ANSI_YELLOW+"Assignment_Type : "+t.getAssignment_Type()+"Assignment Id : "+t.getAssignment_Id()+" || Assignment Name : "+t.getAssignment_Name()+"Assignment Total_marks : ||"+t.getTotal_marks()+"Assignment Submission  : "+t.getSubmission()+  ANSI_RESET));
	 }
   }  
////////////////////////View Student////////////////////////////////////////
   static void View_Students(Scanner sc){
	   EntityManager em = App.getEntMn();
		 EntityTransaction ts  = em.getTransaction();
		 Query query = em.createQuery("Select a from Student_details a");
		 List<Student_details>  ls =query.getResultList();
		 if(ls.size()==0) {
			 System.out.println(ANSI_RED+"No Student Available !"+ANSI_RESET);
		 }else {
			 for(Student_details t : ls) {
 System.out.println(ANSI_YELLOW+"Student Id : "+t.getStudent_Id()+" || Student Name : "+t.getStudent_Name()+" || Student Course : "+t.getStudent_Course()+ANSI_RESET);	 
			List<Assignment_details> als = t.getAssignment();
			
			
		System.out.println(ANSI_YELLOW+"|| Student Submission : "+als.size()+ANSI_RESET);	
			 }
//		ls.stream().forEach(t->System.out.println(ANSI_YELLOW+"Student Id : "+t.getStudent_Id()+"Student Name : "+t.getStudent_Name()+"Student Course : "+t.getStudent_Course()));	 
	
		 }	
   }
////////////////////////Delete Instructor////////////////////////////////////////
   static void Delete_Instructor(Scanner sc){
		 EntityManager em = App.getEntMn();
		 EntityTransaction ts  = em.getTransaction();
		 
	    System.out.println(ANSI_BLUE+"Enter Instructor_Id"+ANSI_RESET);
	      int Instructor_Id = sc.nextInt();
	      sc.nextLine();
	      if(em.find(Instructor_details.class, Instructor_Id)==null) {
	    	 System.out.println(ANSI_RED+"No Instructor Available !"+ANSI_RESET);
	      }else {
	    	  
	      
	      try {
	    	  ts.begin();
	    	  Query que = em.createNativeQuery("Delete from Instructor_details_Assignment_details  where Instructor_details_Instructor_Id=:rId");
			  que.setParameter("rId",Instructor_Id);
	          
			  List<Integer> asIds = em.find(Instructor_details.class,Instructor_Id).getList().stream().map(t->t.getAssignment_Id()).collect(Collectors.toList());
	          Query asq  = em.createNativeQuery("Delete from assignment_details_student_details where assignment_Assignment_Id=:rId");
	          for(Integer i : asIds) {
	        	  asq.setParameter("rId",i);
	        	  asq.executeUpdate();
	          }
	          
	    	  Query qq = em.createNativeQuery("Delete from Assignment_details  where Instructor_Id=:rId");
			  qq.setParameter("rId",Instructor_Id);
			  
	    	  
			  
	    	  Query qu = em.createQuery("Delete from Instructor_details  where Instructor_Id=:rId");
			  qu.setParameter("rId",Instructor_Id);

			  if(que.executeUpdate()>0 || qq.executeUpdate()>0 || qu.executeUpdate()>0) {
				que.executeUpdate();
				qq.executeUpdate();
				qu.executeUpdate();
				  System.out.println(ANSI_RED+"Instructor Deleted"+ANSI_RESET);
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
   }      
////////////////////////////Delete Student////////////////////////////////////////
   static void Delete_Students(Scanner sc){
	   EntityManager em = App.getEntMn();
		 EntityTransaction ts  = em.getTransaction();
		 
	    System.out.println(ANSI_BLUE+"Enter Student_Id"+ANSI_RESET);
	      int Student_Id = sc.nextInt();
	      sc.nextLine();
	      if(em.find(Student_details.class,Student_Id)==null) {
	    	  System.out.println(ANSI_RED+"No Student Available !"+ANSI_RESET);
	      }else {
	    	  
	     
	      try {
	    	  ts.begin();
	    	  if(em.find(Student_details.class,Student_Id).getAssignment().size()==0) {
	    		  Query qu = em.createNativeQuery("Delete from Student_details where Student_Id=:rId");
				  qu.setParameter("rId",Student_Id);

				  if(qu.executeUpdate()>0) {
					  System.out.println(ANSI_RED+"Student Deleted"+ANSI_RESET);
				  }else {
					  System.out.println(ANSI_RED+"somethingWent wrong"+ANSI_RESET);
				  }
	    	  }else {
	    		  Query que = em.createNativeQuery("Delete from assignment_details_student_details where student_Student_Id=:rId");
				  que.setParameter("rId",Student_Id);
				  
		    	  Query qu = em.createNativeQuery("Delete from Student_details where Student_Id=:rId");
				  qu.setParameter("rId",Student_Id);

				  if(que.executeUpdate()>0 && qu.executeUpdate()>0) {
					  System.out.println(ANSI_RED+"Student Deleted"+ANSI_RESET);
				  }else {
					  System.out.println(ANSI_RED+"somethingWent wrong"+ANSI_RESET);
				  }
	    	  }
	      
	    	
			  ts.commit();
		      
		} catch (Exception e) {
		    System.out.println(ANSI_RED+e.getMessage()+ANSI_RESET);
		}finally {
			em.close();
		} 
 }      }
}
