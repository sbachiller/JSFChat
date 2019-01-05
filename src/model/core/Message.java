package model.core;

public class Message {

	private String message = null;
	private String from = null;
	public static final String REFRESH_MSG = "REFRESH_MSG";
	
	public Message(String message, String from) {
		this.message = message;
		this.from = from;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getFrom() {
		return from;
	}
	
	public String toString() {
		return message + "\n";
		
	}
	
	
	
}
