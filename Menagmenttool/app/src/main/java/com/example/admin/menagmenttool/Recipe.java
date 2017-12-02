package com.example.admin.menagmenttool;

/**
 * Created by admin on 26.11.2017.
 */

public class Recipe {

    String name;
    String ingredient;
    String recipts;

    public Recipe(String name, String ingredient, String recipts) {
        this.name = name;
        this.ingredient = ingredient;
        this.recipts = recipts;
    }
    public Recipe(){}

    public String getName() {
        return name;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getRecipts() {
        return recipts;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setRecipts(String recipts) {
        this.recipts = recipts;
    }



}
