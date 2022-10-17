package pizzaSQL.model;

import java.util.Collection;

public class Item {

	private String id;
	private ItemType itemType;
	private String name;
	private Double price;
	private Boolean isVeggie;
	private Collection<Ingredients> ingredients;

	public Item(String id, ItemType pizza, String name, double price, Boolean isVeggie) {
		this.id = id;
		// TODO Auto-generated constructor stub
		this.itemType = pizza;
		this.name = name;
		this.price = price;
		this.isVeggie = isVeggie;
	}

	@Override
	public String toString() {

		return name;
	}

	public String getId() {

		return id;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public Boolean isVeggie() {
		return isVeggie;
	}

	public ItemType getItemType() {
		return itemType;

	}

	public Collection<Ingredients> getIngredients() {

		return ingredients;
	}

	public void setIngredients(Collection<Ingredients> ingredients) {
		this.ingredients = ingredients;

	}

}
