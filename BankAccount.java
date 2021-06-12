public class BankAccount {
	private String firstName;
	private String lastName;
	private int accountID;
	protected double balance;

	//default constructor
	public BankAccount() {
		firstName = null;
		lastName = null;
		accountID = 0;
		balance = 0;
	}
	//parameterized constructor
	public BankAccount(String firstName, String lastName, int accountID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountID = accountID;
	}
	public void setFirstName (String firstName) {
		this.firstName = firstName;
	}
	public void setLastName (String lastName) {
		this.lastName = lastName;
	}
	public void setAccountID (int accountID) {
		this.accountID = accountID;
	}
	public String getFirstName () {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getAccountID() {
		return accountID;
	}
	public double getBalance() {
		return balance;
	}
	public void constructor() {
		balance = 0;
	}
	public void deposit (double balance) {
		this.balance += balance;
	}
	public void withdrawal (double withdrawal) {
		this.balance -= withdrawal;
		System.out.println("Your new balance is " + this.balance + ".");
	}
	public String accountSummary() {
		return ("Account Holder's name: "  + firstName + ", " + lastName + ", Account ID: " + accountID + ", Balance:" + balance);

	}
}
