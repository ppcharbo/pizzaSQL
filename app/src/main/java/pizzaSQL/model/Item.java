package pizzaSQL.model;

import java.util.Collection;

import com.mysql.cj.PingTarget;

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
		if (itemType == ItemType.pizza) {
			Double priceIng = 0.0;
			for (Ingredients ing : ingredients) {

				priceIng += ing.getPrice();
			}
// This is the gross margin of 40%
			priceIng = priceIng * 1.4;
//			We have to include the vat of 9 %
			priceIng = priceIng * 1.09;
			return priceIng / 100;
		}
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

		boolean isVeggie = true;
		
		for (Ingredients i : ingredients) 
			 
	    isVeggie =isVeggie && i.getIsVeggie();
		
		return isVeggie;
	}

}
