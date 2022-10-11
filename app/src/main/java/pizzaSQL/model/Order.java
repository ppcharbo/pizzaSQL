package pizzaSQL.model;


import pizzaSQL.controller.DBViewer;

public class Order {
    private final int id;
    private final Customer c;
    private final Rider r;
    private final double price;
    private boolean delivered;

    DBViewer controller;

    public Order(DBViewer controller, int id, Customer c, Rider r, double price){
        this.id=id; this.c=c; this.r=r; this.price=price;
        this.controller=controller;
        this.delivered = false;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return c;
    }

    public Rider getRider() {
        return r;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void deliver(){
        this.delivered=true;
    }
}
