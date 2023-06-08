package i.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import i.bankapp.dao.AccountBalanceRepository;
import i.bankapp.dao.AccountsRepository;
import i.bankapp.exception.ResourceNotFoundException;
import i.bankapp.model.Accounts;

@Service
public class AccountService {
	@Autowired
	private AccountsRepository accountRepository;
	
	public Accounts createAccount(Accounts acct) throws MethodArgumentNotValidException {
		return accountRepository.save(acct);
	}

	public Accounts getAccountInfo(int acctID) {
		return accountRepository.findById(acctID).orElse(null);
	}

	public void deleteAccount(int acctID) {
		accountRepository.deleteById(acctID);
	}

	public ResponseEntity<Accounts> updateAccount(int acctID, Accounts accounts) throws ResourceNotFoundException {
		Accounts updateAcccounts = accountRepository.findById(acctID)
                .orElseThrow(() -> new ResourceNotFoundException("Account not exist with id: " + acctID));
		
		updateAcccounts.setAcctID(acctID);
		updateAcccounts.setAccountName(accounts.getAccountName());
		updateAcccounts.setAcctStatus(accounts.getAcctStatus());
		updateAcccounts.seteMail(accounts.geteMail());
		updateAcccounts.setPhoneNumber(accounts.getPhoneNumber());

		accountRepository.save(updateAcccounts);

        return ResponseEntity.ok(updateAcccounts);
	}

}
