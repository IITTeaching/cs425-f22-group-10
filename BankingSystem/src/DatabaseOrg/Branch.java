package DatabaseOrg;

public abstract class Branch {
	protected int branchID;
	public String adress;
	
	public Branch() {
		branchID=0;
		adress="default";
	}
	
	public int getbranchID() {
		return branchID;
	}
	
	public String getadress() {
		return adress;
	}
	
	public void setbranchID(int branchID) {
		this.branchID = branchID;
	}
	
	public abstract double bankSystem();
	}	

