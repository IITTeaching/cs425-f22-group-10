package DatabaseOrg;

public abstract class Account extends Customer{
	public int Account_ID;
	public int Account_amount;


	public Account() {
		super();
		Account_ID = 0;
		Account_amount=0;
	}


	public Account( int Account_ID, int Account_amount, String customer_Name ) {
		super();
	}


	public int getA_ID() {
		return Account_ID ;
	}


	public int getA_amount() {
		return Account_amount;
	}	


	public String getowner_name() {
		return customer_Name;
	}


	public int getC_branchID() {
		return branchID;
	}


	public void setAID(int Account_ID) {
		this.Account_ID = Account_ID;
	}

	public void setA_amount(int Account_amount) {
		this.Account_amount = Account_amount;
	}


	public void setowner( String customer_Name) {
		this.customer_Name = customer_Name;
	}

	public abstract double bankSystem();
	}
