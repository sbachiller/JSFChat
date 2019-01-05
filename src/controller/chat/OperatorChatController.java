package controller.chat;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import controller.chat.OperatorChatBean.ConversationDetails;
import controller.login.OperatorLoginBean;
import model.core.Conversation;
import model.core.ConversationsManager;
import model.core.Operator;
import model.db.OperatorManager;

@ManagedBean(name="opcontroller")
@RequestScoped
public class OperatorChatController {
	
	@EJB
	private OperatorManager operatorManager;
	
	@ManagedProperty(value = "#{operator}")
	private OperatorLoginBean operatorLoginBean;
	
	@ManagedProperty(value = "#{opchat}")
	private OperatorChatBean operatorChatBean;
	
	@EJB
	private ConversationsManager conversationsManager;
	
	public void setOperatorChatBean(OperatorChatBean ocb) {
		this.operatorChatBean = ocb;
	}
	
	public void setOperatorLoginBean(OperatorLoginBean olb) {
		this.operatorLoginBean = olb;
	}
	
	public void refreshConversations() {
		System.out.println("RefreshConversations");
		List<ConversationDetails> conversations = new ArrayList<ConversationDetails>();
		Operator op = getOperator();
		
		System.out.println("Refreshing conversations for operator " + op.getLogin());
		
		if (this.operatorChatBean.getCurrentConversation() == null) {
			Conversation opconv = op.getCurrentConversation();
			ConversationDetails tmpcd = ConversationDetails.getSelectedConversationDetailsInstance(opconv.getConversationID(), opconv.getUserName());
			this.operatorChatBean.setCurrentConversation(tmpcd);
		}
		List<Conversation> activeChats = op.getActiveChats();
		ConversationDetails newConv;
		for(Conversation c : activeChats) {
			if(this.operatorChatBean.getCurrentConversation().getConversationId() == c.getConversationID()) {
				newConv = operatorChatBean.getCurrentConversation();
			}
			else {
				newConv = ConversationDetails.getUnselectedConversationDetailsInstance(c.getConversationID(), c.getUserName());
			}
			
			conversations.add(newConv);
		}
		operatorChatBean.setConversations(conversations);
	}
	
	public void changeToConversation(ConversationDetails newCd) {
		System.out.println("changeToConversation function...");
		Operator op = getOperator();
		
		System.out.println("Trying to change to user " + newCd.getUser());
		
		ConversationDetails currentCd = operatorChatBean.getCurrentConversation();
		System.out.println("Current conversation assigned from " + currentCd.getUser());
		
		currentCd.switchLinkStyle();
		newCd.switchLinkStyle();
		
		operatorChatBean.setCurrentConversation(newCd);
		Conversation conv = conversationsManager.getConversationByID(newCd.getConversationId());
		
		op.setCurrentConversation(conv);
		
		operatorChatBean.setHistory(conv.toString());
		System.out.println("\n CONVERSATION:");
		System.out.println(conv.toString());
		
		
	}
	
	private Operator getOperator() {
		return operatorManager.getOperatorByID(operatorLoginBean.getOperatorId());
		
	}
}
