package com.zheng.onlineorder.controller;

import com.zheng.onlineorder.entity.Cart;
import com.zheng.onlineorder.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    @ResponseBody // 因为返回是Cart类型
    public Cart getCart(){
        return cartService.getCart();
    }
}
