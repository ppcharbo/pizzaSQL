package pizzaSQL;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class WorkingMemory {

	int order_id = 0;
	private static WorkingMemory workingMemory;
	private List<OrderStatus> orderList = new ArrayList<OrderStatus>();

	public static WorkingMemory getInstance() {

		if (workingMemory == null)
			workingMemory = new WorkingMemory();

		return workingMemory;

	}

	public OrderStatus search(Integer orderId) {
		for (OrderStatus orderStatus : orderList) {
			if (orderStatus.getOrder().getOrder_id().equals(orderId)) {
				orderStatus.setCode(200);
				orderStatus.setMessage(Order.INPROGRESS);
				return orderStatus;
			}
		}
		OrderStatus orderStatus = OrderStatus.createEmptyOrderStatus();
		orderStatus.setCode(404);
		orderStatus.setMessage(Order.MessageOrderNotFound);
		return orderStatus;

	}

	public OrderStatus addOrder(Order order) {
		order.setOrder_id(order_id++);
		
		OrderStatus orderStatus = OrderStatus.createOrderStatus(order);
		orderList.add(orderStatus);
		return orderStatus;
	}

	public OrderStatus cancelOrder(Order order) {

		return cancelOrder(order.getOrder_id());
	}

	public OrderStatus cancelOrder(Integer order_id) {

		// set the default as order not found
		OrderStatus orderStatusFound = null;
		Date now = new Date();

		for (@SuppressWarnings("rawtypes")
		Iterator iterator = orderList.iterator(); iterator.hasNext();) {

			orderStatusFound = (OrderStatus) iterator.next();

			// we found the order
			if (order_id.equals(orderStatusFound.getOrder().getOrder_id())) {

				// check if the order is already cancelled or delivered
				if (orderStatusFound.getStatus().equals(Order.CANCELLED)
						|| orderStatusFound.getStatus().equals(Order.DELIVERED)) {
					orderStatusFound.setCode(422);
					orderStatusFound.setMessage(Order.UnableToCancelAlreadyDelived);

				}
				// now we check if is not too late !!
				else if (now.getTime() - orderStatusFound.getOrdered_at().getTime() > 5 * 60 * 1000) {
					orderStatusFound.setMessage(Order.UnableToCancelToLate);
					orderStatusFound.setCode(412);
					// we can cancel the order
				} else {
					orderStatusFound.setStatus(Order.CANCELLED);
					orderStatusFound.getOrder().setStatus(Order.CANCELLED);
					orderStatusFound.setMessage(Order.MessageSuccess);
					orderStatusFound.setCode(200);
				}
				return orderStatusFound;

			}
		}

		orderStatusFound = new OrderStatus();
		orderStatusFound.setMessage(Order.MessageOrderNotFound);
		orderStatusFound.setCode(404);
		return orderStatusFound;
	}

	@Override
	public String toString() {
		return "WorkingMemory [orderList=" + orderList + "]";
	}

}
