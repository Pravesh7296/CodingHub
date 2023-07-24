package com.masaischool.Rsb101_Project;



import java.util.*;


import javax.persistence.*;



public class App {
	//////////////////////////color//////////////
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_PURPLE = "\u001B[34m";
	public static final String ANSI_BLUE = "\u001B[35m";
	public static final String ANSI_RED = "\u001B[31m";
/////////////////////////////////////////////////////////
	
	static EntityManager getEntMn() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("masaiUnit");
		return emf.createEntityManager();
	}

    public static void main( String[] args ) {
    	EntityManager em = getEntMn();
        Scanner sc = new Scanner(System.in);
        int choice=0;
        do {
        	try {
        		System.out.println(ANSI_PURPLE+"************************");
        		            System.out.println("* Welcome to Coding_Hub   *");
        		 
        		System.out.println("************************");
        		System.out.println(ANSI_YELLOW+"ENTER : 1 For Admin LogIn");
            	System.out.println("ENTER : 2 For Instructor signUp");
            	System.out.println("ENTER : 3 For Instructor Login");
            	System.out.println("ENTER : 4 For Student SignUp");
            	System.out.println("ENTER : 5 For Student Login");
            	System.out.println("ENTER : 0 For Exit"+ANSI_RESET);
            	choice = sc.nextInt();
			} catch (Exception e) {
				System.out.println(ANSI_RED+"Enter right Input"+ANSI_RESET);
				
			}finally {
				
			}
        	
        	
        	
        	switch(choice) {
        	case 1:
        		MainInterface.AdminLogIn(sc);
        		break;
        	case 2:
        		MainInterface.InstructorSignUp(sc);
        		break;
        	case 3:
        	MainInterface.InstructorLogin(sc);
        		break;
        	case 4:
        		MainInterface.StudentSigUp(sc);
        		break;
        	case 5:
        		MainInterface.StudentLogin(sc);
        		break;
        	case 0:
        		System.out.println(ANSI_GREEN+"Thankyou for Testing"+ANSI_RESET);
        		break;
        		default:
        			System.out.println(ANSI_GREEN+"sorry Enter valid Selection !"+ANSI_RESET);
        	}
        	
        }while(choice!=0);
    }
}
