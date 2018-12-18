package expenses.model;

import java.util.Date;

public class Expense {
	private long id;
	private double cost;
	private long personId;
	private long shoppingCategoryId;
	private long shopId;
	private long cityId;
	private long zwrot;
	private long personZwrotId;
	private Date data;
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public long getZwrot() {
		return zwrot;
	}
	public void setZwrot(long zwrot) {
		this.zwrot = zwrot;
	}
	public long getPersonZwrotId() {
		return personZwrotId;
	}
	public void setPersonZwrotId(long personZwrotId) {
		this.personZwrotId = personZwrotId;
	}
	public long getCityId() {
		return cityId;
	}
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	public long getShoppingCategoryId() {
		return shoppingCategoryId;
	}
	public void setShoppingCategoryId(long shoppingCategoryId) {
		this.shoppingCategoryId = shoppingCategoryId;
	}
	public long getShopId() {
		return shopId;
	}
	public void setShopId(long shopId) {
		this.shopId = shopId;
	}
}
