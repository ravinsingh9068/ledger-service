package com.sap.ledger.reqhandler;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.sap.ledger.entity.Loan;
import com.sap.ledger.repository.LoanRepository;
import com.sap.ledger.view.request.BalanceReq;
import com.sap.ledger.view.response.BalanceResponse;
import com.sap.ledger.view.response.BaseResponse;

@Component
public class BalanceReqHandler implements RequestHandler{
	
	private BalanceReq balanceReq;
	
	private LoanRepository loanRepository;
	
	@Autowired
	private MessageSource messages;
	
	public BalanceReqHandler(BalanceReq balanceRequest, LoanRepository loanRepository) {
		this.balanceReq = balanceRequest;
		this.loanRepository=loanRepository;
	}

	@Override
	public BaseResponse handleCommandRequest(){
		
		Loan loan = loanRepository.findByBankNameAndBorrowerName(balanceReq.getBankName(), balanceReq.getBorrowerName());
		if (loan == null){
			throw new IllegalArgumentException(messages.getMessage("err.loan.record.not.found", null, null));
		}
		BigDecimal totalAmountPaidTillEmi = new BigDecimal(balanceReq.getRemainingEMIs())
				.multiply(loan.getPendingEmiAmount()).add(loan.getPaidAmountTillEmiNumberProvided(balanceReq.getRemainingEMIs()));

		BigDecimal amountPending = loan.getPendingAmount().subtract(totalAmountPaidTillEmi);
		int remainingEmis = (int) Math.ceil((amountPending.divide(loan.getPendingEmiAmount(),4, RoundingMode.HALF_EVEN)).doubleValue());

		BalanceResponse balanceResponse = new BalanceResponse();
		balanceResponse.setAmountPaid(totalAmountPaidTillEmi);
		balanceResponse.setBankName(loan.getBankName());
		balanceResponse.setBorrowerName(loan.getBorrowerName());
		balanceResponse.setPendingEmis(remainingEmis > 0 ? (int)remainingEmis : 0);
		return new BaseResponse(balanceResponse);
	}
	
}
