package pizzaSQL;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import pizzaSQL.model.Customer;
import pizzaSQL.model.Ingredients;
import pizzaSQL.model.Item;
import pizzaSQL.model.Order;
import pizzaSQL.model.Rider;

public class HibernateTest {

	@Test
	public void testFindDiscout() throws Exception {
		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);

		Boolean duplicate = hibernate.findDiscountDuplicate("abc");
		assertFalse(duplicate);
	}

	@Test
	public void testFindOrder() throws Exception {
		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);

		Integer id = 1;
		Order o = hibernate.findOrderById(id);
		System.out.println(o);
		assertTrue(id.equals(o.getId()));
		assertEquals(14, o.getDetails().size());
	}

	@Test
	public void testCustomer() throws Exception {
		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);

		Integer id = 1;
		Customer o = hibernate.findCustomerById(id);
		assertTrue(id.equals(o.getId()));
	}

	@Test
	public void testCreateCustomer() throws Exception {
		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);

		String name = RandomString.getAlphaNumericString(5);
		Integer postalCode = 2020;
		String address = RandomString.getAlphaNumericString(15);
		String email = RandomString.getAlphaNumericString(15);
		String phone = RandomString.getAlphaNumericString(15);
		String password = RandomString.getAlphaNumericString(15);
		Customer o = hibernate.createCustomer(name, postalCode, address, email, phone, password);

		assertTrue(name.equals(o.getName()));
	}

	@Test
	public void testFindRiderById() throws Exception {
		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);

		Integer id = 1;
		Rider o = hibernate.findRiderById(id);
		assertTrue(id.equals(o.getId()));
	}

	@Test
	public void testFindItemsByOrderId() throws Exception {
		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);
		Integer id = 1;
		Collection<Item> collection = hibernate.findItemsByOrder(id);
		assertEquals(14, collection.size());
		for (Item item : collection) {
			System.out.println(item);
		}

	}

	@Test
	public void testPrintOrder() throws Exception {
		Controller controller = new Controller();
		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);
		Integer id = 1;
		Order order = hibernate.findOrderById(id);
		controller.printConfirmation(order);

	}

	@Test
	public void testFindPrice() throws Exception {

		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);
		Integer id = 1;
		Collection<Item> basket = new ArrayList<Item>();

		Item item = hibernate.findItemById(id);
		Collection<Ingredients> ingredients = item.getIngredients();
		for (Ingredients i : ingredients) {
			System.out.println(i);
		}
		basket.add(item);
		double price = hibernate.findPrice(basket, "abdd");
		System.err.println(price);

	}

	@Test
	public void findIngredientByItem() throws Exception {
		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);
		Integer id = 1;
		Collection<Ingredients> collection = hibernate.findAllIngredients(id);
		System.out.println(collection);

	}

	@Test
	public void findAllOrdersInFiveMinutes() throws Exception {
		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);
		Collection<Item> basket = new ArrayList<Item>();
		basket.add(hibernate.findItemById(1));
		Customer customer = hibernate.findCustomerById(1);
		Order completCheckOut = hibernate.completCheckOut(basket, customer, null);
		System.out.println("Created order " + completCheckOut);
		Collection<Order> collection = hibernate.findAllOrdersInFiveMinutes();
		for (Order order : collection) {
			if (order.getId().equals(completCheckOut.getId()))
				return;
		}
		fail("Could not find order to cancel ");

	}

	@Test
	public void deleteOrdersInFiveMinutes() throws Exception {
		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);
		Collection<Item> basket = new ArrayList<Item>();
		basket.add(hibernate.findItemById(1));
		Customer customer = hibernate.findCustomerById(1);
		Order completCheckOut = hibernate.completCheckOut(basket, customer, null);
		System.out.println("Created order " + completCheckOut);
		Collection<Order> collection = hibernate.findAllOrdersInFiveMinutes();
		Order toDelete = null;
		for (Order order : collection) {
			if (order.getId().equals(completCheckOut.getId())) {
				toDelete = order;
				break;
			}
		}
		assertNotNull(toDelete);

		hibernate.deleteOrders(toDelete.getId());

		Order deletedOrder = hibernate.findOrderById(toDelete.getId());
		assertNull(deletedOrder);
	}

}
