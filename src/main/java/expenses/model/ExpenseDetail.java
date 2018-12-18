package expenses.model;

public class ExpenseDetail {
	private String personName;
	private Double koszt;
	private Long zwrotProcent;
	private Double zwrot;
	private String zwrotPersonName;
	private String shopName;
	private String categoryName;
	private String cityName;
	private String data;
	
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public Double getKoszt() {
		return koszt;
	}
	public void setKoszt(Double koszt) {
		this.koszt = koszt;
	}
	public Long getZwrotProcent() {
		return zwrotProcent;
	}
	public void setZwrotProcent(Long zwrotProcent) {
		this.zwrotProcent = zwrotProcent;
	}
	public Double getZwrot() {
		return zwrot;
	}
	public void setZwrot(Double zwrot) {
		this.zwrot = zwrot;
	}
	public String getZwrotPersonName() {
		return zwrotPersonName;
	}
	public void setZwrotPersonName(String zwrotPersonName) {
		this.zwrotPersonName = zwrotPersonName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public boolean isCorrect() {
		return personName != null &&
		!personName.isEmpty() &&
		koszt != null &&
		zwrotProcent != null &&
		zwrotProcent <=100 &&
		zwrotProcent >=0 &&
		zwrot != null &&
		zwrotPersonName != null &&
		!zwrotPersonName.isEmpty() &&
		shopName != null &&
		!shopName.isEmpty() &&
		categoryName != null &&
		!categoryName.isEmpty() &&
		cityName != null &&
		!cityName.isEmpty() &&
		data != null &&
		!data.isEmpty();
	}
}
