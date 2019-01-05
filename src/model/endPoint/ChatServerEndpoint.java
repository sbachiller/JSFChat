package model.endPoint;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import model.core.Conversation;
import model.core.ConversationsManager;
import model.core.Message;
import model.core.Operator;
import model.db.OperatorManager;

@ServerEndpoint(value = "/chat/{channel}")
public class ChatServerEndpoint {
	
	@EJB ConversationsManager convManager;
	@EJB OperatorManager opManager;
	
	private static ConcurrentHashMap<Long,Session> channelSessionMapping = new ConcurrentHashMap<>();

	@OnMessage
	public void handleMessage(String message, Session session) {
		System.out.println("msg: " + message);
		long channel = Long.valueOf(session.getPathParameters().get("channel"));
		long toChannel;
		boolean hasToBeSent;
		Conversation conv;
		Message m;

		
		//Missatge del usuari
		if(doesSessionBelongToUser(channel)) {
			System.out.println("Message from user");
			conv = convManager.getConversationByID(channel);
			toChannel = conv.getOperatorID();
			System.out.println("Assigned operator " + toChannel);
			
			Operator o = opManager.getOperatorByID(toChannel);
			
			//Operador mirant el xat del usuari o no
			if (o.getCurrentConversation().getConversationID() == conv.getConversationID()) {
				hasToBeSent = true;
			}else {
				hasToBeSent = false;
			}
			
			m = new Message(message,conv.getUserName());
		
		//Missatge del operador
		}else {
			System.out.println("Message from operator");
			System.out.println("Channel found: " + channel);
			Operator op = opManager.getOperatorByID(channel);
			System.out.println("Operator instance found at " + op);
			System.out.println("Operator ID: " + op.getId());
			toChannel = op.getCurrentConversation().getConversationID();
			System.out.println("Sending message to user " + toChannel);
			conv = convManager.getConversationByID(toChannel);
			System.out.println("Message sent to channel " + conv);
			hasToBeSent = true;
			
			m = new Message(message,op.getLogin());
			
		}
		
		//Enviar missatge
		if (hasToBeSent == true) {
			System.out.println("Sesiones: ");
			System.out.println(channelSessionMapping.keys());
			session = channelSessionMapping.get(toChannel);
			System.out.println("Sending to channel " + toChannel);
			System.out.println(session.getId());
			if (session != null) {
				try {
					System.out.println("Sending message...");
					session.getBasicRemote().sendText(message);
				} catch (IOException e) {
					System.out.println("Not sent");
					e.printStackTrace();
				}	
			}
		}
		
		conv.addMessage(m);	
	}
	
	@OnOpen
	public void onOpen(Session session, @PathParam("channel") String channel){
		sendRefreshMsgIfUserOpenedASession(channel,session); 
		channelSessionMapping.put(Long.valueOf(channel), session);
		
	}
	
	@OnClose
	public void onClose(Session session, @PathParam("channel") String channel) {
		channelSessionMapping.remove(channel, session);
	}
	
	public boolean doesSessionBelongToUser(long channel) {
		boolean userSession = false;
		if (convManager.containsConversationID(channel)){
			userSession = true;
		}
		return userSession;
	}
	
	public void sendRefreshMsgIfUserOpenedASession(String s, Session session) {
		long channel = Long.valueOf(s);
		
		if(doesSessionBelongToUser(channel)) {
			try {
				System.out.println("Trying to find operator for user " + s);
				
				Conversation conv = convManager.getConversationByID(channel);
				long toChannel = conv.getOperatorID();
				System.out.println("OperatorID found: " + toChannel);
				session = channelSessionMapping.get(toChannel);
				
				System.out.println("Sending REFRESH_MSG...");
				session.getBasicRemote().sendText(Message.REFRESH_MSG);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Not sent REFRESH_MSG");
		}
	}

}
