package pizzaSQL.model;

import java.util.Collection;

public class Item {

	private Integer id;
	private ItemType itemType;
	private String name;
	private Double price;
	private Collection<Ingredients> ingredients;

	public Item(Integer id, ItemType pizza, String name, double price) {
		this.id = id;
		// TODO Auto-generated constructor stub
		this.itemType = pizza;
		this.name = name;
		this.price = price;
		 
	}

	@Override
	public String toString() {

		return name;
	}

	public Integer getId() {

		return id;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
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

	public Boolean isVeggie() {
		
		
		for (Ingredients i : ingredients) {
			if (i.getIsVeggie())
				return true;
		}
		return false;
	}

}
