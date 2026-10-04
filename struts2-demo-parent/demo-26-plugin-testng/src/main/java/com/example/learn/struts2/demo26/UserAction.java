package com.example.learn.struts2.demo26;

import org.apache.struts2.action.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 简单 User Action：list 列表 + save 保存（带基本校验）。
 */
public class UserAction implements Action {

    private User user;
    private List<User> users;
    private int page = 1;

    public String list() {
        users = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            users.add(new User((long) i, "user" + i, "user" + i + "@example.com"));
        }
        return SUCCESS;
    }

    public String save() {
        // 简单校验：username 不空 + email 必含 @
        if (user == null || user.getUsername() == null || user.getUsername().isEmpty()) {
            return INPUT;
        }
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            return INPUT;
        }
        return SUCCESS;
    }

    @Override
    public String execute() {
        return list();
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }

    public static class User {
        private String username;
        private String email;
        public User() {}
        public User(Long id, String username, String email) {
            this.username = username;
            this.email = email;
        }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
}
