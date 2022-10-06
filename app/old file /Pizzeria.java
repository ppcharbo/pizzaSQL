package pizzaSQL;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1")
public class Pizzeria {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/pizza")
	public List<Pizza> pizza() {
		return PizzaMenu.getMenu().getPizzaList();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/pizza/{pizza_id}")
	public Response getPizza(@PathParam("pizza_id") int pizza_id) {

		Pizza pizza = PizzaMenu.search(pizza_id);

		if (pizza != null) {
			return Response.ok(pizza).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("{\"message\": \"Pizza not found\"} ").build();
		}
		// search my database and get a string representation and return it
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/order/{order_id}")
	public Response getOrder(@PathParam("order_id") String order_id) {

		if (!isInteger(order_id)) {
			return Response.status(400).entity("{\"message\": \""+Order.InvalidIdSupplied+"\"} ").build();
		}

		WorkingMemory wm = WorkingMemory.getInstance();

		OrderStatus orderStatus = wm.search(Integer.parseInt(order_id));

		if (orderStatus.getCode() == 200)
			return Response.ok(orderStatus).build();
		else
			return Response.status(Response.Status.NOT_FOUND)
					.entity("{\"message\": \"" + orderStatus.getMessage() + "\"} ").build();

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/order")
	public OrderStatus postOrder(Order order) {

		WorkingMemory wm = WorkingMemory.getInstance();
		OrderStatus orderStatus = wm.addOrder(order);
		return orderStatus;

	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/order/cancel/{order_id}")
	public Response cancelOrder(@PathParam("order_id") Integer order_id) {

		System.out.println("order_id " + order_id);

		WorkingMemory wm = WorkingMemory.getInstance();
		OrderStatus cancelOrder = wm.cancelOrder(order_id);

		int key = cancelOrder.getCode();
		switch (key) {
		case 200:
			return Response.ok(cancelOrder).build();
		default:
			return Response.status(key).entity("{\"message\": \"" + cancelOrder.getMessage() + "\"} ").build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/order/deliverytime/{order_id}")
	public Response getDeliveryTime(@PathParam("order_id") Integer order_id) {

		WorkingMemory wm = WorkingMemory.getInstance();

		OrderStatus orderStatus = wm.search(order_id);

		if (orderStatus.getCode() == 200)
			return Response.ok(orderStatus).build();
		else
			return Response.status(Response.Status.NOT_FOUND)
					.entity("{\"message\": \"" + orderStatus.getMessage() + "\"} ").build();

	}

	public boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}