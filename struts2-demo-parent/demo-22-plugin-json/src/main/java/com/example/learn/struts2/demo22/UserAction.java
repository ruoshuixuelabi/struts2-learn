package com.example.learn.struts2.demo22;

import com.example.learn.struts2.demo22.model.User;
import org.apache.struts2.action.Action;

/**
 * UserAction：返回单个 User 对象。
 * struts.xml 中通过 <param name="root">user</param> 指定序列化根对象，
 * 通过 <param name="excludeProperties">user.password</param> 排除敏感字段。
 */
public class UserAction implements Action {

    private User user;
    private Long userId;

    public String execute() {
        // 模拟服务层：根据 userId 加载用户
        user = new User(userId == null ? 1L : userId,
                        "alice",
                        "alice@example.com",
                        "secret123");
        return SUCCESS;
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
