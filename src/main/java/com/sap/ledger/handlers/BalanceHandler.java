package com.sap.ledger.handlers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.sap.ledger.entity.Loan;
import com.sap.ledger.repository.LoanRepository;
import com.sap.ledger.view.request.BalanceReq;
import com.sap.ledger.view.response.BalanceResponse;

public class BalanceHandler {
	
	@Autowired
	private BalanceReq balanceReq;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private MessageSource messages;
	
	public BalanceResponse HandleBalanceReqCommand(){
		
		Loan loan = loanRepository.getLoanByBankAndBorrower(balanceReq.getBankName(), balanceReq.getBorrowerName());
		if (loan == null){
			throw new IllegalArgumentException(messages.getMessage("err.loan.record.not.found", null, null));
		}
		BigDecimal totalAmountPaidTillEmi = new BigDecimal(balanceReq.getRemainingEMIs())
				.multiply(loan.getPendingEmiAmount()).add(loan.getPaidAmountTillEmiNumberProvided(balanceReq.getRemainingEMIs()));

		BigDecimal amountPending = loan.getPendingAmount().subtract(totalAmountPaidTillEmi);
		int remainingEmis = (int) Math.ceil((amountPending.divide(loan.getPendingEmiAmount())).doubleValue());

		BalanceResponse balanceResponse = new BalanceResponse();
		balanceResponse.setAmountPaid(totalAmountPaidTillEmi);
		balanceResponse.setBankName(loan.getBankName());
		balanceResponse.setBorrowerName(loan.getBorrowerName());
		balanceResponse.setPendingEmis(remainingEmis > 0 ? (int)remainingEmis : 0);
		return balanceResponse;
	}
}
