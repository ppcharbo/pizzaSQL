/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package pizzaSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
	public static String list_pizzasSQL = "SELECT id, name FROM pizzas ";
	public static String ADD_CUSTOMER = "insert into customers(name,phone,address,postal_code) values (?,?,?,?);";
	public static String getIngredient = "SELECT name,price  FROM ingredients  INNER JOIN pizzas_ingredients  ON pizzas_ingredients.ingredients_id = ingredient.id  WHERE pizzas_ingredient.pizza_id = ?;";
	public Connection conn;

	public void mainLoop() throws Exception {
		conn = makeconnection();
		while (true) {
			System.out.println(" ");
			System.out.println("1 - list all avelable pizza");
			System.out.println("2 - Make an order ");
			System.out.println("3 - List of current orders ");
			System.out.println("4 - List of ingredient of pizza ");
			System.out.println("5 - Menu of customers ");
			System.out.println("0 - Exit ");

			Scanner s = new Scanner(System.in);
			String str = s.nextLine();
			if (str.equals("1")) {
				listPizza();
			} else if (str.equals("2")) {
				makeOrder();
			} else if (str.equals("3")) {
				listOfOrder();
			} else if (str.equals("4")) {
				listOfIngredient();
			} else if (str.equals("5")) {
				manageCustemer();
			} else if (str.equals("0")) {
				break;
			}
		}
	}

	private void manageCustemer() throws Exception {
		while (true) {
			System.out.println("inside manageCustemer method");
			System.out.println("1 - Create a new customer");
			System.out.println("2 - Delete Customer ");
			System.out.println("3 - List All customers ");
			System.out.println("0 - Exit ");
			Scanner s = new Scanner(System.in);
			String str = s.nextLine();
			if (str.equals("1")) {
				newCustomer();
			} else if (str.equals("2")) {
				deleteCustomer();
			} else if (str.equals("3")) {
				listAllCustomer();
			} else if (str.equals("0")) {
				break;
			}
		}
	}

	private void listAllCustomer() {
		System.out.println("inside listAllCustomer methd");

	}

	private void deleteCustomer() {
		System.out.println("inside deleteCustomer methd");

	}

	private void newCustomer() throws Exception {
		System.out.println("inside new Custermer methode");
		System.out.println("insert name ");
		Scanner s = new Scanner(System.in);
		String name = s.nextLine();
		System.out.println("insert phone ");
		String phone = s.nextLine();
		System.out.println("insert address ");
		String address = s.nextLine();
		System.out.println("insert postal code ");
		String postalCode = s.nextLine();
		PreparedStatement prepareStatement = conn.prepareStatement(ADD_CUSTOMER);

		prepareStatement.setString(1, name);
		prepareStatement.setString(2, phone);
		prepareStatement.setString(3, address);
		prepareStatement.setString(4, postalCode);
		prepareStatement.executeUpdate();

	}

	private void listOfIngredient() throws Exception {
		System.out.println("inside listOfIngredient methd");
		System.out.println("what pizza's information do you want ?");
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		System.out.println("inside pizza list methd");

		java.sql.Statement statement = conn.createStatement();

		ResultSet resultPizza = statement.executeQuery(list_pizzasSQL);
		while (resultPizza.next()) {

			String nameOfPizza = resultPizza.getString("name");
			String idOfPizza = resultPizza.getString("id");

			System.out.println(idOfPizza + " " + nameOfPizza);
		}
	}

	private void listOfOrder() {
		System.out.println("inside listOfOrder methd");

	}

	private void makeOrder() {
		System.out.println("inside makeorder methd");

	}

	private void listPizza() throws Exception {
		System.out.println("inside pizza list methd");
		Connection conn = makeconnection();
		java.sql.Statement statement = conn.createStatement();

		ResultSet resultPizza = statement.executeQuery(list_pizzasSQL);
		while (resultPizza.next()) {

			String nameOfPizza = resultPizza.getString("name");
			String idOfPizza = resultPizza.getString("id");

			System.out.println(idOfPizza + " " + nameOfPizza);
		}

	}

	public Connection makeconnection() throws ClassNotFoundException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1/pizza";
			String user = "root";
			//String password = "tyghbn";
			String password = "dio";

			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return conn;
	}

	public static void main(String[] args) throws Exception {
		new App().mainLoop();
	}
}
