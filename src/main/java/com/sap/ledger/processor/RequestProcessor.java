package com.sap.ledger.processor;

import static com.sap.ledger.constant.CommonConstants.BALANCE;
import static com.sap.ledger.constant.CommonConstants.LOAN;
import static com.sap.ledger.constant.CommonConstants.PAYMENT;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.sap.ledger.reqhandler.BalanceReqHandler;
import com.sap.ledger.reqhandler.LoanReqHandler;
import com.sap.ledger.reqhandler.PaymentReqHandler;
import com.sap.ledger.view.response.BalanceResponse;
import com.sap.ledger.view.response.BaseResponse;

@Component
public class RequestProcessor {

	@Autowired 
	Environment env;

	@Autowired
	private MessageSource messages;

	@Autowired
	PaymentReqHandler paymentReqHandler;

	@Autowired
	BalanceReqHandler balanceReqHandler;

	@Autowired
	LoanReqHandler loanReqHandler;

	List<String> commands = new ArrayList<>();


	@PostConstruct
	public void ProcessCommands() throws NoSuchMessageException, IOException {
		String filepath = env.getProperty("filepath");
		if (filepath == null || "".equals(filepath)) {
			throw new IllegalArgumentException(messages.getMessage("err.file.path.invalid", null, null));
		}
		
		List<String> commands = loadAllCommands(filepath);
		for (String command : commands) {
			String commandType = command.substring(0, command.indexOf(" "));
			BaseResponse response = null;
			switch (commandType) {
			case LOAN:
				response = loanReqHandler.handleCommandRequest(command);
				break;
			case PAYMENT:
				response = paymentReqHandler.handleCommandRequest(command);
				break;
			case BALANCE:
				response = balanceReqHandler.handleCommandRequest(command);
				break;
			default:
				break;
			}

			if (response.getData() != null && (response.getData() instanceof BalanceResponse)) {
				BalanceResponse balanceResponse = (BalanceResponse) response.getData();
				System.out.println(String.format("%1$s %2$s %3$s %4$s", balanceResponse.getBankName(),
						balanceResponse.getBorrowerName(), balanceResponse.getAmountPaid(),
						balanceResponse.getPendingEmis()));
			}
		}
	}

	private List<String> loadAllCommands(String filepath) throws NoSuchMessageException, IOException {
		try (BufferedReader br = Files.newBufferedReader(Paths.get(filepath))) {
			commands = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			throw new IOException(messages.getMessage("err.reading.file", null, null));
		}
		return commands;
	}
}
