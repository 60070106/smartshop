package com.example.smartshop;
import java.io.*;

public class Pet implements Serializable {
    private int id;
    private String name;
    private String type;
    private String description;
    private int price;

    public Pet(int id, String name, String type, String description, int price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

class mammal extends Pet{
    public mammal(int id, String name, String type, String description, int price) {
        super(id, name, type, description, price);
    }
}

class reptile extends Pet{
    public reptile(int id, String name, String type, String description, int price){
        super(id, name, type, description, price);
    }
}

class flying extends Pet{
    public flying(int id, String name, String type, String description, int price){
        super(id, name, type, description, price);
    }
}