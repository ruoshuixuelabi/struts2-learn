package com.example.learn.struts2.legacy04;

/**
 * Plexus 组件接口：用户服务。
 * 由 Plexus 容器管理生命周期（创建 / 注入 / 销毁）。
 */
public interface UserService {
    String greet(String name);
}