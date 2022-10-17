package pizzaSQL;

import static org.junit.Assert.*;

import org.junit.Test;

public class HibernateTest {

	@Test
	public void testFindDiscout() throws Exception {
		Hibernate hibernate = new Hibernate(Controller.user, Controller.passwd, Controller.URL);
		
		Boolean duplicate = hibernate.findDiscountDuplicate("abc");
		assertFalse(duplicate);
	}

}
