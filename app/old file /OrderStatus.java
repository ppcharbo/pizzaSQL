package pizzaSQL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class OrderStatus implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static OrderStatus createOrderStatus(Order order) {
		OrderStatus orderStatus = new OrderStatus();

		
		
		Date now = new Date(System.currentTimeMillis());
		Date deliver = new Date(System.currentTimeMillis() + generateDeliveryTime());
		orderStatus.setOrdered_at(now);
		orderStatus.setDelivery_time(deliver);
		
		orderStatus.updateOrder(order);
		
		orderStatus.setStatus(Order.INPROGRESS);
		
		if(order!=null)
			order.setStatus(Order.INPROGRESS);
		return orderStatus;
	}

	private void updateOrder(Order order) {
		List<Pizza> pizzas = order.getPizzas();
		PizzaMenu menu = PizzaMenu.getMenu();
		
		ArrayList<Pizza> newPizzaList = new ArrayList<Pizza>();
		for (Pizza pizza : pizzas) {
			Pizza newPizza = menu.getPizzaId(pizza.getPizza_id());
			newPizzaList.add(newPizza);
		}
		order.setPizzas(newPizzaList);
		setOrder(order);
		
	}

	private static int generateDeliveryTime() {

		Random r = new Random();
		int low = 1000 * 60 * 10; // min delivery time 10 min
		int high = 1000 * 60 * 60; // max delivery time 60 min 1h
		return r.nextInt(high - low) + low;

	}

	
	private Order order;
	private String status;
	private Date ordered_at;
	private Date delivery_time;
	private String message;
	private int  code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDelivery_time() {
		return delivery_time;
	}

	public void setDelivery_time(Date delivery_time) {
		this.delivery_time = delivery_time;
	}

	public Date getOrdered_at() {
		return ordered_at;
	}

	public void setOrdered_at(Date ordered_at) {
		this.ordered_at = ordered_at;
	}

	@Override
	public String toString() {
		return "OrderStatus [order=" + order + ", status=" + status + ", ordered_at=" + ordered_at + ", delivery_time="
				+ delivery_time + "]";
	}

	public static OrderStatus createOrderStatus() {
		
		return createOrderStatus(null);
	}

	public static OrderStatus createEmptyOrderStatus() {
		 
		return new OrderStatus();
	}

	
}
