package expenses.model.statistics;

import java.util.List;

public class PersonMonthsCategoriesExpenses {
	private String personName;
	private List<MonthCategoryExpenses> monthsCategoriesExpenses;
	
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public List<MonthCategoryExpenses> getMonthsCategoriesExpenses() {
		return monthsCategoriesExpenses;
	}
	public void setMonthsCategoriesExpenses(
			List<MonthCategoryExpenses> monthsCategoriesExpenses) {
		this.monthsCategoriesExpenses = monthsCategoriesExpenses;
	}
}
