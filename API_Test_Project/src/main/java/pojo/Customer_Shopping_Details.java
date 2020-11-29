package pojo;

public class Customer_Shopping_Details {
	private String session_id;
	private String product_id;
	private String name;
	private String quantity;
	
	public String getSession(String session_id) {
		return session_id;
	}
	public void setSession(String session_id) {
		this.session_id = session_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	

}
