package pizzaSQL.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Order {

	 
 
	@Override
	public String toString() {
		final int maxLen = 10;
		return "Order [details=" + (details != null ? toString(details, maxLen) : null) + ", id=" + id + ", customer=" + customer + ", rider=" + rider + ", price=" + price + ", readtAt=" + readtAt + ", deliveredAt=" + deliveredAt + ", delivered="
				+ delivered + ", discoutCode=" + discoutCode + "]";
	}

	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}

	Collection <Item> details=new ArrayList<Item>();
	private Integer id;
	private Customer customer;
	private Rider rider;
	private Double price;
	private Timestamp readtAt;
	private Timestamp deliveredAt;
	private Boolean delivered;
	private String discoutCode;

	/*
	 
	  +---------------+-------------+------+-----+---------+----------------+
| Field         | Type        | Null | Key | Default | Extra          |
+---------------+-------------+------+-----+---------+----------------+
| id            | int         | NO   | PRI | NULL    | auto_increment |
| idcustomer    | int         | NO   | PRI | NULL    |                |
| idrider       | int         | YES  | MUL | NULL    |                |
| price         | double      | NO   |     | NULL    |                |
| ready_at      | timestamp   | NO   |     | NULL    |                |
| picked_up_at  | timestamp   | YES  |     | NULL    |                |
| delivered     | tinyint     | YES  |     | NULL    |                |
| discount_code | varchar(10) | YES  |     | NULL    |                |
+---------------+-------------+------+-----+---------+----------------+
	  
	
	*/
	public Order(Integer id,Customer customer, Rider rider, Double price, Timestamp readtAt, Timestamp deliveredAt,Boolean delivered, String discoutCode) {
		this.id = id;
		this.customer = customer;
		this.rider = rider;
		this.price = price;
		this.readtAt = readtAt;
		this.deliveredAt = deliveredAt;
		this.delivered = delivered;
		this.discoutCode = discoutCode;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Rider getRider() {
		return rider;
	}

	public void setRider(Rider rider) {
		this.rider = rider;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Timestamp getReadtAt() {
		return readtAt;
	}

	public void setReadtAt(Timestamp readtAt) {
		this.readtAt = readtAt;
	}

	public Timestamp getDeliveredAt() {
		return deliveredAt;
	}

	public void setDeliveredAt(Timestamp deliveredAt) {
		this.deliveredAt = deliveredAt;
	}

	public Boolean getDelivered() {
		return delivered;
	}

	public void setDelivered(Boolean delivered) {
		this.delivered = delivered;
	}

	public String getDiscoutCode() {
		return discoutCode;
	}

	public void setDiscoutCode(String discoutCode) {
		this.discoutCode = discoutCode;
	}

	public Collection<Item> getDetails() {
		return details;
	}

	public void setDetails(Collection<Item> details) {
		this.details = details;
	}

	

}
