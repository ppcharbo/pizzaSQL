package pizzaSQL;

import org.junit.Test;

public class RandomStringTest {

	@Test
	public void test() {
		
		String string = RandomString.getAlphaNumericString(4);
		System.out.println(string);
	}

}
