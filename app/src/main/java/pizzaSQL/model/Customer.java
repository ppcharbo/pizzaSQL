package pizzaSQL.model;

public class Customer {

	private String id;
	private String name;
	private int postalCode;
	private String address;
	private String email;
	private String phone;
	private String password;

	public Customer(String id, String name, int postalCode, String address, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.postalCode = postalCode;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public String getAddress() {
		return address;
	}

	public String getPassword() {
		return password;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		 
		return phone;
	}

}
