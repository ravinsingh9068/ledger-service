package com.sap.ledger.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import lombok.Data;

@Data
public class LoanDetail
{
	private String bankName;
	private String borrowerName;
	private BigDecimal principalAmount;
	private int loanTenure;
	private BigDecimal rateOfInterest;
	private ArrayList<Payment> payments;
	private LocalDateTime createDate = LocalDateTime.MIN;

	public final BigDecimal getTotalAmountToBeRepaid(){
		if (this.getLoanTenure() > 0){
			return this.getPrincipalAmount().add(
					this.getPrincipalAmount().multiply(
							this.getRateOfInterest().multiply(
									new BigDecimal(this.getLoanTenure()))).divide(new BigDecimal(100)));
		}else{
			return new BigDecimal(0);
		}
	}

	public final BigDecimal getEmiAmount(){
		BigDecimal totalAmountToBeRepaid = getTotalAmountToBeRepaid();
		if (totalAmountToBeRepaid.compareTo(new BigDecimal(0)) > 0) {
			return new BigDecimal(Math.ceil((totalAmountToBeRepaid.divide(new BigDecimal(this.getLoanTenure() * 12))).doubleValue()));
		}else{
			return new BigDecimal(0);
		}
	}

	public final BigDecimal getLumpSumPaidTillEmiNumber(int emiNumber){
		if (this.getPayments()!= null && !this.getPayments().isEmpty()){
			return this.getPayments().stream().filter(x -> x.getEmiNumber() <= emiNumber).sum(x -> x.Amount);
		}
		return new BigDecimal(0);
	}
}
