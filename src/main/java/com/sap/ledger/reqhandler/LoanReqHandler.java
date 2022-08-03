package com.sap.ledger.reqhandler;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.ledger.entity.Loan;
import com.sap.ledger.repository.LoanRepository;
import com.sap.ledger.view.response.BaseResponse;

@Component
public class LoanReqHandler implements RequestHandler{
	
	@Autowired
	private LoanRepository loanRepository;

	@Override
	public BaseResponse handleCommandRequest(String command){
		
		String[] loanTuple = command.split(" ");
		
		Loan loan = new Loan();
		loan.setBankName(loanTuple[1]);
		loan.setBorrowerName(loanTuple[2]);
		loan.setPrincipalAmount(new BigDecimal(loanTuple[3]));
		loan.setLoanTenure(Integer.valueOf(loanTuple[4]));
		loan.setInterestRate(new BigDecimal(loanTuple[5]));
		loan = loanRepository.save(loan);
		return new BaseResponse(loan.getId());
	}
}
