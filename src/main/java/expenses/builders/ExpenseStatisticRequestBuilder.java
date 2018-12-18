package expenses.builders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpenseStatisticRequestBuilder {
	
	private String personName;
	private List<String> categoriesNames;
	private String cityName;
	private String shopName;
	private Date fromDate;
	private Date toDate;
	
	public ExpenseStatisticRequestBuilder setPersonName(String personName) {
		this.personName = personName;
		return this;
	}
	
	public ExpenseStatisticRequestBuilder setCategoriesNames(List<String> categoriesNames) {
		this.categoriesNames = categoriesNames;
		return this;
	}
	
	public ExpenseStatisticRequestBuilder addCategoryName(String categoryName) {
		if(this.categoriesNames == null)
			this.categoriesNames = new ArrayList<String>();
		
		this.categoriesNames.add(categoryName);
		return this;
	}
	
	public ExpenseStatisticRequestBuilder setCityName(String cityName) {
		this.cityName = cityName;
		return this;
	}
	
	public ExpenseStatisticRequestBuilder setShopName(String shopName) {
		this.shopName = shopName;
		return this;
	}
	
	public ExpenseStatisticRequestBuilder setDateFrom(Date fromDate) {
		this.fromDate = fromDate;
		return this;
	}
	
	public ExpenseStatisticRequestBuilder setDateTo(Date toDate) {
		this.toDate = toDate;
		return this;
	}
	
	public ExpenseStatisticRequest build() {
		return new ExpenseStatisticRequest(personName, categoriesNames, cityName, shopName, fromDate, toDate);
	}
	
}
