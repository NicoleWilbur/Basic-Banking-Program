public class CheckingAccount extends BankAccount {
	private double interestRate;
	private int overdraftFee;

	public CheckingAccount(String firstName, String lastName, int accountID, double interestRate, int overdraftFee) {
		super(firstName, lastName, accountID);
		this.interestRate = interestRate;
		this.overdraftFee = overdraftFee;
	}
	public void setInterestRate (double interestRate) {
		this.interestRate = interestRate;
	}
	public void setOverdraftFee (int overdraftFee) {
		this.overdraftFee = overdraftFee;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public int getOverdraftFee () {
		return overdraftFee;
	}
	public void setwithdrawal(double withdrawal) {
		super.withdrawal(withdrawal);
	}
	@Override
	public void withdrawal(double withdrawal) {
		if (balance - withdrawal < 0) {
			this.balance = balance - withdrawal - overdraftFee;
			System.out.println("You're balance is now " + this.balance + ". A " + overdraftFee
					+ " overdraft fee has been assessed.");
		}
		else {
			super.withdrawal(withdrawal);
		}
	}
	@Override
	public String accountSummary() {
		return (super.accountSummary() + ", InterestRate: " + interestRate);
	}
}
