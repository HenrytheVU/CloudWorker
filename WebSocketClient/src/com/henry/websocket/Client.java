package com.henry.websocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import javax.websocket.DeploymentException;

public class Client {
	
	public static void main(String[] args) throws URISyntaxException, DeploymentException, IOException {
		
		
		WebSocketClientEndpoint01 wsEndpoint = new WebSocketClientEndpoint01();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String message = null;
		System.out.println("Client running...");
		
		while(true) {
			System.out.println("How many pictures should be compared?");
			message = br.readLine();
			if("-close".equals(message)) {
				return;
			}
			if(isInt(message)) {
				String jobs  = LoadBalancer.calculateJobsAsCSV(Integer.parseInt(message));
				wsEndpoint.sendMessage(jobs);
			} else {
				System.out.println("Please enter a number!");
			}
		}
		
		
	}
	
	public static boolean isInt(String s) {
		try{
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
