package com.sap.ledger.view.response;

import com.sap.ledger.view.ErrorView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
	private Object data;
	private ErrorView errors;
	public BaseResponse(Object data) {
		super();
		this.data = data;
	}
	
	
}
