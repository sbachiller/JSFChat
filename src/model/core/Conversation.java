package model.core;

import java.util.ArrayList;
import java.util.List;

public class Conversation {

	private long conversationID;
	private long operatorID;
	private String userName;
	private List<Message> messages = new ArrayList<Message>();
	
	public Conversation(long conversationID, long operatorID, String userName) {
		this.conversationID = conversationID;
		this.operatorID = operatorID;
		this.userName = userName;
	}

	public long getConversationID() {
		return conversationID;
	}

	public long getOperatorID() {
		return operatorID;
	}

	public String getUserName() {
		return userName;
	}
	
	public void addMessage(Message msg) {
		messages.add(msg);
	}
	
	public String toString() {

		String history = "";
		for( Message m : messages) {
			history+= m.toString();
		}
		return history;
		
	}
	
	
}
