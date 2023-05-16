package com.example.simpleaffablebean.service;

import com.example.simpleaffablebean.dao.ProductDao;
import com.example.simpleaffablebean.ds.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private Cart cart;

    public List<Product> listProducts(int categoryId){
        return productDao.findProductByCategoryId(categoryId);
    }
    public void clearCart(){
        cart.clearCart();
    }

    public Product findProductById(int id){

        return productDao.findProductById(id);
    }

    public void addToCart(int id){
        Product product=findProductById(id);
        cart.addToCart(new CartItem(
                product.getId(),
                product.getName(),
                product.getPrice(),
                1
        ));
    }

    public int cartSize(){
        return cart.cartSize();
    }

    public Set<CartItem> getCartItems() {
        return cart.getCartItems();
    }

    public double totalPrice() {
        double total=getCartItems().stream()
                .map( a -> a.getPrice() *a.getQuantity())
                .mapToDouble(a -> a).sum();
        return total;
    }

}
