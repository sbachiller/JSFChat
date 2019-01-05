package controller.login;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.db.CategoryDB;


@ManagedBean(name="user")
@SessionScoped
public class UserLoginBean {
	
	@EJB private CategoryDB categoryDB;
	private String name = null;
	private long conversationId;
	//Default selectedCategory
	private String selectedCategory = "cat0";
	private String selectedSubCategory;
	
	
	public CategoryDB getCategoryDB() {
		return categoryDB;
	}
	public void setCategoryDB(CategoryDB categoryDB) {
		this.categoryDB = categoryDB;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getConversationId() {
		return conversationId;
	}
	public void setConversationId(long conversationId) {
		this.conversationId = conversationId;
	}
	public String getSelectedCategory() {
		return selectedCategory;
	}
	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}
	public String getSelectedSubCategory() {
		return selectedSubCategory;
	}
	public void setSelectedSubCategory(String selectedSubCategory) {
		this.selectedSubCategory = selectedSubCategory;
	}
	


}
