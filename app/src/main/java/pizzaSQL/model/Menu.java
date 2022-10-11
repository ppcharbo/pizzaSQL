package pizzaSQL.model;

import java.util.ArrayList;

public class Menu {

    private ArrayList<Pizza> pizzas;
    private ArrayList<Drink> drinks;
    private ArrayList<Dessert> desserts;

    public Menu(ArrayList<Pizza> pizzas, ArrayList<Drink> drinks, ArrayList<Dessert> desserts){
        this.pizzas=pizzas; this.drinks=drinks; this.desserts=desserts;
    }
}
