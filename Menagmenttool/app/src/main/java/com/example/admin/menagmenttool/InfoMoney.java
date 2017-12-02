package com.example.admin.menagmenttool;

/**
 * Created by admin on 11.11.2017.
 */

public class InfoMoney {
    String name;
    int cost;

    public InfoMoney(String newName,int newCost){
        name = newName;
        cost = newCost;
    }
    public InfoMoney(){

    }
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}
