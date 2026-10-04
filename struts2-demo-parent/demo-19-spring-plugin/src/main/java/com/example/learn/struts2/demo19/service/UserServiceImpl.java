package com.example.learn.struts2.demo19.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<String> findAllUsernames() {
        return List.of("alice", "bob", "carol");
    }

    @Override
    public String findById(long id) {
        return "user-" + id;
    }
}