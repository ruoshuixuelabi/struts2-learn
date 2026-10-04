package com.example.learn.struts2.demo19.service;

import java.util.List;

/**
 * 简单的 UserService 实现，用于测试模式（不走 Spring 容器时直接 new 注入）。
 * 生产环境通常会标注 @Service 让 Spring 自动扫描注入。
 */
public class SimpleUserService implements UserService {

    @Override
    public List<String> findAllUsernames() {
        return List.of("alice", "bob", "charlie");
    }

    @Override
    public String findById(long id) {
        return "user-" + id;
    }
}
