package com.sap.ledger.view.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentRequest {

	private String bankName;
	private String borrowerName;
	private BigDecimal lumpsumAmount;
	private int emi;
}
