package expenses.model.statistics;

import java.util.List;

public class PersonMonthsExpenses {
	private String personName;
	List<MonthExpense> monthsExpenses;
	
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public List<MonthExpense> getMonthsExpenses() {
		return monthsExpenses;
	}
	public void setMonthsExpenses(List<MonthExpense> monthsExpenses) {
		this.monthsExpenses = monthsExpenses;
	}
}
