package DatabaseOrg;

public abstract class Customer extends Branch {
	public String customer_Name;
	public String customer_adress;
	public int customer_SSN;
	
	
	public Customer() {
		super();
		customer_SSN = 0;
		customer_Name = "default";
		customer_adress="default";
	}
	
	
	public Customer( int customer_SSN, String customer_Name, String customer_adress, int branchID ) {
		super();
		}
	
	public int getC_SSN() {
		return customer_SSN;
		
	}
	
	
	public String getC_Name() {
		return customer_Name;
	}
	
	
	public String getC_adress() {
		return customer_adress;
	}
	
	
	public int getC_branchID() {
		return branchID;
	}
	
	
	public void setCName(String customer_Name) {
		this.customer_Name = customer_Name;
	}
	
	public void setCadress(String customer_adress) {
		this.customer_adress = customer_adress;
	}
	
	
	public void setCSSN( int customer_SSN) {
		this. customer_SSN = customer_SSN;
	}
	
	
	public abstract double bankSystem();
	
	}