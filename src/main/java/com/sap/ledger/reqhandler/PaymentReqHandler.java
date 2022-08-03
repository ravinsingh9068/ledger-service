package com.sap.ledger.reqhandler;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.sap.ledger.entity.Loan;
import com.sap.ledger.entity.Payment;
import com.sap.ledger.repository.LoanRepository;
import com.sap.ledger.repository.PaymentRepository;
import com.sap.ledger.view.response.BaseResponse;

@Component
public class PaymentReqHandler implements RequestHandler{
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private MessageSource messages;
	

	public BaseResponse handleCommandRequest(String command){
		String[] paymentTuple = command.split(" ");
		Payment payment = new Payment();
		payment.setAmount(new BigDecimal(paymentTuple[3]));
		payment.setEmiNumber(Integer.valueOf(paymentTuple[4]));
		payment.setBankName(paymentTuple[1]);
		payment.setBorrowerName(paymentTuple[2]);
		Loan loan = loanRepository.findByBankNameAndBorrowerName(payment.getBankName(),payment.getBorrowerName());
		if (loan == null){
			throw new IllegalArgumentException(messages.getMessage("err.loan.record.not.found", null, null));
		}
		var totalValidEmis = loan.getLoanTenure() * 12;
		if (payment.getEmiNumber() > totalValidEmis){
			throw new IllegalArgumentException(messages.getMessage("err.invalid.emis", null, null));
		}
		payment = paymentRepository.save(payment);
		return new BaseResponse(payment.getId());
	}

}
