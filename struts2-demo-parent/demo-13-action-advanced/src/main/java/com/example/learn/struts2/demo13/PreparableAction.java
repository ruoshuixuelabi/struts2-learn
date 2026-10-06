package com.example.learn.struts2.demo13;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.Preparable;

import com.example.learn.struts2.demo13.model.User;
import org.apache.struts2.interceptor.parameter.StrutsParameter;

/**
 * 演示 Preparable 接口：每次请求到达 Action 业务方法前调用 prepare()。
 *
 * 注意：defaultStack 已经包含 prepare 拦截器，无需显式 interceptor-ref。
 *
 * 实战：prepare() 做轻量初始化（如根据 id 加载 user 对象），
 *     不要做重型操作（每次请求都执行 → 性能差）。
 */
public class PreparableAction extends ActionSupport implements Preparable {

    private Long id;
    private User user;

    @Override
    public void prepare() throws Exception {
        // 每次请求都跑一次
        if (id != null) {
            // 模拟从数据库加载
            user = new User(id, "loaded-" + id, 20 + (int) (id % 50));
        } else {
            user = new User();
        }
    }

    public String edit() {
        return SUCCESS;
    }
    @StrutsParameter
    public Long getId() { return id; }
    @StrutsParameter
    public void setId(Long id) { this.id = id; }
    @StrutsParameter
    public User getUser() { return user; }
}