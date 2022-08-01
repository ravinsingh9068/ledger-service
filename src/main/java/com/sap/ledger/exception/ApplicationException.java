package com.sap.ledger.exception;


public class ApplicationException extends Exception {
	
	private static final long serialVersionUID = -8006630353359097191L;
	protected String message;

	public ApplicationException(String message, Exception e) {
		e.printStackTrace();
		this.message = message;
	}
	
	public ApplicationException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

}
