package com.sap.ledger.view;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ErrorView implements Serializable{

	private static final long serialVersionUID = 1L;

	private HTTPStatus errorCode;

	private String errorMessage;
	
}
