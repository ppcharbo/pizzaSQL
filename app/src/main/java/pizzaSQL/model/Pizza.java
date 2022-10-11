package pizzaSQL.model;

import java.util.ArrayList;

public class Pizza {

    private ArrayList<String> ingredients;

    private final String name;
    private final boolean veggie;
    private final int id;
    private double price;

    public Pizza(int id, String name, boolean veggie){
        this.name=name; this.veggie=veggie; this.id=id;
        price=0.0;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setPrice(double price){
        this.price=price;
    }

    public double getPrice() {
        return price;
    }

    public boolean isVeggie() {
        return veggie;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
