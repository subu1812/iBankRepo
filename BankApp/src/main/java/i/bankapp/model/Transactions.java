package i.bankapp.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "TRANSACTIONS")
public class Transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="seq_generator", sequenceName = "SEQ_TRANSACTIONS", allocationSize=1)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "ACCOUNT_ID")
	private int acctID;
	
	@ManyToOne
    @JoinColumn(name="account_id", nullable=false, insertable = false, updatable = false)
    private Accounts accounts;
	
	@Column(name = "DATE")
	@DateTimeFormat(pattern="dd-MMM-yy")
	private LocalDate date;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "AMOUNT")
	private int amount;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "REMARKS")
	private String remarks;

	public Transactions() {
		super();
	}

	public Transactions(int id, int acctID, LocalDate date, String type, int amount, String status, String remarks) {
		super();
		this.id = id;
		this.acctID = acctID;
		this.date = date;
		this.type = type;
		this.amount = amount;
		this.status = status;
		this.remarks = remarks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAcctID() {
		return acctID;
	}

	public void setAcctID(int acctID) {
		this.acctID = acctID;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
