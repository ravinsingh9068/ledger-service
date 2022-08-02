package com.sap.ledger.reqhandler;

import com.sap.ledger.view.response.BaseResponse;

public interface RequestHandler {

	BaseResponse handleCommandRequest();

}
