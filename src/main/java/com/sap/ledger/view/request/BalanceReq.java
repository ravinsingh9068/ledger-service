package com.sap.ledger.view.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BalanceReq
{
	private String bankName;
	private String borrowerName;
	private int remainingEMIs;

}
