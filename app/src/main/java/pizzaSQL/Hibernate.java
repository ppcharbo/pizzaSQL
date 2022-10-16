package pizzaSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import pizzaSQL.model.Customer;
import pizzaSQL.model.Ingredients;
import pizzaSQL.model.Item;
import pizzaSQL.model.ItemType;

public class Hibernate {
	public static final String listAllCustomersSQL = "SELECT * FROM customers";
	public static final String list_pizzasSQL = "SELECT id, name FROM items WHERE items_type_id = '1'";
	public static final String ADD_CUSTOMER = "insert into customers(name,postal_code,adress,email,phone,passwd) values (?,?,?,?,?,?);";
	public static final String findIngredientSQL = "SELECT id,name,price,veggie FROM items_ingredients JOIN ingredients i on i.id = items_ingredients.ingredients_id WHERE items_id =?";
	public static final String deleteCustomerSQL = "DELETE FROM customers WHERE id =";
	public static final String listDrinkSQL = "SELECT id,name, price FROM items WHERE items_type_id = '2'";
	public static final String dessertSQL = "SELECT id,name, price FROM items WHERE items_type_id = '3'";
	public static final String isVeggieSQL = "SELECT veggie from items_ingredients JOIN ingredients i on i.id = items_ingredients.ingredients_id  WHERE items_id = ?";
	public static final String priceIngredientSQL = "SELECT price from items_ingredients JOIN ingredients i on i.id = items_ingredients.ingredients_id  WHERE items_id = ?";

	private Connection conn;

	public Hibernate(String user, String passwd, String URL) throws ClassNotFoundException {
		conn = makeConnection(user, URL, passwd);
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

	public Collection<Item> findAllPizza() throws Exception {

		ArrayList<Item> coll = new ArrayList<Item>();

		java.sql.Statement statement = conn.createStatement();
		String QRY = "SELECT id, name FROM items WHERE items_type_id = '1'";
		ResultSet resultPizza = statement.executeQuery(QRY);
		while (resultPizza.next()) {

			String id = resultPizza.getString("id");
			String name = resultPizza.getString("name");
			Boolean isVeggie = isVeggie(id);

			double price = getPriceOfIngredients(id) / 100;
			price = price * 1.4;
//			VAT 9%  so we have to multiply ..
			price = price * 1.09;

			Item pizza = new Item(id, ItemType.pizza, name, price, isVeggie);
			Collection<Ingredients> ingredients = findAllIngredients(id);
			pizza.setIngredients(ingredients);
			coll.add(pizza);

//			String ingredientOfPizza = getIngredientOfPizza(id);
//
//			if (showId)
//				System.out.printf("%2s - %-25s  price : %4.2f € veggie : %-3s (%s) \n", id, name, price, veggie, ingredientOfPizza);
//			else
//				System.out.printf("%-25s  price : %4.2f € veggie : %-3s (%s) \n", name, price, veggie, ingredientOfPizza);

		}
		return coll;
	}

	private Collection<Ingredients> findAllIngredients(String pizzaId) throws Exception {
		
		
		Collection<Ingredients> ret= new ArrayList<Ingredients>();
		
	 
		 PreparedStatement stmt = conn.prepareStatement(findIngredientSQL);
		 stmt.setString(1, pizzaId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String id = rs.getString("id");
			String name=rs.getString("name");
			Double price =rs.getDouble("price");
			Boolean isVeggie=rs.getBoolean("veggie");
			ret.add(new Ingredients(id,name,price,isVeggie));

		}
		
		return ret;
	}

	public void createCustomer(String name, String postalCode, String address, String email, String phone, String password) throws SQLException {
		PreparedStatement prepareStatement = conn.prepareStatement(ADD_CUSTOMER);
		prepareStatement.setString(1, name);
		prepareStatement.setString(2, postalCode);
		prepareStatement.setString(3, address);
		prepareStatement.setString(4, email);
		prepareStatement.setString(5, phone);
		prepareStatement.setString(6, password);
		prepareStatement.executeUpdate();
	}

	public Collection<Item> findAllDrinks() throws Exception {
		 
		Collection<Item> ret = new  ArrayList<Item>();
		
		java.sql.Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(listDrinkSQL);
		while (rs.next()) {

			String id = rs.getString("id");
			String name = rs.getString("name");
			double price = rs.getInt("price");
//			margin for profit so we have to multiply ..
			price = price * 1.4;
//			VAT 9%  so we have to multiply ..
			price = price * 1.09;
			ret.add(new Item(id, ItemType.drink, name, price, false));
			
		}
		return ret ;
	}

	public Collection<Item> findAllDessert() throws Exception {
		 
Collection<Item> ret = new  ArrayList<Item>();
		
		java.sql.Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(dessertSQL);
		while (rs.next()) {

			String id = rs.getString("id");
			String name = rs.getString("name");
			double price = rs.getInt("price");
//			margin for profit so we have to multiply ..
			price = price * 1.4;
//			VAT 9%  so we have to multiply ..
			price = price * 1.09;
			ret.add(new Item(id, ItemType.drink, name, price, false));
			
		}
		return ret ;
	}

	public Collection<Customer> findAllCustomers() throws Exception {
Collection<Customer> ret = new  ArrayList<Customer>();
		
		java.sql.Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(listAllCustomersSQL);
		while (rs.next()) {

			String id = rs.getString("id");
			String name = rs.getString("name");
			int postalCode = rs.getInt("postal_code");
			String address = rs.getString("adress");
			String email = rs.getString("email");
			String phone = rs.getString("phone");
			String password = rs.getString("passwd");
//			margin for profit so we have to multiply ..
			 
			ret.add(new Customer(id, name,postalCode,address,email,phone,password));
			
		}
		return ret ;
	}
}
