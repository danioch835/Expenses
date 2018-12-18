package expenses.model;

public class MonthExpense {
	String personName;
	String personZwrotName;
	Double zwrot;
	Integer month;
	Integer year;
	
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonZwrotName() {
		return personZwrotName;
	}
	public void setPersonZwrotName(String personZwrotName) {
		this.personZwrotName = personZwrotName;
	}
	public Double getZwrot() {
		return zwrot;
	}
	public void setZwrot(Double zwrot) {
		this.zwrot = zwrot;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
}
