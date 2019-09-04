package com.example.smartshop;

public class PetFactory {
    public static Pet createPet(int id, String name, String type, String description, int price){
        if (type.equals("mammal")) {
            return new mammal(id, name, type, description, price);
        }
        else if (type.equals("reptile")) {
            return new reptile(id, name, type, description, price);
        }
        else{
            return new flying(id, name, type, description, price);
        }

    }
}
