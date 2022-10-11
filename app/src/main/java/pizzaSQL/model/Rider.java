package pizzaSQL.model;

import pizzaSQL.controller.DBViewer;

public class Rider {

    private final String id, name;
    private boolean available;

    DBViewer controller;

    public Rider(DBViewer controller, String id, String name, boolean available){
        this.controller=controller; this.id=id; this.name=name; this.available=available;
    }

    public void deliver() {
        this.available=false;
        controller.deliever(this.id);
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
