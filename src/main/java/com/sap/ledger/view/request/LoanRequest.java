package com.sap.ledger.view.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanRequest{

	private String bankName;
	private String borrowerName;
	private BigDecimal principalAmount;
	private int loanTenure;
	private BigDecimal rateOfInterest;

}
