
package pizzaSQL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class Order {
	public static final String CANCELLED = "cancelled";
	public static final String DELIVERED = "delivered";
	public static final String INPROGRESS = "In Progress";
	public static final String MessageOrderNotFound = "Order not found";
	public static final String MessageSuccess = "Success";
	public static final String UnableToCancelAlreadyDelived = "Unable to cancel an already canceled or delivered order";
	public static final String UnableToCancelToLate = "Unable to cancel your order after 5 minutes have elapsed.";
	public static final String InvalidIdSupplied = "Invalid ID supplied";

	public static Order createOrder(Integer id, Integer customer_id, String status, String note, boolean takeaway,
			String payment_type, Delivery_address delivery_address) {
		return new Order(id, customer_id, status, new Date(), note, takeaway, payment_type, delivery_address);
	}

	private Integer order_id;
	private Integer customer_id;
	private String status;
	private Date ordered_at;
	private String note;
	private boolean takeaway;
	private String payment_type;
	private Delivery_address delivery_address;
	private List<Pizza> pizzas = new ArrayList<Pizza>();

	private Order(Integer order_id, Integer customer_id, String status, Date ordered_at, String note, boolean takeaway,
			String payment_type, Delivery_address delivery_address) {
		this.order_id = order_id;
		this.note = note;
		this.takeaway = takeaway;
		this.payment_type = payment_type;
		this.customer_id = customer_id;
		this.delivery_address = delivery_address;
		this.status = status;
		this.ordered_at = ordered_at;
	}

	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", takeaway=" + takeaway + ", payment_type=" + payment_type
				+ ", customer_id=" + customer_id + ", delivery_address=" + delivery_address + ", status=" + status
				+ ", ordered_at=" + ordered_at + ", note=" + note + "]";
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOrdered_at() {
		return ordered_at;
	}

	public void setOrdered_at(Date ordered_at) {
		this.ordered_at = ordered_at;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isTakeaway() {
		return takeaway;
	}

	public void setTakeaway(boolean takeaway) {
		this.takeaway = takeaway;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public Delivery_address getDelivery_address() {
		return delivery_address;
	}

	public void setDelivery_address(Delivery_address delivery_address) {
		this.delivery_address = delivery_address;
	}

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public void addPizza(Pizza pizza) {
		pizzas.add(pizza);
	}

	public static Order createRandomOrder() {

		Delivery_address deliveryAddress = Delivery_address.createRandomAddress();
		String randomWord = "la bal dsfdsf ";
		String payment_type = "credit card";
		Order order = Order.createOrder(0, getRandomCustomerId(), payment_type, randomWord, false, payment_type,
				deliveryAddress);
		order.addPizza(Pizza.createRandomPizza());
		return order;
	}

	private static Integer getRandomCustomerId() {
		Random rn = new Random();
		int answer = rn.nextInt(10) + 1;
		return answer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + order_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (order_id != other.order_id)
			return false;
		return true;
	}

 
}
