package i.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import i.bankapp.dao.BeneficiaryRepository;
import i.bankapp.exception.ResourceNotFoundException;
import i.bankapp.model.Beneficiary;

@Service
public class BeneficiaryService {

	@Autowired
	private BeneficiaryRepository beneficiaryRepository;

	public Beneficiary addBeneficiaryAccount(Beneficiary beneficiary) {
		return beneficiaryRepository.save(beneficiary);
	}

	public ResponseEntity<Beneficiary> updateBeneficiaryAccountId(int acctID, Beneficiary beneficiary) throws ResourceNotFoundException {
		Beneficiary updateBeneficiary = beneficiaryRepository.findById(acctID)
                .orElseThrow(() -> new ResourceNotFoundException("Beneficiary not exist with id: " + acctID));

		updateBeneficiary.setBeneficiaryAccountId(beneficiary.getBeneficiaryAccountId());
		updateBeneficiary.setBeneficiaryIFSCCode(beneficiary.getBeneficiaryIFSCCode());
		updateBeneficiary.setBeneficiaryName(beneficiary.getBeneficiaryName());
		updateBeneficiary.setStatus(beneficiary.getStatus());

		beneficiaryRepository.save(updateBeneficiary);

        return ResponseEntity.ok(updateBeneficiary);
	}

	public void deleteBeneficiary(int acctID) {
		beneficiaryRepository.deleteById(acctID);
	}

}
