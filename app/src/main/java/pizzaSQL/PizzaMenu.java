package pizzaSQL;

import java.util.ArrayList;
import java.util.List;

public class PizzaMenu {
	private static PizzaMenu pizzaMenu;
	private List<Pizza> pizzaList = new ArrayList<Pizza>();

	private PizzaMenu() {
		
		ArrayList<String> toppings= new ArrayList<String>();
		toppings.add("Mozzarella");
		toppings.add("Tomatoes");
		
		
		pizzaList.add(Pizza.create(0, "margaritha", true, 12.50, toppings));
		toppings.clear();
		toppings.add("Basil");
		toppings.add("Olive");
		pizzaList.add(Pizza.create(1, "meatlovers", false, 15.75, toppings));
		toppings.clear();
		toppings.add("origano");
		toppings.add("acciughe");
		toppings.add("grana");
		pizzaList.add(Pizza.create(2, "quattrostagioni", false, 20.75, toppings));
	}

	public static PizzaMenu getMenu() {

		if (pizzaMenu == null)
			pizzaMenu = new PizzaMenu();

		return pizzaMenu;

	}

	public List<Pizza> getPizzaList() {
		return pizzaList;
	}

	public Pizza getPizzaId(int id) {
		return pizzaList.get(id);
	}

	public static Pizza search(int pizza_id) {

		try {

			Pizza pizza = getMenu().getPizzaList().get(pizza_id);
			return pizza;
		} catch (IndexOutOfBoundsException e) {
			return null;
		}

	}

}
