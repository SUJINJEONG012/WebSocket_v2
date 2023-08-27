package com.websocket.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class ChatHandler extends TextWebSocketHandler {
	
	private static List<String> onlineList = new ArrayList<>();
	private static List<WebSocketSession> sessionList = new ArrayList<>();
	Map<String, WebSocketSession> userSession = new HashMap<>();
}
