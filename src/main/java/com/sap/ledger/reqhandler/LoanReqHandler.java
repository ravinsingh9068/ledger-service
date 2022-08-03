package com.sap.ledger.reqhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.ledger.entity.Loan;
import com.sap.ledger.repository.LoanRepository;
import com.sap.ledger.view.request.LoanReq;
import com.sap.ledger.view.response.BaseResponse;

@Component
public class LoanReqHandler implements RequestHandler{

	private LoanReq loanReq;
	
	private LoanRepository loanRepository;
	
	@Autowired
	public LoanReqHandler(LoanReq loanRequest, LoanRepository loanRepository) {
		this.loanReq=loanRequest;
		this.loanRepository=loanRepository;
	}

	@Override
	public BaseResponse handleCommandRequest(){
		Loan loan = convertLoanReqToEntity(loanReq);
		loan = loanRepository.save(loan);
		return new BaseResponse(loan.getId());
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
