package com.example.learn.struts2.demo24;

import com.example.learn.struts2.demo24.model.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

/**
 * 服务层（CDI Bean）。
 * @RequestScoped：每个 HTTP 请求一个实例。
 * @Inject 注入 Repository。
 */
@RequestScoped
public class UserService {

    @Inject
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id);
    }
}
