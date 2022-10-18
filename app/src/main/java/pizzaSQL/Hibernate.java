package pizzaSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import pizzaSQL.model.Customer;
import pizzaSQL.model.Ingredients;
import pizzaSQL.model.Item;
import pizzaSQL.model.ItemType;
import pizzaSQL.model.Order;
import pizzaSQL.model.Rider;

public class Hibernate {
	public static final String listAllCustomersSQL = "SELECT * FROM customers";
	public static final String findAllRidersSQL= "SELECT * FROM riders";
	public static final String findOrderLessThen5Min = "select * from orders where    NOW() < ready_at - INTERVAL 5  minute";
	public static final String listAllOrdersSQL = "SELECT * FROM Orders";
	public static final String deleteOrderDetail = "delete from orders_items where orders_id=?";
	public static final String deleteOrderById = "delete from orders  where id=?";
	public static final String findCustomerByIDSQL = "select * from customers where id=?";
	public static final String findItemByIDSQL = "select * from items where id=?";
	public static final String findItemsByOrdersSQL = "select * from orders_items where orders_id=?";
	public static final String findOrderSQL = "select * from orders where id=?";
	public static final String findRiderSQL = "select * from riders where id=?";
	public static final String list_pizzasSQL = "SELECT id, name FROM items WHERE items_type_id = '1'";
	public static final String ADD_CUSTOMER = "insert into customers(name,postal_code,adress,email,phone,passwd) values (?,?,?,?,?,?);";
	public static final String findIngredientSQL = "SELECT id,name,price,veggie FROM items_ingredients JOIN ingredients i on i.id = items_ingredients.ingredients_id WHERE items_id =?";
	public static final String deleteCustomerSQL = "DELETE FROM customers WHERE id =";
	public static final String listDrinkSQL = "SELECT id,name, price FROM items WHERE items_type_id = '2'";
	public static final String dessertSQL = "SELECT id,name, price FROM items WHERE items_type_id = '3'";
	public static final String isVeggieSQL = "SELECT veggie from items_ingredients JOIN ingredients i on i.id = items_ingredients.ingredients_id  WHERE items_id = ?";
	public static final String priceIngredientSQL = "SELECT price from items_ingredients JOIN ingredients i on i.id = items_ingredients.ingredients_id  WHERE items_id = ?";
	public static final String createOrdersSQL = "insert into orders(idcustomer,price,ready_at,picked_up_at,delivered,discount_code) values(?,?,?,?,?,?)";
	public static final String createOrdersDetailSQL = "insert into orders_items(orders_id,items_id) values(?,?)";
	public static final String findDiscountCodeSQL = "select * from orders where discount_code= ? ";
	public static final String findAllPendingOrdersSQL = "select * from orders where delivered=false and ready_at < NOW()";
	public static final String findAllFreeRiderSQL = "select * from riders where available=true";
	public static final String udpateRiderStatusSQL="update riders set available = ?,cameBack = NOW() + INTERVAL 30 minute where id=?";
	public static final String resetRiderComeBack="select * from riders where cameBack < NOW()";
	public static final String closeOrderSQL="update orders set delivered=1 where idrider=?";
	public static final String updateOrderDriverSQL="update orders set  picked_up_at = NOW() , idrider=?   where id=?";
	public Connection conn;

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

	private int getPriceOfIngredients(Integer id) throws SQLException {
		PreparedStatement statement = conn.prepareStatement(priceIngredientSQL);
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();

		int price = 0;
		while (rs.next()) {
			price += rs.getInt("price");
		}
		return price;
	}

	@SuppressWarnings("unused")
	private boolean isVeggie(Integer id) throws SQLException {

		PreparedStatement statement = conn.prepareStatement(isVeggieSQL);
		statement.setInt(1, id);

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

			Integer id = resultPizza.getInt("id");
			String name = resultPizza.getString("name");
			

			double price = getPriceOfIngredients(id) / 100;
//			FIXME
			price = price * 1.4;
//			VAT 9%  so we have to multiply ..
			price = price * 1.09;

			Item pizza = new Item(id, ItemType.pizza, name, price);
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

	Collection<Ingredients> findAllIngredients(Integer pizzaId) throws Exception {

		Collection<Ingredients> ret = new ArrayList<Ingredients>();

		PreparedStatement stmt = conn.prepareStatement(findIngredientSQL);
		stmt.setInt(1, pizzaId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String id = rs.getString("id");
			String name = rs.getString("name");
			Double price = rs.getDouble("price");
			Boolean isVeggie = rs.getBoolean("veggie");
			ret.add(new Ingredients(id, name, price, isVeggie));

		}

		return ret;
	}

	public Customer createCustomer(String name, Integer postalCode, String address, String email, String phone, String password) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(ADD_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, name);
		ps.setInt(2, postalCode);
		ps.setString(3, address);
		ps.setString(4, email);
		ps.setString(5, phone);
		ps.setString(6, password);
		ps.executeUpdate();

		ResultSet generatedKeys = ps.getGeneratedKeys();
		Integer customerID = null;
		if (generatedKeys.next())
			customerID = generatedKeys.getInt(1);

		return new Customer(customerID, name, postalCode, address, email, phone, password);
		// return new Customer(customerID, name, postalCode, address, email, phone, password);
	}

	public Collection<Item> findAllDrinks() throws Exception {

		Collection<Item> ret = new ArrayList<Item>();

		java.sql.Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(listDrinkSQL);
		while (rs.next()) {

			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			double price = rs.getInt("price");
//			margin for profit so we have to multiply ..
			price = price * 1.4;
//			VAT 9%  so we have to multiply ..
			price = price * 1.09;
			ret.add(new Item(id, ItemType.drink, name, price));

		}
		return ret;
	}

	public Collection<Item> findAllDessert() throws Exception {

		Collection<Item> ret = new ArrayList<Item>();

		java.sql.Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(dessertSQL);
		while (rs.next()) {

			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			double price = rs.getInt("price");
//			margin for profit so we have to multiply ..
			price = price * 1.4;
//			VAT 9%  so we have to multiply ..
			price = price * 1.09;
			ret.add(new Item(id, ItemType.drink, name, price));

		}
		return ret;
	}

	public Collection<Customer> findAllCustomers() throws Exception {
		Collection<Customer> ret = new ArrayList<Customer>();

		java.sql.Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(listAllCustomersSQL);
		while (rs.next()) {

			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			Integer postalCode = rs.getInt("postal_code");
			String address = rs.getString("adress");
			String email = rs.getString("email");
			String phone = rs.getString("phone");
			String password = rs.getString("passwd");
//			margin for profit so we have to multiply ..

			ret.add(new Customer(id, name, postalCode, address, email, phone, password));

		}
		return ret;
	}

	public Item findItemById(Integer id) throws Exception {

		PreparedStatement ps = conn.prepareStatement(findItemByIDSQL);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {

			Integer itemTypeId = rs.getInt("items_type_id");
			ItemType type = getItemType(itemTypeId);
			String name = rs.getString("name");
			double price = rs.getDouble("price");
			Item item = new Item(id, type, name, price);
			Collection<Ingredients> ingredient = findAllIngredients(id);
			item.setIngredients(ingredient);
			return item;

		}

		return null;
	}

	private ItemType getItemType(Integer itemTypeId) {

		switch (itemTypeId) {
		case 1:
			return ItemType.pizza;
		case 2:
			return ItemType.drink;
		case 3:
			return ItemType.dessert;
		default:
			throw new IllegalArgumentException("Unexpected value: " + itemTypeId);
		}

	}

	public Order completCheckOut(Collection<Item> basket, Customer customer, String discount_code) throws Exception {

		int idcustomer = Integer.valueOf(customer.getId());
//		600000 millisecond for the 10 minute of preparartion 
		Timestamp ready_at = new Timestamp(System.currentTimeMillis() + 600000);
		Timestamp picked_up_at = null;
		boolean delivered = false;

		double price = findPrice(basket, discount_code);
		// insert into orders(idcustomer,price,ready_at,picked_up_at,delivered,discount_code) values(?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(createOrdersSQL, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, idcustomer);
		ps.setDouble(2, price);
		ps.setTimestamp(3, ready_at);
		ps.setTimestamp(4, picked_up_at);
		ps.setBoolean(5, delivered);
		ps.setString(6, discount_code);
		ps.executeUpdate();
		ResultSet generatedKeys = ps.getGeneratedKeys();
		Integer orderId;

		if (generatedKeys.next()) {
			orderId = generatedKeys.getInt(1);

			for (Item item : basket) {
				ps = conn.prepareStatement(createOrdersDetailSQL);
				ps.setInt(1, orderId);
				ps.setInt(2, Integer.valueOf(item.getId()));
				ps.executeUpdate();
			}
			return findOrderById(orderId);
		}
		return null;
	}

	public double findPrice(Collection<Item> basket, String discount_code) {

		Double price = 0.0;
		for (Item item : basket) {
			price += item.getPrice();
		}
		if (discount_code != "") {
			
			double newPrice = price * 0.9;
			
			System.out.printf("\n applying discount code to price:%6.2f --> new price is %6.2f \n ", price, newPrice);
		}
		return price;
	}

	public Boolean findDiscountDuplicate(String discount) throws Exception {
		PreparedStatement ps = conn.prepareStatement(findDiscountCodeSQL);
		ps.setString(1, discount);
		ResultSet execute = ps.executeQuery();

		return execute.next();

	}

	/*
	 * 
	 * +---------------+-------------+------+-----+---------+----------------+ | Field | Type | Null | Key | Default | Extra | +---------------+-------------+------+-----+---------+----------------+ | id | int | NO | PRI | NULL | auto_increment | | idcustomer | int | NO | PRI | NULL | | | idrider | int
	 * | YES | MUL | NULL | | | price | double | NO | | NULL | | | ready_at | timestamp | NO | | NULL | | | picked_up_at | timestamp | YES | | NULL | | | delivered | tinyint | YES | | NULL | | | discount_code | varchar(10) | YES | | NULL | |
	 * +---------------+-------------+------+-----+---------+----------------+
	 * 
	 * 
	 */
	public Order findOrderById(Integer id) throws Exception {
		PreparedStatement ps = conn.prepareStatement(findOrderSQL);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {

			Integer riderId = rs.getInt("idrider");
			Integer customerId = rs.getInt("idcustomer");

			Rider rider = findRiderById(riderId);
			Customer customer = findCustomerById(customerId);

			Double price = rs.getDouble("price");
			Timestamp readtAt = rs.getTimestamp("ready_at");
			Timestamp deliveredAt = rs.getTimestamp("picked_up_at");
			String discoutCode = rs.getString("discount_code");
			Boolean delivered = rs.getBoolean("delivered");

			Collection<Item> items = findItemsByOrder(id);
			Order order = new Order(id, customer, rider, price, readtAt, deliveredAt, delivered, discoutCode);
			order.setDetails(items);
			return order;

		}

		return null;

	}

	public Collection<Item> findItemsByOrder(Integer id) throws Exception {
		Collection<Item> ret = new ArrayList<Item>();

		PreparedStatement ps = conn.prepareStatement(findItemsByOrdersSQL);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			Integer itemId = rs.getInt("items_id");

			Item item = findItemById(itemId);
			ret.add(item);
		}

		return ret;
	}

	public Rider findRiderById(Integer id) throws Exception {

		PreparedStatement ps = conn.prepareStatement(findRiderSQL);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {

			String name = rs.getString("name");
			Boolean available = rs.getBoolean("available");
			Integer postalCode = rs.getInt("postal_code");

			Timestamp cameBack = rs.getTimestamp("cameBack");
			return new Rider(id, name, cameBack, available, postalCode);

		}

		return null;

	}

	/*
	 * id | int | NO | PRI | NULL | auto_increment | | idcustomer | int | NO | PRI | NULL | | | idrider | int | YES | MUL | NULL | | | price | double | NO | | NULL | | | ready_at | timestamp | NO | | NULL | | | picked_up_at | timestamp | YES | | NULL | | | delivered | tinyint | YES | | NULL | | |
	 * discount_code | varchar(10) | YES | | NULL | |
	 * 
	 */
	@SuppressWarnings("unused")
	private Collection<Order> findAllOrders() throws Exception {
		Collection<Order> ret = new ArrayList<Order>();

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

			// ret.add(Order());

		}
		return ret;
	}

	/*
	 * 
	 * id | int | NO | PRI | NULL | auto_increment | | name | varchar(45) | YES | | NULL | | | postal_code | int | NO | | NULL | | | adress | varchar(45) | NO | | NULL | | | email | varchar(45) | NO | | NULL | | | phone | varchar(45) | YES | | NULL | | | passwd | varchar(45) | NO | | NULL | | +
	 * 
	 * 
	 */
	public Customer findCustomerById(Integer id) throws Exception {

		PreparedStatement ps = conn.prepareStatement(findCustomerByIDSQL);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {

			String name = rs.getString("name");
			Integer postalCode = rs.getInt("postal_code");
			String address = rs.getString("adress");
			String email = rs.getString("email");
			String phone = rs.getString("phone");
			String password = rs.getString("passwd");

			return new Customer(id, name, postalCode, address, email, phone, password);

		}

		return null;
	}

	public Collection<Order> findAllOrdersInFiveMinutes() throws Exception {

		Collection<Order> ret = new ArrayList<Order>();

		java.sql.Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(findOrderLessThen5Min);
		while (rs.next()) {

			Integer id = rs.getInt("id");
			Order order = findOrderById(id);
			ret.add(order);

		}
		return ret;
	}

	public void deleteOrders(Integer id) throws Exception {

		PreparedStatement ps = conn.prepareStatement(deleteOrderDetail);
		ps.setInt(1, id);
		ps.executeUpdate();

		ps = conn.prepareStatement(deleteOrderById);
		ps.setInt(1, id);
		ps.executeUpdate();

	}

	public Collection<Order> findAllPendingOrder() throws Exception {

		Collection<Order> ret = new ArrayList<Order>();

		java.sql.Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(findAllPendingOrdersSQL);
		while (rs.next()) {

			Integer id = rs.getInt("id");
			Order order = findOrderById(id);
			ret.add(order);

		}
		return ret;
	}

	public Collection<Rider> findFreeRiders() throws Exception {
		
		Collection<Rider> ret = new ArrayList<Rider>();

		java.sql.Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(findAllFreeRiderSQL);
		while (rs.next()) {
			Integer id = rs.getInt("id");
			Rider rider = findRiderById(id);
			ret.add(rider);
		}
		return ret;
	}

	public void updateRidersStatus(Rider rider) throws Exception {
		
		PreparedStatement ps = conn.prepareStatement(udpateRiderStatusSQL);
		ps.setBoolean(1, rider.getAvailable());
		ps.setInt(2, rider.getId());
		ps.executeUpdate();

		
	}

	public Collection<Rider> findAllRiders() throws Exception {
		 
		Collection<Rider> ret = new ArrayList<Rider>();

		java.sql.Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(findAllRidersSQL);
		while (rs.next()) {
			Integer id = rs.getInt("id");
			Rider rider = findRiderById(id);
			ret.add(rider);
		}
		return ret;
	}

	public Collection<Rider> resetRiderCameBack() throws Exception {
		 
		Collection<Rider> ret = new ArrayList<Rider>();

		java.sql.Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(resetRiderComeBack);
		while (rs.next()) {
			Integer id = rs.getInt("id");
			Rider rider = findRiderById(id);
			ret.add(rider);
		}
		for (Rider rider : ret) {
			rider.setAvailable(true);
			rider.setCameBack(null);
			updateRidersStatus(rider);
			updateOrderDelivered(rider);
		}
		return ret;
	}

	private void updateOrderDelivered(Rider rider) throws Exception {
		// closeOrderSQL
		PreparedStatement ps = conn.prepareStatement(closeOrderSQL);
		ps.setInt(1, rider.getId());
		ps.executeUpdate();

		
	}

	public void assignRiderToOrder(Order o, Rider rider) throws Exception {
		
		// closeOrderSQL
				PreparedStatement ps = conn.prepareStatement(updateOrderDriverSQL);
				ps.setInt(1, rider.getId());
				ps.setInt(2, o.getId());
				ps.executeUpdate();

	}

}
