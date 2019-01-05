package model.core;

import java.util.ArrayList;
import java.util.List;

public class Operator {

	private boolean online = false;
	private String login = null;
	private String passwd = null;
	private long id;
	private Conversation currentConversation;
	private List<Conversation> activeChats = new ArrayList<Conversation>();

	public Operator(String login, String password, long id) {
		//super();
		this.login = login;
		this.passwd = password;
		this.id = id;
	}
	
	public List<Conversation> getActiveChats() {
		return activeChats;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return passwd;
	}

	public long getId() {
		return id;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline() {
		this.online = true;
	}

	public Conversation getCurrentConversation() {
		return currentConversation;
	}

	public void setCurrentConversation(Conversation currentConversation) {
		this.currentConversation = currentConversation;
	}
	
	public int getNumberOfActiveChats() {
		return activeChats.size();
		
	}
	
	public void addConversation(Conversation conver) {
		activeChats.add(conver);
	}
	
	
	
	
	
}
