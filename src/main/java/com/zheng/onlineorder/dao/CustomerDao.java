package com.zheng.onlineorder.dao;

import com.zheng.onlineorder.entity.Authorities;
import com.zheng.onlineorder.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
// 语义上标记了是与数据库交互的
public class CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void signUp(Customer customer){
        Authorities authorities = new Authorities();  // 设置用户权限
        authorities.setAuthorities("ROLE_USER");
        authorities.setEmail(customer.getEmail());

        Session session = null;

        try{
            session = sessionFactory.openSession();  // session factory里面是数据库的各种增删查改操作
            //存customer和authority 要操作多个表 要保证同时添加或rollback的原子性 要创建transaction
            session.beginTransaction();
            session.save(authorities);  // insert
            session.save(customer);
            session.getTransaction().commit();

        } catch (Exception ex){
            ex.printStackTrace();
            if (session != null){  // 有session 但是有错
                session.getTransaction().rollback();
            }
        } finally { // 最后要关掉 要不然占地方
            if (session != null){
                session.close();
            }
        }
    }

    public Customer getCustomer(String email){
        Customer customer = null;
        Session session = null;

        try{
            session = sessionFactory.openSession();
            customer = session.get(Customer.class, email); // (entityType, id) 要根据非PK所有的话用CriteriaQuery

        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if(session != null) session.close();
        }

        return customer;
    }
}
