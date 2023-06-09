package i.bankapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import i.bankapp.model.Transactions;
@Repository
@EnableJpaRepositories
public interface TransactionsRepository  extends CrudRepository<Transactions, Integer>, JpaRepository<Transactions, Integer> {
	
	//@Query(value="select t.id id,t.ACCOUNT_ID acctID,t.DATE date,t.TYPE type,t.AMOUNT amount,t.STATUS status,t.REMARKS remarks from Transactions t where t.ACCOUNT_ID = ?1",nativeQuery = true)
	//List<TransactionDTO> getAllTransactionsByAcctId(int acctID);
	
	public List<Transactions> findByAcctID(int acctID);

}
