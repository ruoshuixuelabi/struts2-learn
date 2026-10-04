package com.example.learn.struts2.demo24;

import com.example.learn.struts2.demo24.model.User;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据层（CDI Bean）。
 * @ApplicationScoped：整个应用共享一个实例。
 */
@ApplicationScoped
public class UserRepository {

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            list.add(new User((long) i, "user" + i));
        }
        return list;
    }

    public User findById(Long id) {
        return new User(id, "user" + id);
    }
}
