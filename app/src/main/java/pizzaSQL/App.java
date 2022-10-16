/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package pizzaSQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;

import pizzaSQL.model.Ingredients;
import pizzaSQL.model.Item;

public class App {
	/*
	 * FOR LOGIN:
	 */
	public static final String dbName = "pizza";
	public static final String user = "root";
	public static final String passwd = "dio";
	public static final String URL = "jdbc:mysql://127.0.0.1/" + dbName;

	protected static String customerId = "";
	protected static String customerEmail = "";
	protected static String customerPhone = "";
	protected static String customerPostcode = "";
	protected static Connection conn;

	private Hibernate hibernate;
public App() throws  Exception {
	
	hibernate = new Hibernate(user,passwd,URL);
}
	public void mainLoop(Boolean showId) throws Exception {

		Scanner s;

		loop: while (true) {
			System.out.println(" ");
			System.out.println("""
					1 - Make an order
					2 - List all available Pizzas
					3 - List all available Drinks
					4 - List all available Desserts
					5 - List of current orders
					6 - Manage Customers
					0 - Exit\s""");
			s = new Scanner(System.in);
			String str = s.nextLine();
			switch (str) {
			case "1":
				makeOrder(s);
				break;
			case "2":
				System.out.println("\n Available pizza \n");
				getListPizza(false);
				break;
			case "3":
				System.out.println("\n Available drinks \n");
				getListDrinks(false);
				break;
			case "4":
				System.out.println("\n Available dessert \n");
				getListDesserts(false);
				break;
			case "5":
				listOfOrder();
				break;

			case "6":
				manageCustomer(s);
				break;
			case "0":
				break loop;
			}
		}
		s.close();
	}

	private void getListDesserts(boolean b) {
		// TODO Auto-generated method stub
		
	}
	private void getListDrinks(boolean b) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * FOR PIZZA
	 */

	private void getListPizza(Boolean showId) throws  Exception {

		Collection<Item> items = hibernate.findAllPizza();

		for (Item item : items) {

			String id = item.getId();
			String name = item.getName();
			Double price = item.getPrice();
			Boolean isVeggie = item.isVeggie();
			Collection<Ingredients> ingredients = item.getIngredients();

			if (showId)
				System.out.printf("%2s - %-25s  price : %4.2f € veggie : %-3s (%s) \n", id, name, price, isVeggie, ingredients);
			else
				System.out.printf("%-25s  price : %4.2f € veggie : %-3s (%s) \n", name, price, isVeggie, ingredients);

		}

	}
 

	

	
	
	private void manageCustomer(Scanner s) throws Exception {

		loop: while (true) {
			System.out.println("\ninside manageCustomer method");
 			System.out.println("2 - List All customers ");
			System.out.println("0 - Exit ");

			String str = s.nextLine();
			switch (str) {
			case "1":
				newCustomer(s);
				break;
			case "3":
				listAllCustomer();
				break;
			case "0":
				break loop;
			}

		}

	}

	private void listAllCustomer() throws SQLException {
		System.out.println("inside listAllCustomer methd");

		

	}

	 

	private void newCustomer(Scanner s) throws SQLException {
		System.out.println("inside new Customer methode");
		System.out.println("insert name ");

		String name = s.nextLine();
		System.out.println("insert postal code ");
		String postalCode = s.nextLine();
		System.out.println("insert address ");
		String address = s.nextLine();
		System.out.println("insert email ");
		String email = s.nextLine();
		System.out.println("insert phone ");
		String phone = s.nextLine();
		System.out.println("insert a password ");
		String password = s.nextLine();

		

	}

	

	private void listOfOrder() {
		System.out.println("inside listOfOrder methd");

	}

	private void makeOrder(Scanner s) throws Exception {

		System.out.println("Welcome , please enter your choices");
		getListPizza(true);
		getListDrinks(true);
		getListDesserts(true);
		System.out.println("Type the id of the products you wish to purchase. When you are done, type 'd' ");

		while (true) {

			String string = s.nextLine();
			if (string.equals("d")) {
				break;
			} else {
				// add item to orders_items
			}
		}

	}

	public static void main(String[] args) throws Exception {
		new App().mainLoop(false);
	}
}
