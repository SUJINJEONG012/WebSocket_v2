package com.websocket.handler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class ChatHandler extends TextWebSocketHandler {

	private static List<String> onlineList = new ArrayList<>();
	private static List<WebSocketSession> sessionList = new ArrayList<>();
	Map<String, WebSocketSession> userSession = new HashMap<>();
	
	ObjectMapper json = new ObjectMapper();
	
	//Message
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		//json test
		Map<String, Object> dataMap = new HashMap<>();
		
		//master status
		String masterStatus = null;
		if(userSession.containsKey("master")) {
			masterStatus = "online";
		}else {
			masterStatus = "offline";
		}
	
		//sending time
		LocalDateTime currentTime = LocalDateTime.now();
		String time = currentTime.format(DateTimeFormatter.ofPattern("hh:mm a, E"));
		
		//message data
		String senderId = (String) session.getAttributes().get("sessionId");
		String payload = message.getPayload();
		
		log.info("payload >>>>> "+ payload);
		
		dataMap = jsonToMap(payload);
		dataMap.put("senderId", senderId);
		dataMap.put("time", time);
		dataMap.put("masterStatus", masterStatus);
		dataMap.put("onlineList", onlineList);
		
		String receiverId = (String) dataMap.get("receiverId");
		
		log.info("final dataMap >>>>>>>  " + dataMap);
		
		//send a message
		System.out.println("receiver session >>" + userSession.get(receiverId));
		String msg = json.writeValueAsString(dataMap);
		
		
		if(userSession.get(receiverId) != null) {
			userSession.get(receiverId).sendMessage(new TextMessage(msg));
		}
		//send a message myself
		if(!senderId.equals(receiverId)) {
			dataMap.put("receiverId", senderId);
			msg=json.writeValueAsString(dataMap);
			session.sendMessage(new TextMessage(msg));
		}
	}	
	
	
	
}
