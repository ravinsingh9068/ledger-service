package com.sap.ledger.view.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class BalanceResponse{

	private String bankName;
	private String borrowerName;
	private BigDecimal amountPaid;
	private int pendingEmis;
}

