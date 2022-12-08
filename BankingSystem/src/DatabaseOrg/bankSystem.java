package DatabaseOrg;

import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.Scanner;
import java.util.Random;



public class bankSystem {
	//Everything below is for connection to sql servers
	public static Scanner scan = new Scanner(System.in); 
	public static Scanner input = new Scanner(System.in);
	public static final String JDBC_DRIVER = "org.postgresql.Driver";
	public static final String JDBC_DB = "Banking";
	public static final String JDBC_PORT = "5432";
	public static final String JDBC_HOST = "localhost";
	public static final String JDBC_URL = "jdbc:postgresql://" + JDBC_HOST + ":" + JDBC_PORT + "/" + JDBC_DB;
	public static final String DBUSER = "postgres";
	public static final String DBPASSWD = "bank";
	public static Connection c;
	public static Statement statement;
	
	public static void main (String[] args) throws Exception {
		try {
			// load the driver based on the drivers class name
			Class.forName(JDBC_DRIVER);
			// create a connection
			
			c = DriverManager.getConnection(JDBC_URL, DBUSER, DBPASSWD);
			statement = c.createStatement();
            System.out.println("Connection to Bank Database Successful.");	
            
            startScreen(statement); //starts the first screen
            
            statement.close();
            c.close();

		}
		catch (Exception e) {
			System.err.println("An error occurred: " + e.toString());
			System.err.println("\n\nFOR THIS PROGRAM TO WORK YOU HAVE TO HAVE A POSTGRES SERVER RUNNING LOCALLY (OR DOCKER) AT "
							   + JDBC_HOST
							   + " WITH PORT " + JDBC_PORT
							   + " AND DATABASE " + JDBC_DB
							   + " AND USER " + DBUSER
							   + " WITH PASSWORD " + DBPASSWD);}
		}
		
	public static void startScreen(Statement stmt) throws Exception { //first screen
		//below is a menu prompt for the user to choose either being a customer or employee
		System.out.println("Choose role based on menu below. Only enter number associated"); 
		System.out.println("1. Customer");
		System.out.println("2. Employee");
		System.out.println("3. Exit");
		int SSN = 0;
		int userInput =  scan.nextInt();
		switch(userInput) { //switch case that combs over the user's input
			case 1:{ //You are a customer
				System.out.println("Greetings valued customer!");
				customerPrompt(statement);
				break;
			}
			case 2:{ //Will now prompt you to specify which type of employee you are
				System.out.println("Which type of employee are you? Select from menu below.");
				System.out.println("1. Manager");
				System.out.println("2. Teller");
				System.out.println("3. Loan Specialist");
				int userInput2 = scan.nextInt();
				switch(userInput2) {
					case 1:{ //You are a manager
						System.out.println("Welcome back Mr. Manager!"); 
						managerPrompt(statement);
						break;
					}
					case 2:{ //You are a teller
						System.out.println("Welcome back Teller!");
						tellerPrompt(statement);
						break;
					}
					case 3:{ //You are a loan specialist
						System.out.println("Welcome back Loan Specialist!");
						System.out.println("What is your SSN?");
						SSN =scan.nextInt();
						break;
					}
				}
			}
			case 3:{
				break;
			}
		}
	}
   
	public static void customerPrompt(Statement statement) throws Exception{ //customer prompt screen for customer interface
		int accountID, balance, depoAmount, withAmount,accountNum, accountNumz, amount, customerID;
		int min = 100000;  
		int max = 1000000;
		String type;
		//menu options below for list of actions
		System.out.println("Select options from menu below. ");
		System.out.println("1. Open Account");
		System.out.println("2. Withdraw");
		System.out.println("3. Deposit");
		System.out.println("4. Transfer");
		System.out.println("5. Check Balance");
		System.out.println("6. Exit");
		System.out.println("");
		int userResponse = scan.nextInt();
		switch(userResponse) {
			case 1:{ //now prompts you for new account info
				System.out.println("What will be your accountID?");
				accountID = scan.nextInt();
				System.out.println("Do you want to open a checking or savings account?");
				type = input.nextLine();
				System.out.println("How much money are you putting into the account?");
				balance = scan.nextInt();
				accountNum = (int)(Math.random()*(max-min+1)+min); ;
				openAccount(accountID, type, balance, accountNum, statement);
				break;
			}
			case 2:{ //now prompts you for your account info and withdrawal amount
				System.out.println("What is your accountID?");
				accountID = scan.nextInt();
				System.out.println("What is your account number?");
				accountNum = scan.nextInt();
				System.out.println("How much do you want to withdraw?");
				withAmount = scan.nextInt();
				withdraw(accountID, accountNum, withAmount, statement);
				break;
			}
			case 3:{ //now prompts you for deposit info
				System.out.println("What is your account number?");
				accountNum = scan.nextInt();
				System.out.println("How much u want to deposit?");
				depoAmount = scan.nextInt();
				deposit(accountNum, depoAmount, statement);
				break;
			}
			case 4:{ //Will prompt you for your info and recepient info
				System.out.println("What is the account number?");
				accountNum = scan.nextInt();
				System.out.println("What is the account number of the account that the money is being sent to?");
				accountNumz = scan.nextInt();
				System.out.println("How much money do you want to send?");
				amount = scan.nextInt();
				System.out.println("What is the customerID");
				customerID = scan.nextInt();
				transferMoney(customerID, accountNum, accountNumz, amount, statement);
				break;
			}
			case 5: { //allows user to check balance; asks for info
				System.out.println("What is the account number?");
				System.out.println("");
				accountNum = scan.nextInt();
				checkBalance(accountNum, statement);
				break;
			}
			case 6:{
				System.out.println("Goodbye!");
				return;
			}
		}
	}
	
	
	public static void managerPrompt(Statement statement) throws Exception{ //opens up the manager interface
		int customerID, balance, amount, depoAmount, accountNum, accountNumz;
		String type;
		int min = 100000;  
		int max = 1000000;
		//below are the manager menu options
		System.out.println("Select options from menu below. ");
		System.out.println("1. Open Account");
		System.out.println("2. Remove Account");
		System.out.println("3. Withdraw");
		System.out.println("4. Deposit");
		System.out.println("5. Transfer");
		System.out.println("6. Alter Overdraft Fee");
		System.out.println("7. Alter Interest Fee");
		System.out.println("8. Alter Account Fee");
		System.out.println("9. Check Balance");
		System.out.println("10. Exit");
		System.out.println("");
		int userResponse = scan.nextInt();
		switch(userResponse) { //another switch case over user input
			case 1:{ //Lets the manager open an account after info is collected
				System.out.println("What will be your customerID?");
				customerID = scan.nextInt(); 
				System.out.println("Do you want to open a checking or savings account?");
				type = input.nextLine();
				System.out.println("How much money are you putting into the account?");
				balance = scan.nextInt();
				accountNum = (int)(Math.random()*(max-min+1)+min); ;
				openAccount(customerID, type, balance, accountNum, statement); //calls upon openAccount method
				break;
			}
			case 2: { //Lets the manager remove an account after info is collected
				System.out.println("What is the account number?");
				accountNum = scan.nextInt();
				removeAccount(accountNum, statement);
				break;
			}
			case 3:{ //Lets the manager withdraw money after info is collected
				System.out.println("What is the accountID?");
				customerID = scan.nextInt();
				System.out.println("What is the account number?");
				accountNum = scan.nextInt();
				System.out.println("How much do you want to withdraw?");
				amount = scan.nextInt();
				withdraw(customerID, accountNum, amount, statement);
				break;
			}
			case 4:{ //Lets the manager deposit money after info is collected
				System.out.println("What is the account number?");
				accountNum = scan.nextInt();
				System.out.println("How much you want to deposit?");
				depoAmount = scan.nextInt();
				deposit(accountNum, depoAmount, statement);
				break;
			}
			case 5:{
				System.out.println("What is the account number?");
				accountNum = scan.nextInt();
				System.out.println("What is the account number of the account that the money is being sent to?");
				accountNumz = scan.nextInt();
				System.out.println("How much money do you want to send?");
				amount = scan.nextInt();
				System.out.println("What is the customerID");
				customerID = scan.nextInt();
				transferMoney(customerID, accountNum, accountNumz, amount, statement);
				break;
			}
			case 6:{
				alterOverdraft();
				break;
			}
			case 7: {
				alterInterest();
				break;
			}
			case 8: {
				alterAccount();
				break;
			}
			case 9: {
				System.out.println("What is the account number?");
				accountNum = scan.nextInt();
				checkBalance(accountNum, statement);
				break;
			}
			case 10: {
				System.out.println("Goodbye!");
				return;
			}
		}
	}
	
	
	public static void tellerPrompt(Statement statement) throws Exception{
		int customerID, balance, amount, accountNum, depoAmount, accountNumz;
		String type;
		System.out.println("Select options from menu below. ");
		System.out.println("1. Withdrawal");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Transfer");
        System.out.println("5. Logout");
        System.out.println("");
		int userResponse = scan.nextInt();
		switch(userResponse) {
			case 1:{
				System.out.println("What is your accountID?");
				customerID = scan.nextInt();
				System.out.println("What is your account number?");
				accountNum = scan.nextInt();
				System.out.println("How much do you want to withdraw?");
				amount = scan.nextInt();
				withdraw(customerID, accountNum, amount, statement);
				break;
			}
			case 2:{
				System.out.println("What is your account number?");
				accountNum = scan.nextInt();
				System.out.println("How much u want to deposit?");
				depoAmount = scan.nextInt();
				deposit(accountNum, depoAmount, statement);
				break;
				
			}
			case 3:{
				System.out.println("What is the account number?");
				accountNum = scan.nextInt();
				checkBalance(accountNum, statement);
				break;
			}
			case 4:{
				System.out.println("What is the account number?");
				accountNum = scan.nextInt();
				System.out.println("What is the account number of the account that the money is being sent to?");
				accountNumz = scan.nextInt();
				System.out.println("How much money do you want to send?");
				amount = scan.nextInt();
				System.out.println("What is the customerID");
				customerID = scan.nextInt();
				transferMoney(customerID, accountNum, accountNumz, amount, statement);
				break;
			}
			case 5:{
				System.out.println("Goodbye!");
				return;
			}
		}
	}
	
	//Inserts collected data from prompt into sql table to create new account
	public static void openAccount(int customerID, String type, int balance, int accountNum, Statement statement) throws SQLException{
		String accInfo = "INSERT INTO accounts(customerId, accountNum, currentBalance, type) " +
				"VALUES("+customerID+","+accountNum+",'"+balance+"','"+type+"')";
		statement.executeUpdate(accInfo);
	}
	
	//Removes account based on collected data
	public static void removeAccount(int accountNum, Statement statement) throws Exception {
		String delete = "DELETE FROM accounts WHERE accountNumber=" + accountNum;
		statement.executeUpdate(delete);
	}
	//allows the user to checkBalance through a query based on collected data
	public static void checkBalance(int accountNum, Statement statement) throws Exception {
		String view = "SELECT FROM accounts WHERE accountNumber=" + accountNum;
		statement.executeQuery(view);
	}
	//Withdraws money by subtracting amount spoken from current balance of account in table. Done through update statement
	public static void withdraw(int accountID, int accountNum, int amount, Statement statement) throws Exception {
		String withdraw = "UPDATE accounts SET currentBalance = " +
				"(SELECT currentBalance FROM accounts WHERE accountNumber = "+accountNum+") - "+amount+" WHERE accountNumber = "+accountNum;
		statement.executeUpdate(withdraw);
	}
	//deposits money by adding spoken amount to current balance. Done through update statement
	public static void deposit(int accountNum, int amount, Statement statement) throws SQLException {
        String deposit = "UPDATE accounts SET currentBalance =" + 
                "(SELECT currentBalance FROM accounts WHERE accountNumber =" + accountNum + ")+" + amount +" WHERE accountNumber = " + accountNum;
        statement.executeUpdate(deposit);

    }
	//Utilizes both the withdraw and deposit functions
	public static void transferMoney(int customerID, int accountNum, int accountNum2, int amount, Statement statement) throws Exception {
        withdraw(customerID, accountNum, amount, statement); //calls upon withdraw method
        deposit(accountNum2, amount, statement); //calls upon deposit method
    }
	
	public static void alterOverdraft() throws Exception { //allows the manager to update overDraftfee on the account through update statement
		
	}
	
	public static void alterInterest() throws Exception { //allows the manager to update interestFee on account through Update statement
		
	}
	
	public static void alterAccount() throws Exception { //allows the manager to update accountFee on account through Update statement
		
	}
	
	public static void printQueryResults(Connection c, String sql) {
		try {
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery(sql);
			ResultSetMetaData md = r.getMetaData();
			int numCols = md.getColumnCount();

			System.out.printf("================================================================================" +
							  "\nQUERY: %s" +
							  "\n================================================================================\n",
							  sql);
			while(r.next()) {
				System.out.print("(");
				for(int i = 1; i <= numCols; i++) {
					System.out.printf("%s: %s%s",
									  md.getColumnName(i),
									  r.getString(i),
									  i < numCols ? ", ": ""
						);					
				}
				System.out.println(")");					
			}
			
			r.close();
			s.close();
		}
		catch (SQLException e) {
			System.err.println("An error occurred: " + e.toString());			
		}
	
	
}

}
