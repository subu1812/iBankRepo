package i.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import i.bankapp.exception.ResourceNotFoundException;
import i.bankapp.model.Beneficiary;
import i.bankapp.service.BeneficiaryService;

@RestController
@RequestMapping("/iBank")
public class BeneficiaryController {
	
	@Autowired
	private BeneficiaryService beneficiaryService;
	
	@PostMapping("/account/{acctID}/addBeneficiaryAccount")
	public Beneficiary addBeneficiaryAccount(@RequestBody Beneficiary beneficiary) {
		return beneficiaryService.addBeneficiaryAccount(beneficiary);
	}
	
	@PutMapping("/account/{acctID}/updateBeneficiaryAccount/{beneficiaryAccountId}")
	public ResponseEntity<Beneficiary> updateBeneficiaryAccountId(@PathVariable int acctID,@RequestBody Beneficiary beneficiary) throws ResourceNotFoundException{
		return beneficiaryService.updateBeneficiaryAccountId(acctID,beneficiary);
	}    
	
	@DeleteMapping("/account/{acctID}/deleteBeneficiary")
	public void deleteBeneficiary(@PathVariable int acctID) {
		beneficiaryService.deleteBeneficiary(acctID);
	}

}
