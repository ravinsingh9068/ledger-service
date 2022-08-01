package com.sap.ledger.rest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/balance")
@Slf4j
public class BalanceController extends AbstractRestHandler {
	
	@Autowired
	//private MessageSource messages;

	@GetMapping(value = "/get")
	public JSONObject getBalance(@RequestParam String payload) throws Exception {
		log.debug("Requested payload is : " + payload);
		return null;
	}

	
	
	
	
}
