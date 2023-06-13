package i.bankapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import i.bankapp.model.Beneficiary;

@Repository
public interface BeneficiaryRepository extends CrudRepository<Beneficiary, Integer>, JpaRepository<Beneficiary, Integer> {


}
