package com.zheng.onlineorder.service;

import com.zheng.onlineorder.dao.CartDao;
import com.zheng.onlineorder.entity.Cart;
import com.zheng.onlineorder.entity.Customer;
import com.zheng.onlineorder.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CartService { // A

    @Autowired // injection
    private CustomerService customerService;   // B

    @Autowired // injection
    private CartDao cartDao; //B2
    public Cart getCart(){
        Authentication logginInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = logginInUser.getName();
        Customer customer = customerService.getCustomer(email);

        if (customer!=null){
            Cart cart = customer.getCart();
            double totalPrice = 0;
            for (OrderItem item : cart.getOrderItemList()){
                totalPrice += item.getPrice()*item.getQuantity();
            }
            cart.setTotalPrice(totalPrice);
            return cart;
        }
        return new Cart();
    }

    public void cleanCart(){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedInUser.getName();
        Customer customer = customerService.getCustomer(email);
        if (customer!=null) {
            cartDao.removeAllCartItems(customer.getCart());
        }
    }

}
