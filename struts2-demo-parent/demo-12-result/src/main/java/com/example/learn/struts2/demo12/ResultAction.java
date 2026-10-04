package com.example.learn.struts2.demo12;

import org.apache.struts2.ActionContext;
import org.apache.struts2.ActionSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一个 Action 演示 4 种 ResultType。
 *
 * - dispatch()     : dispatcher（默认转发）
 * - redirect()     : redirect（302 重定向）
 * - chainFirst()   : chain 第 1 步
 * - chainSecond()  : chain 第 2 步
 * - json()         : json Result（直接返回 JSON）
 */
public class ResultAction extends ActionSupport {

    private String message;
    private String username = "alice";
    private String password = "secret-123";
    private List<String> roles = new ArrayList<>();

    // -------- 1) dispatcher --------
    public String dispatch() {
        message = "来自 dispatcher 转发（同 request）";
        return SUCCESS;
    }

    // -------- 2) redirect --------
    public String redirect() {
        // redirect 后原 request 域丢失，只能放 session
        message = "来自 redirect（302 后第二个请求读不到，但 session 可用）";
        ActionContext.getContext().getSession().put("sessionMsg", message);
        return SUCCESS;
    }

    // -------- 3) chain --------
    public String chainFirst() {
        message = "from chainFirst";
        return "chain-next";
    }
    public String chainSecond() {
        // 共享值栈，能读到 chainFirst 设置的 message
        message = message + " → chainSecond";
        return SUCCESS;
    }

    // -------- 4) json --------
    public String json() {
        roles.add("admin");
        roles.add("user");
        return SUCCESS;
    }

    // ---- 用于 json 序列化的 payload（root=payload） ----
    public Map<String, Object> getPayload() {
        Map<String, Object> p = new HashMap<>();
        p.put("username", username);
        p.put("password", password);    // 演示用 excludeProperties 排除
        p.put("roles", roles);
        p.put("timestamp", System.currentTimeMillis());
        return p;
    }

    // ---- getters / setters ----
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}