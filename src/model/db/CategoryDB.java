package model.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;


@Singleton
public class CategoryDB {

	private Hashtable<String,List<String>> categories = new Hashtable<String,List<String>>();
	
	public CategoryDB() {
		
	}
	
	@PostConstruct
	private void init() {
		
		List<String> subcat2 = new ArrayList<String>(Arrays.asList("s21","s22","s23"));
		List<String> subcat1 = new ArrayList<String>(Arrays.asList("s11","s12","s13"));
		List<String> subcat0 = new ArrayList<String>(Arrays.asList("s01","s02","s03"));
		
		categories.put("cat1", subcat1);
		categories.put("cat2", subcat2);
		categories.put("cat0", subcat0);
	}
	
	public Set<String> getCategories(){
		return categories.keySet();
		
	}
	
	public List<String> getSubcategories(String category){
		List<String> subcat = categories.get(category);
		
		return subcat;
	}
	
	public String getFirstCategory() {
		//Suponemos que nunca está vacía, si no podria fallar
		return categories.keys().nextElement();
		
	}
	
	
	
}
