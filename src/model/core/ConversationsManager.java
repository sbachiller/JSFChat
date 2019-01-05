package model.core;

import java.util.Collection;
import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import model.db.OperatorManager;

@Stateless
public class ConversationsManager {
	
	private static long lastConversationID;
	private Hashtable<Long,Conversation> converMap = new Hashtable<Long,Conversation>();
	@EJB private OperatorManager operatorManager;

	
	@PostConstruct
	private void init() {
		System.out.println("init convManager");
		lastConversationID = System.currentTimeMillis();
	}
	
	public long createConversation(String username) {
		System.out.println("Entrando a convManager");
		long conversationId = generateConversationID();
		System.out.println("conversationID creada");
		Operator op = operatorManager.getAvailableOperator();
	
		Conversation c = new Conversation(conversationId, op.getId(), username);
		
		
		if (op.getActiveChats().size() == 0) {
			op.setCurrentConversation(c);
		}
		
		op.addConversation(c);
		converMap.put(conversationId, c);
		return conversationId;
		
	}
	
	public Conversation getConversationByID(long id) {
		System.out.println("Testing if ConversationID " + id + " is contained");
		return converMap.get(id);
	}
	
	public boolean containsConversationID(long id) {
		if(converMap.containsKey(id)) {
			return true;
		}else {
			return false;
		}
		
	}
	
	private static synchronized long generateConversationID() {
		lastConversationID = lastConversationID + 1;
		return lastConversationID;
	}
	
	
	
	
	
}
