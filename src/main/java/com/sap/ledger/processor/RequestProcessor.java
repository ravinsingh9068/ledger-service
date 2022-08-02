package com.sap.ledger.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import com.sap.ledger.reqhandler.BalanceReqHandler;
import com.sap.ledger.reqhandler.RequestHandler;
import com.sap.ledger.view.response.BalanceResponse;
import com.sap.ledger.view.response.BaseResponse;

@Component
public class RequestProcessor {
	
	@Value("${filepath}")
	private String filePath;
	
	@Autowired
	private MessageSource messages;
	
	@Autowired
	RequestHandlerFactory requestHandlerFactory;
	
	List<String> commands = new ArrayList<>();

	public RequestProcessor(String filepath){
		if (filepath ==null || "".equals(filepath)){
			throw new IllegalArgumentException(messages.getMessage("err.file.path.invalid", null, null));
		}
		this.filePath = filepath;
	}

	@PostConstruct
	public void ProcessCommands() throws NoSuchMessageException, IOException{
		List<String> commands = loadAllCommands();
		for (String command : commands){
			RequestHandler requestHandler = requestHandlerFactory.getRequestHandler(command);
			if (requestHandler != null){

				BaseResponse response = requestHandler.handleCommandRequest();
				if (requestHandler.getClass() == BalanceReqHandler.class && (response.getData()!=null)){
					var balanceResponse = (BalanceResponse)response;
					System.out.println(String.format("%1$s %2$s %3$s %4$s", 
							balanceResponse.getBankName(), balanceResponse.getBorrowerName(), balanceResponse.getAmountPaid(), balanceResponse.getPendingEmis()));
				}
			}
			else{
				System.out.println("Invalid Command");
			}
		}
	}

	private List<String> loadAllCommands() throws NoSuchMessageException, IOException{
		try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
			commands = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			throw new IOException(messages.getMessage("err.reading.file", null, null));
		}
		return commands;
	}
}
