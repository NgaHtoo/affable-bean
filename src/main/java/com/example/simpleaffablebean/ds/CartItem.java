package com.example.simpleaffablebean.ds;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.Objects;

@Getter @Setter @ToString
public class CartItem {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private boolean render;

    private LinkedList<Integer> quantityLinkedList=new LinkedList<>();

    public CartItem() {
    }

    public CartItem(int id, String name, double price, int quantity) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return id == cartItem.id && Objects.equals(name, cartItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
