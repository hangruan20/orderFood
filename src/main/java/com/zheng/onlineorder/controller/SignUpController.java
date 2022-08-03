package com.zheng.onlineorder.controller;

import com.zheng.onlineorder.entity.Customer;
import com.zheng.onlineorder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class SignUpController {

    private final CustomerService customerService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SignUpController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // annotation controller 记录注册的url 记在RequestMapping里面
    @RequestMapping(value="/signup", method = RequestMethod.POST) // 通过dispatch servlet找请求
    @ResponseStatus(value = HttpStatus.CREATED)
    public void signUp(@RequestBody Customer customer){  // RequestBody json->java
        // logic
//        System.out.println("customer name "+ customer.getFirstName());
        String encodedPassword = bCryptPasswordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        customerService.signUp(customer); // 交给service处理
    }
}
