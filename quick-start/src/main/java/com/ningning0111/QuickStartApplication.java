package com.ningning0111;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.websocket.WebSocketService;

@SpringBootApplication
public class QuickStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickStartApplication.class, args);
	}

	// API平台：https://app.infura.io/
	@Bean
	public Web3j web3j(){
		return Web3j.build(new HttpService("https://sepolia.infura.io/v3/{Your ID}"));
	}

	@Bean
	public WebSocketService webSocketService() {
		return new WebSocketService("wss://sepolia.infura.io/ws/v3/{Your ID}",true);
	}

}
