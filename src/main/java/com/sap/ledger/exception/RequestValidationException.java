package com.sap.ledger.exception;

/**
 * for HTTP 412 errors
 */
public final class RequestValidationException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;

	public RequestValidationException() {
        super();
    }

    public RequestValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestValidationException(String message) {
        super(message);
    }

    public RequestValidationException(Throwable cause) {
        super(cause);
    }
}