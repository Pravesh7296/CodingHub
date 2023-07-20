package com.masaischool.Rsb101_Project;



import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public class MainInterface {
//////////////////////////color//////////////
public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_YELLOW = "\u001B[33m";
public static final String ANSI_GREEN = "\u001B[32m";
public static final String ANSI_PURPLE = "\u001B[34m";
public static final String ANSI_BLUE = "\u001B[35m";
public static final String ANSI_RED = "\u001B[31m";
/////////////////////////////////////////////////////////


////////////////////////Admin LogIn///////////////////////////////////
	static void AdminLogIn(Scanner sc) {
		  System.out.println(ANSI_GREEN+"Enter User Name"+ANSI_RESET);
	      String user = sc.next();
	      System.out.println(ANSI_GREEN+"Enter Password"+ANSI_RESET);
	      String password = sc.next();
	      Admin_Services.AdminLogIn(user,password); 
	}

	
	
////////////////////////Instructor signUp///////////////////////////////////
static void InstructorSignUp(Scanner sc) {
System.out.println(ANSI_GREEN+"Enter User Name"+ANSI_RESET);
String user = sc.next();
System.out.println(ANSI_GREEN+"Enter Password"+ANSI_RESET);
String password = sc.next();
System.out.println(ANSI_GREEN+"Enter Course"+ANSI_RESET);
String Course = sc.next();
InstructorServices.InstructorSignUp(user, password,Course);
}  


////////////////////////////InstructorLogin/////////////////////////////////////	
 
	static void InstructorLogin(Scanner sc) {
      System.out.println(ANSI_GREEN+"Enter User Name"+ANSI_RESET);
      String user = sc.next();
      System.out.println(ANSI_GREEN+"Enter Password"+ANSI_RESET);
      String password = sc.next();
      InstructorServices.InstructorLogIn(user, password);
      }
///////////////////StudentSigUp/////////////////////////////////////
      static void StudentSigUp(Scanner sc) {
    	  try {
    		  System.out.println(ANSI_GREEN+"Enter Student Name"+ANSI_RESET);
    			  
    		  
              String user = sc.next();
              System.out.println(ANSI_GREEN+"Enter Password"+ANSI_RESET);
              String password = sc.next();
              System.out.println(ANSI_GREEN+"Enter Course"+ANSI_RESET);
              String Course = sc.next();
              StudentServices.StudentSignUp(user, password,Course);
		} catch (Exception e) {
			System.out.println(ANSI_RED+e.getMessage()+ANSI_RESET);
		}
         
          }
/////////////////////StudentLogin//////////////////////////////////////////
      static void StudentLogin(Scanner sc) {
    	  try {
    		  System.out.println(ANSI_GREEN+"Enter Student Name"+ANSI_RESET);
              String user = sc.next();
              System.out.println(ANSI_GREEN+"Enter Password"+ANSI_RESET);
              String password = sc.next();
              StudentServices.StudentLogIn(user, password);
		} catch (Exception e) {
			System.out.println(ANSI_RED+e.getMessage()+ANSI_RESET);
		}
      }
}

