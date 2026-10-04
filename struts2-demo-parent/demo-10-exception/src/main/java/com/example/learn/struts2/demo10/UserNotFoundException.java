package com.example.learn.struts2.demo10;

/**
 * 自定义业务异常。最佳实践：继承 RuntimeException（非 checked），
 * 让 Struts exception 拦截器能在 Action 不显式 throws 的情况下捕获。
 */
public class UserNotFoundException extends RuntimeException {
    private final Long userId;

    public UserNotFoundException(Long userId) {
        super("User not found: id=" + userId);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}