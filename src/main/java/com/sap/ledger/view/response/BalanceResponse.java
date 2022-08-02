package com.sap.ledger.view.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class BalanceResponse extends BaseResponse{

	private String bankName;
	private String borrowerName;
	private BigDecimal amountPaid;
	private int pendingEmis;
}

