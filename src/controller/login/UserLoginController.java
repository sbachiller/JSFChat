package controller.login;

import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;

import model.core.ConversationsManager;
import model.db.CategoryDB;


@ManagedBean
@RequestScoped
public class UserLoginController {

	@EJB CategoryDB categoryDB;
	@EJB ConversationsManager convManager;
	
	@ManagedProperty(value = "#{user}")
	private UserLoginBean usuario;
	
	public UserLoginBean getUsuario() {
		return usuario;
	}

	public void setUsuario(UserLoginBean usuario) {
		this.usuario = usuario;
	}
	
	public Set<String> getCategories(){
		return categoryDB.getCategories();
	}
	
	public List<String> getSubCategories(String category){
		return categoryDB.getSubcategories(category);
	}
	
	public void categoryChanged(ValueChangeEvent event) {
		
	}
	
	public String createConversation() {
		System.out.println("Entrando a createConversation");
		long newId = convManager.createConversation(usuario.getName());
		usuario.setConversationId(newId);
		return "userChat";
	}
	
	
}
