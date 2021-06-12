/***
 =================
Name: Nicole Wilbur

Project Name: CSC372-CTA01 -- PORTFOLIO MILESTONE #1 Corrections

4/11/21 updates: comments along side code.
	Summary, corrected spelling mistake, set upper limit on transaction amount, 
	added validation loop for first and last names, fixed bug in checkbalance loop.
==================
 ***/

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Iterator;

public class ABCBankSystem {
	
	//control loop for main menu in main
	public static int mainLoop (Scanner scnr) {
		int userNeeds;
		do {
			try {
			System.out.println("Enter 1 to add an account, enter 2 to deposit, enter 3 to withdraw, "
				+ "enter 4 to get balance, enter 5 to print all account information, and enter 6 to exit.");
			userNeeds = scnr.nextInt();
			if (userNeeds >= 1 && userNeeds <= 6) break;
			}
			catch(InputMismatchException e) {
				System.out.println("Please enter a valid option.");
			}
			finally {
				scnr.nextLine();
			}
			System.out.println("Invalid entry. Please enter a number between 1 and 6.");
		}
		while(true);
		return userNeeds;
	}
	//handles user input for deposit or withdrawal amounts                  //4-11-21 fixed all the withdrawal spelling mistakes
	public static double depositwithdrawalValidation(Scanner scnr) {
		System.out.println("Enter the deposit or withdrawal amount: ");
		double amountEntered = scnr.nextDouble();
		while (amountEntered > 10000.00) {                                 //4-11-21 sets an upper limit on transaction amount
			System.out.println ("ABC Bank has a per transaction limit of 10,000. Please enter a smaller amount.");
			amountEntered = scnr.nextDouble();
		}
		while (amountEntered < 0) {
			System.out.println("Please enter a valid positive dollar amount.");
		    amountEntered = scnr.nextDouble(); 
		}
		return amountEntered;
	}
	//handles user input for accountID
	public static int accountLoop(Scanner scnr) {
		System.out.println("Enter an account number: ");
		int accountID = scnr.nextInt();
		while (accountID < 0) {
			System.out.println("Please enter a valid account ID number.");
		    accountID = scnr.nextInt(); 
		}
		return accountID;
	}
	//handles user input for adding another account menu
	public static int addAnother (Scanner scnr) {
		System.out.println("Type 1 to make another transaction or 2 to exit to the main menu: "); 
		int addAnother = scnr.nextInt(); 
		while (!(addAnother == 1 || addAnother == 2)) {
			System.out.println("Please enter a valid option."); 
			addAnother = scnr.nextInt(); 
			} 
		return addAnother;
	  }
	//method for adding accounts
	public static void addAccount(ArrayList<BankAccount> accountListings) {
		String returnResult;
		Scanner scnr = new Scanner(System.in);
		int accountType;
		try {
			do {
				System.out.println("Enter 1 to add a savings account, enter 2 to add a checking account.");
				accountType = scnr.nextInt();
				while (!(accountType == 1 || accountType == 2)) {
					System.out.println("Please enter a valid option.");
					accountType = scnr.nextInt();
				}
				scnr.nextLine();
				System.out.println("Enter the first name for the account: ");
				String firstName = scnr.nextLine();
				while (firstName.length() < 1) {									//4-11-21 added validation for firstName
					System.out.println("Please enter a first name at least one character long.");
					firstName = scnr.nextLine();
				}
				System.out.println("Enter the last name for the account: ");
				String lastName = scnr.nextLine();
				while (lastName.length() < 1) {                                     //4-11-21 added validation for lastName
					System.out.println("Please enter a last name at least one character long.");
					lastName = scnr.nextLine();
				}
				int accountID = accountLoop(scnr);
				if (accountType == 2) {
					System.out.println("Enter the interest rate for this checking account as a decimal value");
					double interestRate = scnr.nextDouble();
					while (interestRate > 1.00 || interestRate < 0.00) {
						System.out.println("Please enter a valid decimal value.");
						interestRate = scnr.nextDouble();
					}
					System.out.println("Enter the overdraft fee for this account. Overdraft fees for ABC bank are"
							+ " whole dollar amounts between $10 and $40.");
					int overdraftFee = scnr.nextInt();
					while (overdraftFee < 10 || overdraftFee > 40) {
						System.out.println ("Please enter a valid whole number amount.");
						overdraftFee = scnr.nextInt();
					}
					CheckingAccount personalChecking = new CheckingAccount(firstName, lastName, accountID, interestRate, overdraftFee);
					accountListings.add(personalChecking);
					printAccounts(accountListings);
				}
				else {
					BankAccount personalSavings = new BankAccount(firstName, lastName, accountID);
					accountListings.add(personalSavings);
					printAccounts(accountListings);
				}
			} while (addAnother(scnr) == 1);
			returnResult = "Success";
		}
		catch (Exception Fail){
			returnResult = "Failed";
		}
		System.out.println("Account added: " + returnResult);
	}
	//method for making deposits
	public static void makeDeposit(ArrayList<BankAccount> accountListings) {
		String returnResult;
		try {
			Scanner scnr = new Scanner(System.in);
			do {
				double amountEntered = depositwithdrawalValidation(scnr);
				
				Iterator<BankAccount> accountIterator = accountListings.iterator();
				
				while (accountIterator.hasNext()) {
					BankAccount account = accountIterator.next();
		            if (account.getAccountID() == accountLoop(scnr)) {
		                account.deposit(amountEntered);   
		            	System.out.println("Deposit successful.");	                    
		                break; 
		            }
		            else {
		                System.out.println("Account ID not Found."); 
		            }
		        }
			    printAccounts(accountListings);
			}
			while (addAnother(scnr) == 1);
			returnResult = "Success";
		}
		catch (Exception fail) {
			returnResult = "Failed";
		}
		System.out.println("Deposit status: " + returnResult);
	}
	//method for making withdrawals
	public static void makewithdrawal(ArrayList<BankAccount> accountListings) {
		String returnResult;
		try {
			Scanner scnr = new Scanner(System.in);
			do {
				double amountEntered = depositwithdrawalValidation(scnr);
				
				Iterator<BankAccount> accountIterator = accountListings.iterator();
				
				while (accountIterator.hasNext()) {
					BankAccount account = accountIterator.next();
		            if (account.getAccountID() == accountLoop(scnr)) {
		                account.withdrawal(amountEntered);   
		            	System.out.println("withdrawal successful.");	                    
		                break; 
		            }
		            else {
		                System.out.println("Account ID not Found."); 
		            }
		        }
			    printAccounts(accountListings);
			}
			while (addAnother(scnr) == 1);
			returnResult = "Success";
		}
		catch (Exception fail) {
			returnResult = "Failed";
		}
		System.out.println("withdrawal status: " + returnResult);
	}
	//method to check balance of a specific account
	public static void checkBalance(ArrayList<BankAccount> accountListings) { //4-11-21 fixed the bug in this module
		String returnResult;
		try {
			Scanner scnr = new Scanner(System.in);
			
			do {
				System.out.println("Please enter a valid account number:");
				int accountID = scnr.nextInt();
				Iterator<BankAccount> accountIterator = accountListings.iterator();
				while (accountIterator.hasNext()) {
					BankAccount account = accountIterator.next();
		            if (account.getAccountID() ==  accountID) {  
		            	System.out.println("The balance on account " + account.getAccountID() + " is " 
		                                   + account.getBalance() + " dollars.");	                    
		                break; 
		            }
		        }
			}
			while (addAnother(scnr) == 1);
			returnResult = "Success";
		}
		catch (Exception fail) {
			returnResult = "Failed";
		}
		System.out.println("Check balance status: " + returnResult);
	}
	//method to print all account information for all accounts
	public static void printAccounts(ArrayList<BankAccount> accountListings) {
		String returnResult;
		try {
			System.out.println("===================================================================");
			for (int i = 0; i < accountListings.size(); ++i) {
				BankAccount objectAccount = accountListings.get(i);
				System.out.println("");
				System.out.println("Account" + (i + 1));
				System.out.println(objectAccount.accountSummary());
				System.out.println("");
			}
			System.out.println("===================================================================");
			returnResult = "Success";
		}
		catch (Exception failed) {
			returnResult = "Failed";
		}
		System.out.println("Account listings print to screen status: " + returnResult);
	} 
	//main method		
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		ArrayList<BankAccount> accountListings = new ArrayList<BankAccount>();
		int userNeeds = mainLoop(scnr);
		while (userNeeds !=6 ){
			if (userNeeds == 1) {
				addAccount(accountListings);
			}
			else if (userNeeds == 2) {
				makeDeposit(accountListings);
			}
			else if (userNeeds == 3) {
				makewithdrawal(accountListings);
			}
			else if (userNeeds == 4) {
				checkBalance(accountListings);
			}
			else {
				printAccounts(accountListings);				
			}
			userNeeds = mainLoop(scnr);
		}
		
		System.out.print("Thanks for doing business with ABC Bank!");
	}	
}