package i.bankapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import i.bankapp.dao.TransactionsRepository;
import i.bankapp.model.Transactions;

@Service
public class TransactionsService {

	@Autowired
	TransactionsRepository transactionsRepository;

	public Transactions addtransactions(Transactions transactions) {
		return transactionsRepository.save(transactions);
	}
	
	public List<Transactions> getAllTransactionsByAcctId(int acctID){
		return transactionsRepository.findByAcctID(acctID);
	}
	
}
