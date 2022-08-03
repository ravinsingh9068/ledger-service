package com.sap.ledger.reqhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.sap.ledger.entity.Loan;
import com.sap.ledger.entity.Payment;
import com.sap.ledger.repository.LoanRepository;
import com.sap.ledger.repository.PaymentRepository;
import com.sap.ledger.view.request.PaymentReq;
import com.sap.ledger.view.response.BaseResponse;

@Component
public class PaymentReqHandler implements RequestHandler{
	@Autowired
	private PaymentReq paymentReq;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	private MessageSource messages;
	
	public PaymentReqHandler(PaymentReq paymentRequest,PaymentRepository paymentRepository) {
		this.paymentReq=paymentRequest;
		this.paymentRepository=paymentRepository;
	}

	public BaseResponse handleCommandRequest(){
		Loan loan = loanRepository.findByBankNameAndBorrowerName(paymentReq.getBankName(), paymentReq.getBorrowerName());
		if (loan == null){
			throw new IllegalArgumentException(messages.getMessage("err.loan.record.not.found", null, null));
		}
		var totalValidEmis = loan.getLoanTenure() * 12;
		if (paymentReq.getRepaymentEMINumbers() > totalValidEmis){
			throw new IllegalArgumentException(messages.getMessage("err.invalid.emis", null, null));
		}
		Payment payment = convertPaymentReqToEntity(paymentReq);
		payment = paymentRepository.save(payment);
		return new BaseResponse(payment.getId());
	}

	private Payment convertPaymentReqToEntity(PaymentReq paymentReq) {
		if (paymentReq == null){
			throw new IllegalArgumentException(messages.getMessage("err.invalid.payment.request", null, null));
		}
		Payment payment = new Payment();
		payment.setAmount(paymentReq.getRepaidAmount());
		payment.setEmiNumber(paymentReq.getRepaymentEMINumbers());
		payment.setBankName(paymentReq.getBankName());
		payment.setBorrowerName(paymentReq.getBorrowerName());
		return payment;
	}
}
