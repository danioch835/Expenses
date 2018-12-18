package expenses.model.statistics;

import java.util.List;

public class PersonCategoriesExpenses {
	private String personName;
	private List<CategoryExpense> categoriesExpenses;
	
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public List<CategoryExpense> getCategoriesExpenses() {
		return categoriesExpenses;
	}
	public void setCategoriesExpenses(List<CategoryExpense> categoriesExpenses) {
		this.categoriesExpenses = categoriesExpenses;
	}
}
