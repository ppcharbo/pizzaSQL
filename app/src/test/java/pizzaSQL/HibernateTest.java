package pizzaSQL;

import static org.junit.Assert.*;

import org.junit.Test;

import pizzaSQL.model.Customer;
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
		
		Integer id=1;
		Order  o  = hibernate.findOrderById(id);
		System.out.println(o);
		assertTrue(id.equals(o.getId()));
	}
	
	
	@Test
	public void testCustomer() throws Exception {
		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);
		
		Integer id=1;
		Customer  o  = hibernate.findCustomerById(id);
		assertTrue(id.equals(o.getId()));
	}
	
	@Test
	public void testCreateCustomer() throws Exception {
		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);
		
		
		String name=RandomString.getAlphaNumericString(5);
		Integer postalCode=2020;
		String address=RandomString.getAlphaNumericString(15);
		String email=RandomString.getAlphaNumericString(15);
		String phone=RandomString.getAlphaNumericString(15);
		String password=RandomString.getAlphaNumericString(15);
		Customer  o  = hibernate.createCustomer(  name,   postalCode,   address,   email,   phone,   password);
		
		assertTrue(name.equals(o.getName()));
	}
	
	@Test
	public void testFindRiderById() throws Exception {
		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);
		
		Integer id=1;
		Rider  o  = hibernate.findRiderById(id);
		assertTrue(id.equals(o.getId()));
	}

}
