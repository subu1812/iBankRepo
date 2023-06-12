package i.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i.bankapp.dao.AccountBalanceRepository;
import i.bankapp.model.AccountBalance;

@Service
public class AccountBalanceService {
	
	@Autowired
	private AccountBalanceRepository accountBalanceRepository;
	
	public int getBalance(int acctID) {
		return accountBalanceRepository.findBalanceByAcctID(acctID);
	}

	public void depositAmount(int acctID, int amount) {
		accountBalanceRepository.saveBalanceByAcctID(acctID, amount);
	}

	public void withdrawAmount(int acctID, int amount) {
		accountBalanceRepository.withdrawAmountByAcctID(acctID, amount);
	}

	public void transferAmount(int acctID, int destAcctID, int amount) {
		accountBalanceRepository.withdrawAmountByAcctID(acctID, amount);
		accountBalanceRepository.saveBalanceByAcctID(destAcctID, amount);
	}

	public AccountBalance addAccountBalance(AccountBalance accountBalance) {
		return accountBalanceRepository.save(accountBalance);
	}

	public AccountBalance getAccountBalanceByAcctID(int acctID) {
		return accountBalanceRepository.getByAcctID(acctID);
	}

}
