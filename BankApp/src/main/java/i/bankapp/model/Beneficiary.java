package i.bankapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Beneficiary")
public class Beneficiary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@NotNull
	@Column(name = "ACCOUNT_ID")
	private int acctID;
	
	@ManyToOne
    @JoinColumn(name="account_id", nullable=false, insertable = false, updatable = false)
    private Accounts accounts;
	
	@NotNull
	@Column(name = "BENE_ACCOUNT_ID")
	private int beneficiaryAccountId;
	
	@Column(name = "BENE_IFSCCODE")
	private String beneficiaryIFSCCode;

	@NotNull
	@Column(name = "BENE_NAME")
	private String beneficiaryName;
	
	@NotNull
	@Column(name = "STATUS")
	private String status;

	public Beneficiary() {

	}

	public Beneficiary(int acctID, int beneficiaryAccountId, String beneficiaryIFSCCode, String beneficiaryName, String status) {
		super();
		this.acctID = acctID;
		this.beneficiaryAccountId = beneficiaryAccountId;
		this.beneficiaryIFSCCode = beneficiaryIFSCCode;
		this.beneficiaryName = beneficiaryName;
		this.status = status;
	}

	public int getAcctID() {
		return acctID;
	}

	public void setAcctID(int acctID) {
		this.acctID = acctID;
	}

	public int getBeneficiaryAccountId() {
		return beneficiaryAccountId;
	}

	public void setBeneficiaryAccountId(int beneficiaryAccountId) {
		this.beneficiaryAccountId = beneficiaryAccountId;
	}

	public String getBeneficiaryIFSCCode() {
		return beneficiaryIFSCCode;
	}

	public void setBeneficiaryIFSCCode(String beneficiaryIFSCCode) {
		this.beneficiaryIFSCCode = beneficiaryIFSCCode;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
