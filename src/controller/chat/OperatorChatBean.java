package controller.chat;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="opchat")
@SessionScoped
public class OperatorChatBean {
	
	private String history = "";
	private ConversationDetails currentConversation;
	private List<ConversationDetails> conversations;
	
	
	public ConversationDetails getCurrentConversation() {
		return this.currentConversation;
	}
	
	public void setCurrentConversation(ConversationDetails conversation) {
		this.currentConversation = conversation;
	}
	
	public String getHistory() {
		return this.history;
	}
	
	public void setHistory(String history) {
		this.history = history;
	}
	
	public List<ConversationDetails> getConversations(){
		return conversations;
	}
	
	public void setConversations(List<ConversationDetails> conversation) {
		this.conversations = conversation;
	}
	
	public static class ConversationDetails {
		
		private long conversationId;
		private String user;
		private String conversationLinkStyle;
		
		static final String UNSELECTED_CHAT_STYLE = "notSelectedChat";
		static final String SELECTED_CHAT_STYLE = "selectedChat";
		
		public ConversationDetails(long id, String user, String conversationLinkStyle)
		{
			this.conversationId = id;
			this.user = user;
			this.conversationLinkStyle = conversationLinkStyle;
		}
		
		public static ConversationDetails getUnselectedConversationDetailsInstance(long id, String user) {
			ConversationDetails unselected = new ConversationDetails(id,user,UNSELECTED_CHAT_STYLE);
			return unselected;
		}
		
		public static ConversationDetails getSelectedConversationDetailsInstance(long id, String user) {
			System.out.println("ID: " + id + " - User: " + user);
			ConversationDetails selected = new ConversationDetails(id,user,SELECTED_CHAT_STYLE);
			return selected;
		}

		public long getConversationId() {
			return this.conversationId;
		}

		public void setConversationId(long conversationId) {
			this.conversationId = conversationId;
		}
		
		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getConversationLinkStyle() {
			return conversationLinkStyle;
		}
		
		public void switchLinkStyle() {
			if (conversationLinkStyle == UNSELECTED_CHAT_STYLE){
				conversationLinkStyle = SELECTED_CHAT_STYLE;
			}else {
				conversationLinkStyle = UNSELECTED_CHAT_STYLE;
			}
		}
		
		public void setConversationLinkStyle(String conversationLinkStyle) {
			this.conversationLinkStyle = conversationLinkStyle;
		}
	}
}
