package i.bankapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_BALANCE")
public class AccountBalance {
	
	@Id
	@Column(name = "ACCOUNT_ID")
	private int acctID;
	
	@OneToOne(mappedBy = "accountBalance")
    private Accounts accounts;
	
	@Column(name = "BALANCE")
	private int balance;

	public AccountBalance() {

	}

	public AccountBalance(int acctID, int balance) {
		super();
		this.acctID = acctID;
		this.balance = balance;
	}

	public int getAcctID() {
		return acctID;
	}

	public void setAcctID(int acctID) {
		this.acctID = acctID;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}
