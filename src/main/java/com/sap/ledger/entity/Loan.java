package com.sap.ledger.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity @Data @EqualsAndHashCode(callSuper=false)
@AllArgsConstructor @NoArgsConstructor
public class Loan{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String bankName;
	
	private String borrowerName;
	
	private BigDecimal principalAmount;
	
	private int loanTenure;
	
	private BigDecimal interestRate;
	
	private ArrayList<Payment> payments;
	
	private LocalDateTime createDate = LocalDateTime.MIN;

	public final BigDecimal getPendingAmount(){
		if (this.getLoanTenure() > 0){
			return this.getPrincipalAmount().add(
					this.getPrincipalAmount().multiply(
							this.getInterestRate().multiply(
									new BigDecimal(this.getLoanTenure()))).divide(new BigDecimal(100)));
		}else{
			return new BigDecimal(0);
		}
	}

	public final BigDecimal getPendingEmiAmount(){
		BigDecimal pendingAmount = getPendingAmount();
		if (pendingAmount.compareTo(new BigDecimal(0)) > 0) {
			return new BigDecimal(Math.ceil((pendingAmount.divide(new BigDecimal(this.getLoanTenure() * 12))).doubleValue()));
		}else{
			return new BigDecimal(0);
		}
	}

	public final BigDecimal getPaidAmountTillEmiNumberProvided(int emiNumber){
		if (this.getPayments()!= null && !this.getPayments().isEmpty()){
			return this.getPayments().stream()
					.filter(x -> x.getEmiNumber() <= emiNumber)
					.map(Payment::getAmount)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
		}
		return new BigDecimal(0);
	}
}
