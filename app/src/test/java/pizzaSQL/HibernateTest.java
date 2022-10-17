package pizzaSQL;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.PreparedStatement;
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
		Integer postalCode = 1100;
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

	@Test
	public void findAllPendingOrderTest() throws Exception {
		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);
		Collection<Item> basket = new ArrayList<Item>();
		basket.add(hibernate.findItemById(1));
		Customer customer = hibernate.findCustomerById(1);
		Order completCheckOut = hibernate.completCheckOut(basket, customer, null);
		System.out.println("Created order " + completCheckOut);
		Collection<Order> collection = hibernate.findAllPendingOrder();
		for (Order order : collection) {
			if (order.getId().equals(completCheckOut.getId()))
				return;
		}
		fail("Could not find pending order ");

	}

	@Test
	public void testFindFreeRiders() throws Exception {

		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);

		Collection<Rider> findFreeRiders = hibernate.findFreeRiders();
		for (Rider rider : findFreeRiders) {
			System.out.println(rider);
		}
	}

	@Test
	public void testUpdateRidersStatus() throws Exception {

		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);

		Collection<Rider> findFreeRiders = hibernate.findFreeRiders();
		
		Rider rider = findFreeRiders.iterator().next();
		rider.setAvailable(false);
		hibernate.UpdateRidersStatus(rider);
		rider=hibernate.findRiderById(rider.getId());
		Boolean availabe = rider.getAvailable();
		assertFalse(availabe);
		
	}

	@Test
	public void testResetRiderCameBack() throws Exception {

		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);

		Collection<Rider> findFreeRiders = hibernate.findFreeRiders();
		
		Rider rider = findFreeRiders.iterator().next();
		rider.setAvailable(false);
		hibernate.UpdateRidersStatus(rider);
		rider=hibernate.findRiderById(rider.getId());
		Boolean availabe = rider.getAvailable();
		assertFalse(availabe);
		String sql = "update riders set cameBack = NOW() - INTERVAL 60  minute where id=?";
		PreparedStatement st = hibernate.conn.prepareStatement(sql);
		st.setInt(1, rider.getId());
		st.executeUpdate();
		
		hibernate.resetRiderCameBack();
		findFreeRiders = hibernate.findFreeRiders();
		for (Rider rider2 : findFreeRiders) {
			
			if(rider2.getId().equals(rider.getId()))
				return;
		}
		
		
		
		fail("no free rider founds ");
		
	}

	
	

	@Test
	public void testAssignCurrentOrder() throws Exception {

		Controller controller = new Controller();
		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);
		// just reset all riders 
         Collection <Rider> allRiders=hibernate.findAllRiders();
         for (Rider rider : allRiders) {
        	 rider.setAvailable(true);
			hibernate.UpdateRidersStatus(rider);
		}
		
		// first we must create the customer 
		String name = "pippo";
		Integer postalCode = 1100;
		String address = "Nouvlle";
		String email = RandomString.getAlphaNumericString(15);
		String phone = RandomString.getAlphaNumericString(15);
		String password = RandomString.getAlphaNumericString(15);
		
		Customer customer = hibernate.createCustomer(name, postalCode, address, email, phone, password);
		
		//we need to create an order 
		
		Collection<Item> basket = new ArrayList<Item>();
		basket.add(hibernate.findItemById(1));
		Order completCheckOut = hibernate.completCheckOut(basket, customer, null);
		System.out.println("Created order " + completCheckOut);
		Order myorder = hibernate.findOrderById(completCheckOut.getId());

		controller.listOfOrder();

	}
}
