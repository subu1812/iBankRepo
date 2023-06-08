package i.bankapp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.lang.NonNull;

import lombok.Data;
@Data
@Entity
@Table(name = "ACCOUNTS")
public class Accounts {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ACCOUNT_ID")
	private int acctID;
	
	@OneToMany(mappedBy="accounts")
	private Set<Transactions> transactions;
	
	@OneToMany(mappedBy="accounts")
	private Set<Beneficiary> beneficiary;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "acctID")
    private AccountBalance accountBalance;
	
	@NonNull
	@Pattern(regexp="^[a-zA-Z0-9\s]*$")
	@Column(name = "ACCOUNT_NAME")
	private String accountName;
	
	@Column(name = "PHONE")
	private int phoneNumber;
	
	@Email(message = "Email is not valid")
	@NotEmpty(message = "Email is mandatory")
	@Column(name = "EMAIL",unique=true)
	private String eMail;
	
	@NonNull
	@Column(name = "STATUS")
	private String acctStatus;
	
	public Accounts() {

	}

	public Accounts(int acctID, @Pattern(regexp = "^[a-zA-Z0-9 ]*$") String accountName, int phoneNumber,
			@Email String eMail, String acctStatus) {
		super();
		this.acctID = acctID;
		this.accountName = accountName;
		this.phoneNumber = phoneNumber;
		this.eMail = eMail;
		this.acctStatus = acctStatus;
	}

	public int getAcctID() {
		return acctID;
	}

	public void setAcctID(int acctID) {
		this.acctID = acctID;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getAcctStatus() {
		return acctStatus;
	}

	public void setAcctStatus(String acctStatus) {
		this.acctStatus = acctStatus;
	}

}
