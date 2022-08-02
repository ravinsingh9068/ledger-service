package com.sap.ledger.reqhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.ledger.entity.Loan;
import com.sap.ledger.repository.LoanRepository;
import com.sap.ledger.view.request.LoanReq;

@Component
public class LoanReqHandler {

	@Autowired
	private LoanReq loanReq;
	
	@Autowired
	private LoanRepository loanRepository;
	
	public long HandleLoanReqCommand(){
		Loan loan = convertLoanReqToEntity(loanReq);
		loan = loanRepository.save(loan);
		return loan.getId();
	}

	private Loan convertLoanReqToEntity(LoanReq loanReq) {
		Loan loan = new Loan();
		loan.setBankName(loanReq.getBankName());
		loan.setBorrowerName(loanReq.getBorrowerName());
		loan.setPrincipalAmount(loanReq.getPrincipalAmount());
		loan.setInterestRate(loanReq.getInterestRate());
		loan.setLoanTenure(loanReq.getLoanTenure());
		return loan;
	}
}
