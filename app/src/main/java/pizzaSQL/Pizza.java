package pizzaSQL;

import java.util.ArrayList;

public class Pizza {
    private int pizza_id;
    private String name;
    private boolean vegetarian;
    private double price;
    private ArrayList<String> toppings;

    public Pizza(int pizza_id, String name, boolean vegetarian, double price, ArrayList<String> toppings) {
        this.pizza_id = pizza_id;
        this.name = name;
        this.vegetarian = vegetarian;
        this.price = price;
        this.toppings = toppings;
    }
    public Pizza(int pizza_id) {
        this.pizza_id = pizza_id;
    }

    public static Pizza create(int pizza_id, String name, boolean vegetarian, double price, ArrayList<String> toppings) {
        return new Pizza(pizza_id, name, vegetarian, price, toppings);
    }

 
 
	public int getPizza_id() {
		return pizza_id;
	}

	public void setPizza_id(int pizza_id) {
		this.pizza_id = pizza_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ArrayList<String> getToppings() {
		return toppings;
	}

	public void setToppings(ArrayList<String> toppings) {
		this.toppings = toppings;
	}

	public static Pizza createRandomPizza() {
		ArrayList<String> toppings=new ArrayList<String>();
		toppings.add("mozzarella");
		toppings.add("Tomatoes");
		return Pizza.create(0, "margaritha", true, 12.50, toppings);
	}
}
