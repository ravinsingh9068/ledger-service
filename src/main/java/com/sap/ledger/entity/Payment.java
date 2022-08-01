package com.sap.ledger.entity;

import java.math.BigDecimal;

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
public class Payment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private int emiNumber;
	private BigDecimal amount;
}
