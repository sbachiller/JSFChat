package controller.login;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.db.OperatorManager;


@ManagedBean
@RequestScoped
public class OperatorLoginController {
	
	static final String USER_NOT_EXIST = "User does not exist or incorrect password ";
	
	@ManagedProperty(value = "#{operator}") 
	private OperatorLoginBean operador;
	
	@EJB private OperatorManager operatorManager;
	
	public OperatorLoginBean getOperador() {
		return operador;
	}

	public void setOperador(OperatorLoginBean operator) {
		this.operador = operator;
	}
	
	public OperatorLoginController() {
		
	}

	public OperatorManager getOperatorManager() {
		return operatorManager;
	}

	public void setOperatorManager(OperatorManager operatorManager) {
		this.operatorManager = operatorManager;
	}
	
	public String verifyUser() {
		if (operatorManager.isARegisteredOperator(operador.getLogin(), operador.getPasswd())) {
			operador.setAsLogged();
			operador.setOperatorId(operatorManager.getOperatorByLogin(operador.getLogin()).getId());
			return "operatorChat";
		} else {
			if (operador.getLogin() != null && operador.getPasswd() != null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(USER_NOT_EXIST));
			}
			return "index";
		}
		
	}
	
	public String logout() {
		System.out.println("logout");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "index";
		
	}
	
	
	
	

}
