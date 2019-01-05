package model.db;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import model.core.Operator;

@Singleton
public class OperatorDB {

	private Hashtable<Long,Operator> operators = new Hashtable<Long,Operator>();

	
	public OperatorDB() {
		
	}
	
	@PostConstruct
	private void init() {

		Long testid = 1L;
		Long testid2 = 2L;
		Operator testoperator = new Operator("test","test",testid);
		Operator testoperator2 = new Operator("op","op",testid2);
		
		operators.put(testid, testoperator);
		operators.put(testid2, testoperator2);
		
	}
	
	public Hashtable<Long,Operator> getOperators(){
		return operators;
		
	}

	
	
}
