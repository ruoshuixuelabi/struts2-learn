package com.example.learn.struts2.demo23;

import com.example.learn.struts2.demo23.model.User;
import org.apache.struts2.action.Action;

import java.util.ArrayList;
import java.util.List;

/**
 * REST 风格的 User Action。
 *
 * 通过 struts.xml 配置 struts.mapper.action.prefix="!" 后，
 * URL 可写成：users!index / users!show / users!create / users!update / users!destroy
 *
 * 注意：这里没有用 @RestAction 注解（传统 Action + !method 风格即可；
 * RestfulActionMapper 和 !method 风格是两种不同的 REST 方案）。
 */
public class UserResourceAction implements Action {

    private List<User> users;
    private User user;
    private Long id;
    private String lastResult;

    // GET /users
    public String index() {
        users = new ArrayList<>();
        users.add(new User(1L, "alice", "alice@example.com"));
        users.add(new User(2L, "bob",   "bob@example.com"));
        lastResult = "index-" + users.size();
        return "success";
    }

    // GET /users/1
    public String show() {
        user = new User(id == null ? 1L : id, "alice", "alice@example.com");
        lastResult = "show-" + id;
        return "success";
    }

    // POST /users
    public String create() {
        lastResult = "create-" + (user == null ? "null" : user.getUsername());
        return "create";
    }

    // PUT /users/1
    public String update() {
        lastResult = "update-" + id + "-" + (user == null ? "null" : user.getUsername());
        return "success";
    }

    // DELETE /users/1
    public String destroy() {
        lastResult = "destroy-" + id;
        return "destroy";
    }

    @Override
    public String execute() {
        return index();
    }

    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLastResult() { return lastResult; }
    public void setLastResult(String lastResult) { this.lastResult = lastResult; }
}
