package com.sap.ledger.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sap.ledger.exception.DataFormatException;
import com.sap.ledger.exception.RequestValidationException;
import com.sap.ledger.exception.ResourceNotFoundException;
import com.sap.ledger.view.ErrorView;
import com.sap.ledger.view.HTTPStatus;

/**
 * This class is meant to be extended by all REST resource "controllers". It
 * contains exception mapping and other common REST API functionality
 */
@ControllerAdvice
public abstract class AbstractRestHandler implements ApplicationEventPublisherAware {

	protected ApplicationEventPublisher eventPublisher;
	
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public @ResponseBody ErrorView handleException(Exception ex, HttpServletRequest request) {
		ex.printStackTrace();
		return new ErrorView(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataFormatException.class)
	public @ResponseBody ErrorView handleDataFormatException(DataFormatException ex, HttpServletRequest request) {
		ex.printStackTrace();
		return new ErrorView(HTTPStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public @ResponseBody ErrorView handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
		ex.printStackTrace();
		return new ErrorView(HTTPStatus.NOT_FOUND, ex.getMessage());
	}

	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	@ExceptionHandler(RequestValidationException.class)
	public @ResponseBody ErrorView handleRequestValidationException(RequestValidationException ex, HttpServletRequest request) {
		ex.printStackTrace();
		return new ErrorView(HTTPStatus.PRECONDITION_FAILED, ex.getMessage());
	}
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.eventPublisher = applicationEventPublisher;
	}

}