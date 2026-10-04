package com.example.learn.struts2.demo19.service;

import java.util.List;

public interface UserService {
    List<String> findAllUsernames();
    String findById(long id);
}