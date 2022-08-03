package com.sap.ledger.processor;

import static com.sap.ledger.constant.CommonConstants.BALANCE;
import static com.sap.ledger.constant.CommonConstants.LOAN;
import static com.sap.ledger.constant.CommonConstants.PAYMENT;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.ledger.repository.LoanRepository;
import com.sap.ledger.repository.PaymentRepository;
import com.sap.ledger.reqhandler.BalanceReqHandler;
import com.sap.ledger.reqhandler.LoanReqHandler;
import com.sap.ledger.reqhandler.PaymentReqHandler;
import com.sap.ledger.reqhandler.RequestHandler;
import com.sap.ledger.view.request.BalanceReq;
import com.sap.ledger.view.request.LoanReq;
import com.sap.ledger.view.request.PaymentReq;

@Component
public class RequestHandlerFactory {
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	
	public RequestHandler getRequestHandler(String command) {
		RequestHandler request = null;
		if (command.trim().length() > 0) {
			String commandType = command.substring(0, command.indexOf(" "));
			if (commandType != null && commandType.length() > 0) {
				switch (commandType) {
				case LOAN:
					request = getLoanHandler(command);
					break;
				case PAYMENT:
					request = getPaymentHandler(command);
					break;
				case BALANCE:
					request = getBalanceHandler(command);
					break;
				default:
					break;
				}
			}
		}
		return request;
	}

	private LoanReqHandler getLoanHandler(String commands){
		
		String[] loanTuple = commands.split(" ");
		String bankName = loanTuple[1];
		String borrowerName = loanTuple[2];
		BigDecimal principalAmount = new BigDecimal(loanTuple[3]);
		Integer tenure = Integer.valueOf(loanTuple[4]);
		BigDecimal interestRate = new BigDecimal(loanTuple[5]);
		return new LoanReqHandler(new LoanReq(bankName, borrowerName, principalAmount, tenure, interestRate), loanRepository);
	}

	private PaymentReqHandler getPaymentHandler(String commands){
		
		String[] paymentTuple = commands.split(" ");
		String bankName = paymentTuple[1];
		String borrowerName = paymentTuple[2];
		BigDecimal amount = new BigDecimal(paymentTuple[3]);
		Integer emi = Integer.valueOf(paymentTuple[4]);
		return new PaymentReqHandler(new PaymentReq(bankName, borrowerName, amount, emi), paymentRepository);
	}

	private BalanceReqHandler getBalanceHandler(String commands){
		
		String[] balanceTuple = commands.split(" ");
		String bankName = balanceTuple[1];
		String borrowerName = balanceTuple[2];
		Integer emi = Integer.valueOf(balanceTuple[3]);

		return new BalanceReqHandler(new BalanceReq(bankName, borrowerName, emi), loanRepository);
	}
}
