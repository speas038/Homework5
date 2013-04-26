package act.model;


/**
 * Account class contains all information for one band account
 * @author Alex
 *
 */
public class Account implements Comparable<Account>{
	
	public int ID;
	public String name;
	public double balance;
	
	public Account(int ID, String name, double balance){
		this.ID = ID;
		this.name = name;
		this.balance = balance;
	}
	
	/**
	 * This is freaking awesome because it lets the jcombobox display what I want to !!!!!!!
	 * @Override
	 */
	public String toString(){
		return ID + " " + name + " " + balance;
	}
	
	public double getBalance(){
		return balance;
	}
	
	public int getID(){
		return ID;
	}
	
	public void setBalance(double bal){
		balance = bal;
	}
	
	public String getBalanceString(){
		return Double.toString(balance);
	}
	
	public String getName(){
		return name;
	}
	
/**
 * This allows implementation of the java.lang.sort functionality to the ArrayList of Accoutns in the model
 * class
 * @param Account
 */
	public int compareTo(Account a){
		if (this.ID < a.ID )
			return -1;
		else if(this.ID > a.ID)
			return 1;
		else
			return 0;
	}

}
