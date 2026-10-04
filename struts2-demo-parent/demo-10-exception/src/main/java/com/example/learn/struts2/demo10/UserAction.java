package com.example.learn.struts2.demo10;

import org.apache.struts2.ActionSupport;

/**
 * 演示 Struts 2.x 两层异常处理：
 *
 * - delete() : 抛 IllegalArgumentException → Action 级映射 → /home.jsp (input)
 * - login()  : 抛 SecurityException → Action 级映射 → /login.jsp
 * - throwNpe() : 抛 NullPointerException → Action 级没匹配 → 全局 Throwable 兜底 → /error.jsp
 * - findUser() : 抛自定义 UserNotFoundException → 全局映射 → /user-notfound.jsp
 */
public class UserAction extends ActionSupport {

    private Long id;

    public String delete() {
        if (id == null) {
            // Action 级 exception-mapping 捕获 → input result
            throw new IllegalArgumentException("userId 不能为空");
        }
        return SUCCESS;
    }

    public String login() {
        // Action 级 exception-mapping 捕获 → login result
        throw new SecurityException("会话已过期，请重新登录");
    }

    public String throwNpe() {
        // Action 级和全局业务异常都没匹配，由全局 Throwable 兜底
        Object o = null;
        o.toString();   // NPE
        return SUCCESS;
    }

    public String findUser() {
        // 自定义异常被全局 UserNotFoundException 映射捕获
        throw new UserNotFoundException(id);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}