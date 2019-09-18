package com.revature.dao;

import com.revature.util.IncorrectPasswordException;
import com.revature.util.OverdraftException;
import com.revature.util.UnknownUserException;

public interface BankDAO {
	//OPTION 1.1, LOG IN
	public void LogIn();
	//OPTION 1.2, REGISTER
	//public void CreateUser(); 
	
	//CHECKS IF USERNAME && PASSWORD ARE IN THE DB, RETURNS USER_ID
	//CHECKS IF SUPER USER BY MATCHING WITH connection.properties
	//SUPER USER WILL RETURN 0, 
	//ALL VALID USERS WILL RETURN POSITIVE INTEGERS
	//INVALID USERS WILL RETURN -1
	public int Validate(String USERNAME, String PASSWORD) throws IncorrectPasswordException, UnknownUserException;
	//OPTION 2
	//FOLLOWS AFTER VALIDATE, 0 WILL GRANT SUPER USER ACCES
	//POSITIVE INTEGER, WILL GRANT NORMAL USER ACCESS
	//-1, WILL GRANT OPTION TO LogIn() AGAIN OR GO BACK TO MAIN MENU
	public void Option(int KEY_ID);
	//OPTION 2.1 MENU, SUPER USER ACCESS
	public void SuperOption();
	//OPTION 2.2 MENU, NORMAL USER ACCESS
	public void UserOption(int KEY_ID);
	//ALL OPTION MENUS WILL LOOP, LOG OUT IS IMPLEMENTED WITH SWITCH CASES
	//AND CASE = '0'
	
	//BEGIN THINKING OF IMPLEMENTATION, CREATE OBJECT TO BE PASSED TO AND FROM DB
	//PARAMTERS CAN BE CHANGED
	//OPTION 2.1
	//CREATE A USER
	public void CreateUser(); 
	//UPDATE A USER
	public void UpdateUser(); 
	//DELETE A USER
	public void DeleteUser(); 
	//VIEW A USER
	public void ViewUser();	
	
	//OPTION 2.2
	//CREATE AN ACCOUNT
	public void CreateAccount(int USER_ID); 
	//VIEW AN ACCOUNT (PLURAL)
	public void ViewAccounts(int USER_ID);
	//DELETE AN ACCOUNT, IF EMPTY
	public void DeleteAccount(int USER_ID); 
	//UPDATE AN ACCOUNT, DEPOSIT/WITHDRAW
	//GIVE OPTION TO WITHDRAW OR DEPOSIT, IF WITHDRAW PASS NEGATIVE NUMBER
	public void UpdateAccount(int USER_ID) throws OverdraftException; 
	public void ViewTransactions(int USER_ID); 
}
