package expenses.builders;

import java.util.Date;
import java.util.List;

public class ExpenseStatisticRequest {
	private String personName;
	private List<String> categoriesNames;
	private String cityName;
	private String shopName;
	private Date fromDate;
	private Date toDate;

	public ExpenseStatisticRequest() {}
	
	public ExpenseStatisticRequest(String personName, List<String> categoriesNames, 
								   String cityName, String shopName, Date fromDate, Date toDate) {
		this.personName = personName;
		this.categoriesNames = categoriesNames;
		this.cityName = cityName;
		this.shopName = shopName;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
	
	public List<String> getCategoriesNames() {
		return categoriesNames;
	}
	public void setCategoriesNames(List<String> categoriesNames) {
		this.categoriesNames = categoriesNames;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
}
