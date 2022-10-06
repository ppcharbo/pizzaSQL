package pizzaSQL;

import java.util.ArrayList;

public class Customer {
    private int customerID;
    private ArrayList<Order> ordersList;

    public Customer(int customerID, ArrayList<Order> ordersList) {
        this.customerID = customerID;
        this.ordersList = ordersList;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public ArrayList<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(ArrayList<Order> ordersList) {
        this.ordersList = ordersList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", ordersList=" + ordersList +
                '}';
    }

    public void addOrder(Order order){
        ordersList.add(order);
    }
}
