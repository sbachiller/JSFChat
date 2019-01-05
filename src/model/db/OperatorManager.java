package model.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import model.core.Operator;

@Stateless
public class OperatorManager {
	
	@EJB 
	OperatorDB operatordb;
	
	public OperatorManager() {
		
	}
	
	public boolean isARegisteredOperator(String username, String password) {
		
		boolean registered = false;
		Hashtable<Long,Operator> operators = operatordb.getOperators();
		Collection<Operator> ops = operators.values();
		
		for (Operator o : ops) {
			if (o.getLogin().equals(username)) {
				if (o.getPassword().equals(password)) {
					registered = true;
					o.setOnline();
				}
			}
		}
		
		return registered;
	}
	
	public Operator getOperatorByLogin(String login) {
		Operator search = null;
		Hashtable<Long,Operator> operators = operatordb.getOperators();
		Collection<Operator> ops = operators.values();
		
		for (Operator o : ops) {
			if (o.getLogin().equals(login)) {
				search = o;
				break;
			}
		}
		return search;
	}
	
	public Operator getOperatorByID(long id) {
		Operator search = null;
		Hashtable<Long,Operator> operators = operatordb.getOperators();
		Collection<Operator> ops = operators.values();
		
		for (Operator o : ops) {
			if (o.getId() == id) {
				search = o;
				break;
			}
		}
		return search;
	}
	
	
	public Operator getAvailableOperator() {
		Hashtable<Long,Operator> operators = operatordb.getOperators();
		Collection<Operator> ops = operators.values();
		int min = Integer.MAX_VALUE;
		Operator minOp = null;
		
		for (Operator o : ops) {
			System.out.println(o.getLogin());
			if (o.isOnline()) {
				if (o.getNumberOfActiveChats() < min) {
					min = o.getNumberOfActiveChats();
					minOp = o;
				}
			}
		}
		return minOp;
	}
}
