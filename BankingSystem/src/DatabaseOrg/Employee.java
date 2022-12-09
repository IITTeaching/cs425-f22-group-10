package DatabaseOrg;

public abstract class Employee extends Branch {
	public int Employee_ID;
	public String Employee_Name;
	public String Employee_adress;
	public int Employee_SSN;
	public Employee() {
		super();
		Employee_ID = 0;
		Employee_Name = "default";
		Employee_adress="default";
		Employee_SSN=0;
	}
	
	public Employee(int Employee_ID, String Employee_Name,int branchID,String Employee_adress,String
			Employee_SSN) {
		super();
	}


	public int getE_ID() {
		return Employee_ID;
	}


	public String getE_Name() {
		return Employee_Name;
	}


	public String getE_adress() {
		return Employee_adress;
	}


	public int getE_SSN() {
		return Employee_SSN;
	}


	public void setID(int Employee_ID) {
		if (Employee_ID > 0) {
			this.Employee_ID = Employee_ID;
		}
	}


	public void setName(String Employee_Name) {
		this.Employee_Name = Employee_Name;
	}


	public void setadress(String Employee_adress) {
		this.Employee_adress = Employee_adress;
	}


	public void setSSN(int Employee_SSN) {
		this.Employee_SSN = Employee_SSN;
	}


	public void setbranch(String branch ) {
		this.branchID = branchID;
	}


	public String toString() {
		return Employee_Name + "," + Employee_ID + "," + branchID+","+ Employee_adress +","+
				Employee_SSN;
	}


	public abstract double bankSystem();
	}