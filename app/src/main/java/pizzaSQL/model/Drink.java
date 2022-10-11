package pizzaSQL.model;

public class Drink {

    private final String name;
    private final double price;

    public Drink(String name, double price){
        this.name=name; this.price=price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name;
    }
}
