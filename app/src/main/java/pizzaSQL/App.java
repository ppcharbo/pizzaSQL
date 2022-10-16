/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package pizzaSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {
	/*
	 * FOR LOGIN:
	 */
	public static final String dbName = "pizza";
	public static final String user = "root";
	public static final String passwd = "dio";
	public static final String URL = "jdbc:mysql://127.0.0.1/" + dbName;

	public static final String listAllCustomersSQL = "SELECT * FROM customers";
	public static final String list_pizzasSQL = "SELECT id, name FROM items WHERE items_type_id = '1'";
	public static final String ADD_CUSTOMER = "insert into customers(name,postal_code,adress,email,phone,passwd) values (?,?,?,?,?,?);";
	public static final String getIngredient = "SELECT name,price  FROM ingredients  INNER JOIN pizzas_ingredients  ON pizzas_ingredients.ingredients_id = ingredient.id  WHERE pizzas_ingredient.pizza_id = ?;";
	public static final String deleteCustomerSQL = "DELETE FROM customers WHERE id =";
	public static final String listDrinkSQL = "SELECT id,name, price FROM items WHERE items_type_id = '2'";
	public static final String dessertSQL = "SELECT id,name, price FROM items WHERE items_type_id = '3'";
	public static final String isVeggieSQL = "SELECT veggie from items_ingredients JOIN ingredients i on i.id = items_ingredients.ingredients_id  WHERE items_id = ?";
	public static final String priceIngredientSQL = "SELECT price from items_ingredients JOIN ingredients i on i.id = items_ingredients.ingredients_id  WHERE items_id = ?";
	protected static String customerId = "";
	protected static String customerEmail = "";
	protected static String customerPhone = "";
	protected static String customerPostcode = "";
	protected static Connection conn;

	public void mainLoop(Boolean showId) throws Exception {
		conn = makeConnection(user, URL, passwd);
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

	/*
	 * FOR PIZZA
	 */
	private void getListPizza(Boolean showId) throws SQLException {

		java.sql.Statement statement = conn.createStatement();
		String QRY = "SELECT id, name FROM items WHERE items_type_id = '1'";
		ResultSet resultPizza = statement.executeQuery(QRY);
		while (resultPizza.next()) {

			String nameOfPizza = resultPizza.getString("name");
			String idOfPizza = resultPizza.getString("id");
			String veggie = isVeggie(idOfPizza) ? "yes" : "no";
			double price = getPriceOfIngredients(idOfPizza) / 100;
//			margin for profit so we have to multiply ..
			price = price * 1.4;
//			VAT 9%  so we have to multiply ..
			price = price * 1.09;
			String ingredientOfPizza = getIngredientOfPizza(idOfPizza);

			if (showId)
				System.out.printf("%2s - %-25s  price : %4.2f € veggie : %-3s (%s) \n", idOfPizza, nameOfPizza, price, veggie, ingredientOfPizza);
			else
				System.out.printf("%-25s  price : %4.2f € veggie : %-3s (%s) \n", nameOfPizza, price, veggie, ingredientOfPizza);

		}

	}

	public Connection makeConnection(String user, String URL, String passwd) throws ClassNotFoundException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, user, passwd);
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return conn;
	}

	private boolean isVeggie(String id) throws SQLException {

		PreparedStatement statement = conn.prepareStatement(isVeggieSQL);
		statement.setString(1, id);

		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			if (rs.getInt("veggie") == 0)
				return false;
		}
		return true;
	}

	private int getPriceOfIngredients(String id) throws SQLException {
		PreparedStatement statement = conn.prepareStatement(priceIngredientSQL);
		statement.setString(1, id);
		ResultSet rs = statement.executeQuery();

		int price = 0;
		while (rs.next()) {
			price += rs.getInt("price");
		}
		return price;
	}

	private String getIngredientOfPizza(String id) throws SQLException {
		StringBuilder out = new StringBuilder();
		Statement stmt = conn.createStatement();
		String QRY = "SELECT name FROM items_ingredients JOIN ingredients i " + "on i.id = items_ingredients.ingredients_id WHERE items_id ='" + id + "'";
		ResultSet rs = stmt.executeQuery(QRY);
		while (rs.next()) {
			out.append(rs.getString("name"));
			if (!rs.isLast())
				out.append(", ");

		}
		return out.toString();
	}

	/*
	 * FOR DRINKS
	 */
	private void getListDrinks(Boolean showId) throws Exception {

		java.sql.Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(listDrinkSQL);
		while (rs.next()) {

			String id = rs.getString("id");
			String drinkName = rs.getString("name");
			double price = rs.getInt("price");
//			margin for profit so we have to multiply ..
			price = price * 1.4;
//			VAT 9%  so we have to multiply ..
			price = price * 1.09;
			if (showId)
				System.out.printf("%2s - %-25s  price : %4.2f € \n", id, drinkName, price);
			else
				System.out.printf("%-25s  price : %4.2f € \n", drinkName, price);
		}
	}

	/*
	 * FOR DESSERTs
	 */
	private void getListDesserts(boolean showId) throws Exception {

		java.sql.Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(dessertSQL);
		rs = statement.executeQuery(dessertSQL);
		while (rs.next()) {
			String id = rs.getString("id");

			String dessertName = rs.getString("name");
			double price = rs.getInt("price");
//			margin for profit so we have to multiply ..
			price = price * 1.4;
//			VAT 9%  so we have to multiply ..
			price = price * 1.09;
			if (showId)
				System.out.printf("%2s - %-25s  price : %4.2f € \n", id, dessertName, price);
			else
				System.out.printf("%-25s  price : %4.2f € \n", dessertName, price);

		}
	}

	private void manageCustomer(Scanner s) throws Exception {

		loop: while (true) {
			System.out.println("\ninside manageCustomer method");
			System.out.println("1 - Create a new customer");
			System.out.println("2 - Delete Customer ");
			System.out.println("3 - List All customers ");
			System.out.println("0 - Exit ");

			String str = s.nextLine();
			switch (str) {
			case "1":
				newCustomer(s);
				break;
			case "2":
				deleteCustomer();
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

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(listAllCustomersSQL);
		while (rs.next()) {

			String id = rs.getString("id");
			String name = rs.getString("name");
			String email = rs.getString("email");
			String phone = rs.getString("phone");

			System.out.println("[Customer id: " + id + " Name: " + name + " email: " + email + " Phone num: " + phone);
		}

	}

	private void deleteCustomer() throws SQLException {
		listAllCustomer();
		System.out.println("Type the id of a customer to delete: ");
		Scanner s = new Scanner(System.in);
		String id = s.nextLine();

		s.close();
		Statement stmt = conn.createStatement();
		stmt.executeQuery((deleteCustomerSQL + id));

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

		createCustomer(conn, name, postalCode, address, email, phone, password);

	}

	public void createCustomer(Connection connection, String name, String postalCode, String address, String email, String phone, String password) throws SQLException {
		PreparedStatement prepareStatement = connection.prepareStatement(ADD_CUSTOMER);

		prepareStatement.setString(1, name);
		prepareStatement.setString(2, postalCode);
		prepareStatement.setString(3, address);
		prepareStatement.setString(4, email);
		prepareStatement.setString(5, phone);
		prepareStatement.setString(6, password);
		prepareStatement.executeUpdate();
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
