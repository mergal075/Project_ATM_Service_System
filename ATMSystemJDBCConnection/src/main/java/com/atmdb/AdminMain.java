package com.atmdb;

import java.sql.SQLException;
import java.util.Scanner;

import atm.exception.GlobalException;

public class AdminMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, GlobalException {
		//Menu
		int ch=0;
		Scanner sc=new Scanner(System.in);
		do {
			System.out.println("ATM Operations");
			System.out.println("__________________________");
			System.out.println("1.add The ATM Details");
			System.out.println("2.Generate the PIN");
			System.out.println("3.Deposit The Money");
			System.out.println("4.Withdraw The Money");
			System.out.println("5.Check The Balance");
			System.out.println("6.mini statement ");
			System.out.println("7.Display all  Customer");
			System.out.println("8.Update Customer Details");
			System.out.println("9.Exit");
			System.out.println("_______________________");
			ch=sc.nextInt();sc.nextLine();
			
			
			switch(ch) {
			case 1:
				AdminAccess.addAtm();
			         break;
			case 2:
				AdminAccess.GeneratePin();
			         break;
			case 3:
				AdminAccess.Deposit();
			         break;
			case 4:
				AdminAccess.Withdraw();
					break;
					
			case 5:
				AdminAccess.CheckBalance();
					break;
			case 6:
					AdminAccess.miniStatements();
					break;
			case 7:
				AdminAccess.DisplayAllCustomer();
				break;
			case 8:
				AdminAccess.UpdateAtm();
				break;
			case 9:
				System.out.println("**********************************\n Thank You For Using our Service\n********************************");
				System.exit(0);
			}//switch
			System.out.println("Do you want to continue . Press n to stop and any other key to continue");
			System.out.println("_____________________________________________________________________________");
			 char dch=sc.next().charAt(0);
			 if(dch=='n' || dch=='N') {
				 break;
			 }
		}while(true);
		
       
	}//main


}
