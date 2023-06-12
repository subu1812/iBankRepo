package i.bankapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import i.bankapp.model.AccountBalance;

@Repository
@EnableJpaRepositories
public interface AccountBalanceRepository extends CrudRepository<AccountBalance, Integer>, JpaRepository<AccountBalance, Integer> {

	@Query("select balance from AccountBalance where acctID = ?1")
	public int findBalanceByAcctID(int acctID);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update AccountBalance set balance = balance+?2 where acctID=?1")
	public void saveBalanceByAcctID(int acctID, int balance);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update AccountBalance set balance = balance-?2 where acctID=?1")
	public void withdrawAmountByAcctID(int acctID, int balance);
	
	public AccountBalance getByAcctID(int acctID);
}
