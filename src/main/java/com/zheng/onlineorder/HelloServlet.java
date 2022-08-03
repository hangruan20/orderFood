package com.zheng.onlineorder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zheng.onlineorder.entity.Customer;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        ObjectMapper mapper = new ObjectMapper();  // jackson 里面的

        Customer customer= new Customer();
        customer.setEmail("sun@laioffer.com");
        customer.setPassword("123456");
        customer.setFirstName("rick");
        customer.setLastName("sun");
        customer.setEnabled(true);

        response.getWriter().print(mapper.writeValueAsString(customer)); // 通过writeValueAsString 把class变成json格式
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JSONObject obj = new JSONObject(IOUtils.toString(request.getReader()));

        String lastName = obj.getString("last_name");
        int age = obj.getInt("age");

        System.out.println("last name is " + lastName);
        System.out.println("age is " + age);

        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "ok");
        response.getWriter().print(jsonResponse);

    }


    public void destroy() {
    }
}