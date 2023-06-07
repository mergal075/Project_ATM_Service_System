package com.atmdb;

import java.sql.SQLException;
import java.util.Scanner;

import atm.exception.GlobalException;


public class AtmMainApp {

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
			System.out.println("_______________________");
			ch=sc.nextInt();sc.nextLine();
			
			
			switch(ch) {
			case 1:
					AtmOperation.addAtm();
			         break;
			case 2:
					AtmOperation.GeneratePin();
			         break;
			case 3:
					AtmOperation.Deposit();
			         break;
			case 4:
					AtmOperation.Withdraw();
					break;
					
			case 5:
					AtmOperation.CheckBalance();
					break;
			case 6:
					AtmOperation.miniStatements();
					break;
	        }//switch
			System.out.println("Do you want to continue . Press n to stop and any other key to continue");
			 char dch=sc.next().charAt(0);
			 if(dch=='n' || dch=='N') {
				 break;
			 }
		}while(true);
		
       
	}//main

}
