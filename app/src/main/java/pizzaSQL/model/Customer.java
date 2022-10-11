package pizzaSQL.model;

import pizzaSQL.controller.DBViewer;

import java.util.ArrayList;

public class Customer {

    private ArrayList<Pizza> pizzas;
    private ArrayList<Drink> drinks;
    private ArrayList<Dessert> desserts;

    private String name;
    private String id;
    private String email;
    private String passwd;
    private String phone;

    private DBViewer controller;

    public Customer(DBViewer controller, String name, String id, String email, String passwd, String phone){
        this.name=name; this.id=id; this.email=email; this.passwd=passwd; this.phone=phone;
        this.controller=controller;
    }

    public void addPizza(Pizza p){
        this.pizzas.add(p);
    }

    public void addDrink(Drink d){
        this.drinks.add(d);
    }

    public void addDessert(Dessert d){
        this.desserts.add(d);
    }

    public double getOrderTotal(){
        double sum=0;
        for (Drink d: drinks){
            sum+=d.getPrice();
        }
        for (Dessert d: desserts){
            sum+=d.getPrice();
        }
        for (Pizza p: pizzas){
            sum+=p.getPrice();
        }
        //add vat
        return sum;
    }

    public Order placeOrder()
}
