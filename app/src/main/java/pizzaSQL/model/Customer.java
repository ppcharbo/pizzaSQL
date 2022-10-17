package pizzaSQL.model;

public class Customer {

	private Integer id;
	private String name;
	private Integer postalCode;
	private String address;
	private String email;
	private String phone;
	private String password;

	public Customer(Integer id, String name, Integer postalCode, String address, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.postalCode = postalCode;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public String getAddress() {
		return address;
	}

	public String getPassword() {
		return password;
	}

	public Integer getId() {
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
