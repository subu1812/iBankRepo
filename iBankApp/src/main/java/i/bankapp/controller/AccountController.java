package i.bankapp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import i.bankapp.exception.ResourceNotFoundException;
import i.bankapp.model.AccountBalance;
import i.bankapp.model.Accounts;
import i.bankapp.model.Transactions;
import i.bankapp.service.AccountBalanceService;
import i.bankapp.service.AccountService;
import i.bankapp.service.TransactionsService;

@RestController
@RequestMapping("/iBank")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountBalanceService accountBalanceService;
	
	@Autowired
	private TransactionsService transactionsService;
	
 	// createAccount
	@PostMapping("/account")
	@ExceptionHandler({ConstraintViolationException.class})
	public @ResponseBody Accounts createAccount(@Valid @RequestBody Accounts accounts) throws MethodArgumentNotValidException{
		return accountService.createAccount(accounts);
	}
	
	@PutMapping("/account/{acctID}")
	public ResponseEntity<Accounts> updateAccount(@PathVariable int acctID, @RequestBody Accounts accounts) throws ResourceNotFoundException {
		return accountService.updateAccount(acctID,accounts);
	}

	// checkBalance
	@GetMapping("/account/{acctID}/balance")
	public int getBalance(@PathVariable int acctID) {
		return accountBalanceService.getBalance(acctID);
	}

	// depositAmount
	@PutMapping("/account/{acctID}/deposit/{amount}")
	public @ResponseBody List<Object> depositAmount(@PathVariable int acctID, @PathVariable int amount) {
		int initBal = getBalance(acctID);
		accountBalanceService.depositAmount(acctID, initBal + amount);
		AccountBalance accountBalance = new AccountBalance(acctID, initBal + amount);
		accountBalanceService.addAccountBalance(accountBalance);
		Transactions transactions = new Transactions();
		transactions.setAcctID(acctID);
		transactions.setAmount(amount);
		transactions.setDate(LocalDate.now());
		transactions.setType("Credit");
		transactions.setStatus("Success");
		transactionsService.addtransactions(transactions);
		List<Object> accountBalanceAndTransactionsList = new ArrayList<Object>();
		accountBalanceAndTransactionsList.add(accountBalance);
		accountBalanceAndTransactionsList.add(transactions);
		return accountBalanceAndTransactionsList;
	}

	// withdrawAmount
	@PutMapping("/account/{acctID}/withdraw/{amount}")
	public @ResponseBody List<Object> withdrawAmount(@PathVariable int acctID, @PathVariable int amount) {
		int initBal = getBalance(acctID);
		accountBalanceService.withdrawAmount(acctID, initBal - amount);
		AccountBalance accountBalance = new AccountBalance(acctID, initBal - amount);
		accountBalanceService.addAccountBalance(accountBalance);
		Transactions transactions = new Transactions();
		transactions.setAcctID(acctID);
		transactions.setAmount(amount);
		transactions.setDate(LocalDate.now());
		transactions.setType("Debit");
		transactions.setStatus("Success");
		transactionsService.addtransactions(transactions);
		List<Object> accountBalanceAndTransactionsList = new ArrayList<Object>();
		accountBalanceAndTransactionsList.add(accountBalance);
		accountBalanceAndTransactionsList.add(transactions);
		return accountBalanceAndTransactionsList;
	}

	// transferAmount
	@PutMapping("/account/{acctID}/transfer/{destAcctID}/{amount}")
	public @ResponseBody List<Object> transferAmount(@PathVariable int acctID, @PathVariable int destAcctID, @PathVariable int amount) {
		//int initBalSender = getBalance(acctID);
		//int initBalReceiver = getBalance(destAcctID);
		accountBalanceService.transferAmount(acctID, destAcctID, amount);
		List<Object> senderAccount = withdrawAmount(acctID, amount);
		List<Object> receiverAccount = depositAmount(destAcctID, amount);
		List<Object> transferAmountDetials = new ArrayList<Object>();
		transferAmountDetials.add(senderAccount);
		transferAmountDetials.add(receiverAccount);
		return transferAmountDetials;
	}

	// deleteAccount
	@DeleteMapping("/account/{acctID}")
	public void deleteAccount(@PathVariable int acctID) {
		accountService.deleteAccount(acctID);
	}

	// getAccountInfo
	@GetMapping("/account/{acctID}")
	public Accounts getAccountInfo(@PathVariable int acctID) {
		return accountService.getAccountInfo(acctID);
	}
	
	@PostMapping("/accountbalance")
	public AccountBalance addAccountBalance(@RequestBody AccountBalance accountBalance) {
		return accountBalanceService.addAccountBalance(accountBalance);
	}
	
	@GetMapping("/accountbalance/{acctID}")
	public AccountBalance getAccountBalanceById(@PathVariable int acctID) {
		return accountBalanceService.getAccountBalanceById(acctID);
	}
	
	@PostMapping("/transactions")
	public Transactions addtransactions(@RequestBody Transactions transactions) {
		return transactionsService.addtransactions(transactions);
	}
	
	@GetMapping("/transactions/{acctID}")
	public @ResponseBody List<Transactions> getAllTransactionsByAcctId(@PathVariable int acctID) {
		return transactionsService.getAllTransactionsByAcctId(acctID);
	}
	
	@GetMapping("/account/{acctID}/accountSummary")
	public List<Object> getAccountSummary(@PathVariable int acctID){
		Accounts accounts = getAccountInfo(acctID);
		AccountBalance accountBalance = getAccountBalanceById(acctID);
		List<Transactions> transactions = transactionsService.getAllTransactionsByAcctId(acctID);
		List<Object> accountSummary = new ArrayList<>();
		accountSummary.add(accounts);
		accountSummary.add(accountBalance);
		accountSummary.add(transactions);
		return accountSummary;
	}
	
	
	
	
}
