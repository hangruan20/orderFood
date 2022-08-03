package com.zheng.onlineorder.service;

import com.zheng.onlineorder.dao.CustomerDao;
import com.zheng.onlineorder.entity.Cart;
import com.zheng.onlineorder.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service    // 业务逻辑相关
public class CustomerService {

    private CustomerDao customerDao;

    @Autowired
    public CustomerService(CustomerDao customerDao){
        this.customerDao = customerDao;
    }

    public void signUp(Customer customer){
        Cart cart = new Cart();
        customer.setCart(cart);
        customer.setEnabled(true);

        customerDao.signUp(customer);
    }

    public Customer getCustomer(String email) {
        return customerDao.getCustomer(email);
    }


}
