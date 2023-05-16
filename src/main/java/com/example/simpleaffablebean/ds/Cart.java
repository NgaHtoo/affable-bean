package com.example.simpleaffablebean.ds;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Set;

@SessionScope
@Component
public class Cart {

    private Set<CartItem> cartItems=new HashSet<>();

    public Set<CartItem> getCartItems(){
        return cartItems;
    }
    public void addToCart(CartItem cartItem){
        this.cartItems.add(cartItem);

    }

    public void removeFromCart(CartItem cartItem){
        this.cartItems.remove(cartItem);

    }

    public int cartSize(){
        return this.cartItems.size();
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    public void clearCart(){
        this.cartItems.clear();
    }
}
