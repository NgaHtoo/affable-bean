package com.example.simpleaffablebean.controller;

import com.example.simpleaffablebean.ds.CartItem;
import com.example.simpleaffablebean.ds.CustomerInfo;
import com.example.simpleaffablebean.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    private boolean changeButton;

    @GetMapping({"/home","/"})
    public String home(){
        return "home";
    }
    @GetMapping("/products")
    public String listProductsByCategory(@RequestParam("cid") int cid, Model model){
        model.addAttribute("products",productService.listProducts(cid));
        this.cid=cid;
        return "products";
    }
    int cid;
    @ModelAttribute("categoryId")
    public int categoryId(){
        return cid;
    }

    @GetMapping("/add-cart")
    public String addToCart(@RequestParam("id")int id,Model model){
        model.addAttribute("product",productService.findProductById(id));
        productService.addToCart(id);
        return "redirect:/products?cid="+cid;
    }
    @ModelAttribute("cartSize")
    public Integer cartSize(){
        return productService.cartSize();
    }
    @GetMapping("/cart-view")
    public String viewCart(Model model){
        model.addAttribute("cartItem",new CartItem());
        model.addAttribute("changeButton",false);
        model.addAttribute("cartItems",productService.getCartItems());
        return "cart-view";
    }
    @GetMapping("/clear-cart")
    public String clearCart(){
        productService.clearCart();
        return "redirect:/cart-view";
    }

    @GetMapping("/checkout-v1")
    public String checkOutV1(Model model){
        Set<CartItem> cartItems=productService.getCartItems()
                .stream()
                .map(c ->
                        {c.setRender(true);
                            return c;}
                ).collect(Collectors.toSet());
        model.addAttribute("cartItem",new CartItem());
        model.addAttribute("cartItems",cartItems);
        model.addAttribute("changeButton",true);
        return "cart-view";
    }

    @PostMapping("/checkout-v2")
    public String checkOutV2(CartItem cartItem,Model model){
        model.addAttribute("cartItems",productService.getCartItems());
        int i=0;
        for (CartItem cartItem1:productService.getCartItems()){
            cartItem1.setQuantity(cartItem.getQuantityLinkedList().get(i));
            cartItem1.setRender(false);
            i++;
        }

        return "redirect:/cart-view";
    }
    @ModelAttribute("total")
    public String addPrice(Model model){
        model.addAttribute("total",productService.totalPrice());
        return "redirect:/view-cart";
    }
    static final CustomerInfo customerInfo=new CustomerInfo();
    @RequestMapping(value = "/proceed",method = {RequestMethod.GET,RequestMethod.POST})
    public String proceed(Model model){
        model.addAttribute("customer",customerInfo);
        System.out.println(customerInfo.getName());
        return "proceed-to-checkout";
    }
    @RequestMapping(value = "/purchase",method = {RequestMethod.GET,RequestMethod.POST})
    public String purchase(Model model){
        model.addAttribute("cartItems",productService.getCartItems());
        model.addAttribute("customers",customerInfo);
        System.out.println(customerInfo.getName());
        return "purchase";
    }
    @ModelAttribute("orderCode")
    public int orderCode(){
        Random random=new Random();
        return random.nextInt(9999999);
    }
}
