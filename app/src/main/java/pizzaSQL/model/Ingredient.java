package pizzaSQL.model;

public class Ingredient {

    private final String name;
    private final double price;
    private final int id;

    public Ingredient(int id, String name, String price){
        this.name = name; this.price=Double.parseDouble(price); this.id=id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name;
    }
}
