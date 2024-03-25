package com.ningning0111;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.websocket.WebSocketService;

import java.net.ConnectException;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class QuickStartApplicationTests {

	@Autowired
	private Web3j web3j;

	@Autowired
	private WebSocketService webSocketService;

	/**
	 * 获取账户信息
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@Test
	public void accountBalanceTest() throws ExecutionException, InterruptedException {
		EthGetBalance ethGetBalance = web3j.ethGetBalance("0xbb9F8B0a410b6E5d82B9Db0AD0CfEdB2C8b5dF08",
						DefaultBlockParameterName.fromString(DefaultBlockParameterName.LATEST.name())
				).sendAsync().get();
		System.out.println(ethGetBalance.getBalance());
	}

	/**
	 * 获取Gas价格
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@Test
	public void gasTest() throws ExecutionException, InterruptedException {
		EthGasPrice ethGasPrice = web3j.ethGasPrice().sendAsync().get();
		System.out.println(ethGasPrice.getGasPrice());
	}

	@Test
	public void test() throws ExecutionException, InterruptedException {
		EthGetTransactionReceipt ethGetTransactionReceipt = web3j.ethGetTransactionReceipt("0x9030edd43f8ae6c4ed49bcbc11dd7d6f6ce2798e8bb1c5ea4f1e130780fec74a").sendAsync().get();
		System.out.println(ethGetTransactionReceipt);
	}

	@Test
	public void test1() throws ConnectException, InterruptedException {
		webSocketService.connect();
		Web3j web3jClient = Web3j.build(webSocketService);
		web3jClient.replayPastBlocksFlowable(DefaultBlockParameterName.fromString("earliest"), true).subscribe(ethBlock -> {
			System.out.println(ethBlock.getBlock().getHash());
		});
		Thread.sleep(30000);
		webSocketService.close();
	}



}
