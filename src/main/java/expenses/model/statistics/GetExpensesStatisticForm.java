package expenses.model.statistics;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class GetExpensesStatisticForm {
	String personName;
	String[] categories;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date dateFrom;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date dateTo;
	
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String[] getCategories() {
		return categories;
	}
	public void setCategories(String[] categories) {
		this.categories = categories;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
}
