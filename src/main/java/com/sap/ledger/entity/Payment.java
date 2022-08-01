package com.sap.ledger.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Payment
{
	private int emiNumber;
	private BigDecimal amount;
}
