package com.example.learn.struts2.demo13;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.ModelDriven;

import com.example.learn.struts2.demo13.model.User;

/**
 * 演示 ModelDriven 接口：
 *   - 必须 new 一个 model 实例（params 拦截器会把请求参数绑定到这里）
 *   - 不需要给 Action 写 setUser()（params 不会绑到 Action 的 user 属性）
 *   - 暴露 getUser() 是为了 JSP 访问
 *
 * 表单场景（User 是一组字段的 POJO）推荐用 ModelDriven；
 * 字段零散场景直接在 Action 上写 setter 即可。
 */
public class ModelDrivenAction extends ActionSupport implements ModelDriven<User> {

    private final User user = new User();

    @Override
    public User getModel() {
        return user;
    }

    public String save() {
        // 模拟保存：把参数绑定到 model 后调用 service
        return SUCCESS;
    }

    public User getUser() { return user; }
}