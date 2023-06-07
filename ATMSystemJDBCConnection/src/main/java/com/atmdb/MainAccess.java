package com.atmdb;

import java.sql.SQLException;
import java.util.Scanner;

import atm.exception.GlobalException;

public class MainAccess {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, GlobalException {
		//Menu
		int ch=0;
		Scanner sc=new Scanner(System.in);
		
		System.out.println("**************************************************\n(*_*) Welcome To SBI ...(*_*) \n ***************************************************");
		do {
			
			System.out.println("__________________________");
			System.out.println("1.Admin");
			System.out.println("2.Customer");
			System.out.println("3.Exit");
	
			System.out.println("___________________________");
			
			ch=sc.nextInt();sc.nextLine();
			
			
			switch(ch) {
			case 1:
						System.out.println("Login");
						
						System.out.println("enter the Admin UserName");
						String un=sc.nextLine();
						System.out.println("enter the Admin Password");
						String pass=sc.nextLine();
						if(un.equalsIgnoreCase("Admin") & pass.equals("Admin123"))
						{
							System.out.println("************************\n Login Successfully...\n*************************");
							//AtmMainApp.main(args);
							AdminMain.main(args);
						}else
						{
							System.out.println("***********************\n Invalid Login...\n************************");
						}
						
			         break;
			case 2:
				
				System.out.println("Select");
				System.out.println("1.Register");
				System.out.println("2.Login");
				int ch1=sc.nextInt();
				
					switch(ch1)
					{
				
						case 1:
							System.out.println("Register");sc.nextLine();
							System.out.println("enter the UserName");
							String un1=sc.nextLine();
							System.out.println("enter the Password");
							String pass1=sc.nextLine();
							
							CustomerLoginRegister.Register(un1,pass1);
							  break;
							         
						case 2:
								System.out.println("Login");sc.nextLine();
						         System.out.println("enter the UserName");
								String u=sc.nextLine();
								System.out.println("enter the Password");
								String p=sc.nextLine();
									
								boolean flag=CustomerLoginRegister.login(u,p);
								if(flag==true)
								{
									AtmMainApp.main(args);
								}
								else {
									System.out.println("******************************\n Invalid UserName and Password\n*********************************");
								}
						         break;
					}//switch
				break;         
				case 3:
					System.out.println("**********************************\n Thank You For Using our Service\n********************************");
						System.exit(0);
						break;
	        }//switch
			 
		}while(true);
		
       
	}//main

		}
	
