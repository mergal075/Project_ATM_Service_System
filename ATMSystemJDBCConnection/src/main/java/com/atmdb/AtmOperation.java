package com.atmdb;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AtmOperation {

	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
	private static int accno;
	static {
		try {
			conn=DatabaseConnection.getDatabaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static Scanner sc=new Scanner(System.in);
	
	
	
	//insert the record in table
	
	public static void addAtm() throws SQLException , atm.exception.GlobalException    {
		Scanner sc=new Scanner(System.in);
		
		int accEx=1,balEx=1,pinEx=1;
		st=conn.createStatement();
		System.out.println("Enter the AccountNo : ");
		 accno=sc.nextInt();sc.nextLine();
		 
		 if(accno<999){
				accEx=0;
				}
			if(accEx==0)
			{
				try
				{
				throw new atm.exception.GlobalException("*************************************\n Invalid Account No \n**************************");
				}catch(atm.exception.GlobalException ne)
				{
					System.out.println(ne.getMessage());
				}
				addAtm();
			}	
		 
			
			System.out.println("enter the account holder Name :");
			String name=sc.nextLine();
			
			System.out.println("enter the ATM balance: ");
			int balance=sc.nextInt();
			
			if(balance>=100 && balance%100!=0)
			{
				balEx=0;
			}
			if(balEx==0)
			{
				try
				{
				throw new atm.exception.GlobalException("*************************************\n Balance Should be in 100 Range\n***************************");
				
				}
				catch(atm.exception.GlobalException ne)
				{
					System.out.println(ne.getMessage());
					
				}
				//addAtm();
			}
			
			System.out.println("enter the ATM PIN: ");
			int pin=sc.nextInt();sc.nextLine();
			
			if(pin>1 && pin<=999  )
			{
				pinEx=0;
			}else if(pin>9999   )
			{
				
				pinEx=0;
			}
			if(pinEx==0)
			{
				try
				{
				throw new atm.exception.GlobalException("*************************************\n Pin Should be 4 digit\n***************************");
				
				}
				catch(atm.exception.GlobalException ne)
				{
					System.out.println(ne.getMessage());
					
				}
				addAtm();
			}
			
			
			
			System.out.println("enter the account TYPE: ");
			String acctype=sc.nextLine();
			String s1="insert into atm values("+accno+",'"+name+"',"+balance+","+pin+",'"+acctype+"')"; 
			int res=st.executeUpdate(s1);
			if(res>0) {
			System.out.println("*************************************\n record successfully inserted.....!!!\n*****************************");}
		
		
	}
	
	//withdraw cash
	
	public static void Withdraw() throws SQLException {
		Scanner sc=new Scanner(System.in);
		int accEx=1;
		st=conn.createStatement();
		System.out.println("Enter the AccountNo : ");
		 accno=sc.nextInt();
		 
		 //checking eaccount no is valid or not
		 if(accno<999){
				accEx=0;
				}
			if(accEx==0)
			{
				try
				{
				throw new atm.exception.GlobalException("*************************************\n Invalid Account No \n**************************");
				}catch(atm.exception.GlobalException ne)
				{
					System.out.println(ne.getMessage());
				}
				Withdraw();
			}	
		 
		 System.out.println("enter Your ATM PIN");
		 int pin=sc.nextInt();
		 int pinEx=1;
		 if(pin>1 && pin<=999  )
			{
				pinEx=0;
			}else if(pin>9999   )
			{
				
				pinEx=0;
			}
			if(pinEx==0)
			{
				try
				{
				throw new atm.exception.GlobalException(" *************************************\n Pin Should be 4 digit\n***************************");
				
				}
				catch(atm.exception.GlobalException ne)
				{
					System.out.println(ne.getMessage());
					
				}
				Withdraw();
			}
		 
		 
		 st=conn.createStatement();
		 String sql="select * from atm where accno="+accno+" and pin="+pin  ;
		// "+accno+",'"+   +",pin="+pin
		 rs=st.executeQuery(sql);
		 if(rs.next())//if record exists go for withdraw the money
			{
				
				System.out.println("enter the amount to withdraw");//ask user withdrawal
				int amt=sc.nextInt();
				int balEx=1;
				if(amt%100!=0)
				{
					balEx=0;
				}
				if(balEx==0)
				{
					try
					{
					throw new atm.exception.GlobalException("*************************************\n amount Should be in 100 Range\n***************************");
					
					}
					catch(atm.exception.GlobalException ne)
					{
						System.out.println(ne.getMessage());
						
					}
					Withdraw();
				}
				
				int balance=rs.getInt("balance");
				
				if(amt<=balance)
				{
					balance-=amt; 
					String s1="update atm set balance='"+balance+"' where accno="+accno ;
					//for mini statement
					
					SimpleDateFormat form=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					Date date=new Date();
					String status="withdraw";
					String d=form.format(date);
					int mamt=amt;
					String s2="insert into mininfo  values('"+status+"','"+d+"',"+mamt+","+accno+")"; 
					
					int res2=st.executeUpdate(s2);
					
					
					//System.out.println("Date:              :"+form.format(date));
					
					
					
					int res=st.executeUpdate(s1);
					if(res>0) {//how many records are updated
					System.out.println("************************************************\n amount successfully withdrawned\n**********************************");}
				}else {
					System.out.println("**********************************************\n Insufficient Balance.....!! \n***************************************************");
				}
			}
			else {
				System.out.println("*************************************\n record does not exists\n*************************************");
			}
		 
		
		
		
	}
	
	//Check The Balance
	public static void CheckBalance() throws SQLException {
		System.out.println("Enter Account No to fecth the record");
		accno=sc.nextInt();
		int accEx=1;		
		
		 if(accno<999){
				accEx=0;
				}
			if(accEx==0)
			{
				try
				{
				throw new atm.exception.GlobalException("*************************************\n Invalid Account No \n**************************");
				}catch(atm.exception.GlobalException ne)
				{
					System.out.println(ne.getMessage());
				}
				CheckBalance();
			}	
		System.out.println("enter a PIN:");
		int pin=sc.nextInt();
			
			
			  int pinEx=1;
		 if(pin>1 && pin<=999  )
			{
				pinEx=0;
			}else if(pin>9999   )
			{
				
				pinEx=0;
			}
			if(pinEx==0)
			{
				try
				{
				throw new atm.exception.GlobalException("Pin Should be 4 digit\n***************************");
				
				}
				catch(atm.exception.GlobalException ne)
				{
					System.out.println(ne.getMessage());
					
				}
				CheckBalance();
			}
			 
			
			
			
		st=conn.createStatement();
		String sql="select * from atm where accno="+accno+" and pin="+pin  ;
		
		 rs=st.executeQuery(sql);
		 
		 if(rs.next()) {
			 System.out.println("*************************************\n Your Available Balance is Ruppes: "+rs.getInt("balance")+" .00 Rs.\n*************************************");
		 }else {
			 System.out.println("*************************************\n Your Account  "+accno+" not exists \n *************************************");
		 }
		
	}
	
	//generating pin
	public static void GeneratePin() throws SQLException {
		Scanner sc=new Scanner(System.in);
		st=conn.createStatement();
		System.out.println("Enter the AccountNo : ");
		 accno=sc.nextInt();
		 System.out.println("Enter the PIN");
		 int p=sc.nextInt();
		 st=conn.createStatement();
		String sql="select * from atm where accno="+accno+" and pin="+p ;
		rs=st.executeQuery(sql);
		if(rs.next()) {
			System.out.println("enter Your New ATM PIN");
			 int pin=sc.nextInt();
			 
			 
			 int pinEx=1;
			 if(pin>1 && pin<=999  )
				{
					pinEx=0;
				}else if(pin>9999   )
				{
					
					pinEx=0;
				}
				if(pinEx==0)
				{
					try
					{
					throw new atm.exception.GlobalException("*************************************\n Pin Should be 4 digit\n***************************");
					
					}
					catch(atm.exception.GlobalException ne)
					{
						System.out.println(ne.getMessage());
						
					}
					GeneratePin();
				}
			 
			 
			 String s1="update atm set pin='"+pin+"' where accno="+accno ;
				
				int res=st.executeUpdate(s1);
				if(res>0) {//how many records are updated
				System.out.println("*************************************\n PIN Generated Successfully .....!!!");}
			 
			
		}else {
			 System.out.println("*************************************\n Your Account  "+accno+" not exists\n*************************************");
		 } 
		
	}

	//Deposit
	
	public static void Deposit() throws SQLException {
		Scanner sc=new Scanner(System.in);
		int accEx=1;
		st=conn.createStatement();
		System.out.println("Enter the AccountNo : ");
		 accno=sc.nextInt();
		 
		 //checking eaccount no is valid or not
		 if(accno<999){
				accEx=0;
				}
			if(accEx==0)
			{
				try
				{
				throw new atm.exception.GlobalException("*************************************\n Invalid Account No \n**************************");
				}catch(atm.exception.GlobalException ne)
				{
					System.out.println(ne.getMessage());
				}
				Withdraw();
			}	
		 
		 System.out.println("enter Your ATM PIN");
		 int pin=sc.nextInt();
		 int pinEx=1;
		 if(pin>1 && pin<=999  )
			{
				pinEx=0;
			}else if(pin>9999   )
			{
				
				pinEx=0;
			}
			if(pinEx==0)
			{
				try
				{
				throw new atm.exception.GlobalException(" *************************************\n Pin Should be 4 digit\n***************************");
				
				}
				catch(atm.exception.GlobalException ne)
				{
					System.out.println(ne.getMessage());
					
				}
				Deposit();
			}
		 
		 
		 st=conn.createStatement();
		 String sql="select * from atm where accno="+accno+" and pin="+pin  ;
		 rs=st.executeQuery(sql);
		 if(rs.next())//if record exists go for withdraw the money
			{
				
				System.out.println("enter the amount to deposited");//ask user withdrawal
				int amt=sc.nextInt();
				int balEx=1;
				if(amt%100!=0)
				{
					balEx=0;
				}
				if(balEx==0)
				{
					try
					{
					throw new atm.exception.GlobalException("*************************************\n amount Should be in 100 Range\n***************************");
					
					}
					catch(atm.exception.GlobalException ne)
					{
						System.out.println(ne.getMessage());
						
					}
					Deposit();
				}
				
				int balance=rs.getInt("balance");
				
				if(amt<=50000)
				{
					balance+=amt; 
					String s1="update atm set balance='"+balance+"' where accno="+accno ;
					
					
					//for mini statement
					
					
					SimpleDateFormat form=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					Date date=new Date();
					String status="Deposit";
					String d=form.format(date);
					int mamt=amt;
					String s2="insert into mininfo  values('"+status+"','"+d+"',"+mamt+","+accno+")"; 
					
					int res2=st.executeUpdate(s2);
					
					
					
					
					int res=st.executeUpdate(s1);
					if(res>0) {//how many records are updated
					System.out.println("*************************************\n amount successfully deposited\n**********************************");}
				}else
				{
					System.out.println("******************************************************\n Sorry We can Not Proceed it....! Amount should no be grater than 50000 \n**********************************");	
				}
			}
			else {
				System.out.println("*************************************\n record does not exists\n*************************************");
			}
		 
		
		
		
	}


//Get Mini Statements details
	public static void miniStatements() throws SQLException {
		
		System.out.println("Enter Account No to fecth MiniStatements");
		accno=sc.nextInt();
		//System.out.println("enter  Your ATM PIN");
		//int p
		
		st=conn.createStatement();
		String sql="select * from atmview where accno="+accno;
		
		 rs=st.executeQuery(sql);
		 
		 if(rs.next()) {
			    System.out.println("Enter ATM PIN");
				int pin=sc.nextInt();
				
				 int pinEx=1;
				 if(pin>1 && pin<=999  )
					{
						pinEx=0;
					}else if(pin>9999   )
					{
						
						pinEx=0;
					}
					if(pinEx==0)
					{
						try
						{
						throw new atm.exception.GlobalException("************************\nPin Should be 4 digit\n***************************");
						
						}
						catch(atm.exception.GlobalException ne)
						{
							System.out.println(ne.getMessage());
							
						}
						miniStatements();
					}
				
				
				String q="select * from atmview where accno="+accno  ;
				 rs=st.executeQuery(q);
				 System.out.println("*******MINI STATEMENT DETAILS************");
				 
				 int flag=0;
				 
				 
				 System.out.println("_______________________________________________");
				 while(rs.next()) {
					 if(flag==0)
					 {
						 System.out.println("Account Number      :"+rs.getInt("accno"));
						 System.out.println("Account Holder Name :"+rs.getString("accHolderName"));
						 System.out.println();
						 System.out.println("__________________________________________");
						 System.out.println("Status  \t date \t\t AMount");
						 System.out.println("__________________________________________");
						 flag++;
					 }
					 System.out.println(rs.getString("Status")+" | "+rs.getString("date")+" | "+rs.getInt("Amount"));
					
				 }/*else
				 {
					 System.out.println("*************************************\nYou enteren Wrong ATM PIN\n*************************************");
				 }*/
				
				
			 
		 }else {
			 System.out.println("*************************************\nYour Account  "+accno+" not exists\\n*************************************");
		 }
		
		
	}

	
		
		

		
}
