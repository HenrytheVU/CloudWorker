package com.henry.websocket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

public class WebSocketClientEndpoint02 {
	
	Session session = null;
	
	public WebSocketClientEndpoint02() throws URISyntaxException, DeploymentException, IOException {
		URI uri = new URI("ws://workervm02.cloudapp.net/CloudWorker/workerendpoint");
		ContainerProvider.getWebSocketContainer().connectToServer(this, uri);
	}
	
	@OnOpen
	public void processOpen(Session session) {
		this.session = session;
	}
	
	@OnMessage
	public void processMessage(String msg) {
		System.out.println(msg);
	}
	
	public void sendMessage(String msg) {
		try {
			session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendObject(Object data) {
		try {
			session.getBasicRemote().sendObject(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@OnClose
	public void processClose() {
		try {
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
