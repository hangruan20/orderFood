package com.zheng.onlineorder.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class Authorities implements Serializable {  // Serializable 是用来转化为字节流之类的 变成stream 要从内存存到磁盘里 用hibernate是需要的

    private static final long serialVersionUID = 8734140534986494039L; // 版本号 用于验证匹配 看是否兼容 serialVersionUID名字不能改
    @Id
    private String email; //如果combo 主键 就变成类 primary key class

    private String authorities;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
}
