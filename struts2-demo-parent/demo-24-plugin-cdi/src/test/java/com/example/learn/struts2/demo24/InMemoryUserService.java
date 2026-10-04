package com.example.learn.struts2.demo24;

import com.example.learn.struts2.demo24.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试用 UserService：在没有 CDI 容器（无 Weld）的 JUnit 环境下，
 * 用反射塞进 UserAction 替代真正的 @Inject。
 * 生产环境（mvn jetty:run）由 Weld 容器自动注入真正的 UserService（@RequestScoped）。
 */
public class InMemoryUserService extends UserService {

    private final List<User> users = new ArrayList<>();

    public InMemoryUserService() {
        for (int i = 1; i <= 3; i++) {
            users.add(new User((long) i, "user" + i));
        }
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(Long id) {
        return new User(id, "user" + id);
    }
}