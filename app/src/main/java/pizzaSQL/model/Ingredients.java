package pizzaSQL.model;

public class Ingredients {

	private String id;
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public Boolean getIsVeggie() {
		return isVeggie;
	}

	private String name;
	private Double price;
	private Boolean isVeggie;

	public Ingredients(String id, String name, Double price, Boolean isVeggie) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.isVeggie = isVeggie;
	}

}
