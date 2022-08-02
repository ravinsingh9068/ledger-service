package com.sap.ledger.processor;

import static com.sap.ledger.constant.CommonConstants.BALANCE;
import static com.sap.ledger.constant.CommonConstants.LOAN;
import static com.sap.ledger.constant.CommonConstants.PAYMENT;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.sap.ledger.reqhandler.BalanceReqHandler;
import com.sap.ledger.reqhandler.LoanReqHandler;
import com.sap.ledger.reqhandler.PaymentReqHandler;
import com.sap.ledger.reqhandler.RequestHandler;
import com.sap.ledger.view.request.BalanceReq;
import com.sap.ledger.view.request.LoanReq;
import com.sap.ledger.view.request.PaymentReq;

@Component
public class RequestHandlerFactory {
	
	public RequestHandler getRequestHandler(String command) {
		RequestHandler request = null;
		if (command.trim().length() > 0) {
			command = command.substring(0, command.indexOf(" "));
			if (command != null && command.length() > 0) {
				switch (command) {
				case LOAN:
					getLoanHandler(command);
					break;
				case PAYMENT:
					getPaymentHandler(command);
					break;
				case BALANCE:
					getBalanceHandler(command);
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
		String bankName = loanTuple[0];
		String borrowerName = loanTuple[1];
		BigDecimal principalAmount = new BigDecimal(loanTuple[2]);
		Integer tenure = Integer.valueOf(loanTuple[3]);
		BigDecimal interestRate = new BigDecimal(loanTuple[4]);
		return new LoanReqHandler(new LoanReq(bankName, borrowerName, principalAmount, tenure, interestRate));
	}

	private PaymentReqHandler getPaymentHandler(String commands){
		
		String[] paymentTuple = commands.split(" ");
		String bankName = paymentTuple[0];
		String borrowerName = paymentTuple[1];
		BigDecimal amount = new BigDecimal(paymentTuple[2]);
		Integer emi = Integer.valueOf(paymentTuple[3]);
		return new PaymentReqHandler(new PaymentReq(bankName, borrowerName, amount, emi));
	}

	private BalanceReqHandler getBalanceHandler(String commands){
		
		String[] balanceTuple = commands.split(" ");
		String bankName = balanceTuple[0];
		String borrowerName = balanceTuple[1];
		Integer emi = Integer.valueOf(balanceTuple[2]);

		return new BalanceReqHandler(new BalanceReq(bankName, borrowerName, emi));
	}
}
