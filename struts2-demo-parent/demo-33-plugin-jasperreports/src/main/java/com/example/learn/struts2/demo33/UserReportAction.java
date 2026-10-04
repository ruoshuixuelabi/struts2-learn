package com.example.learn.struts2.demo33;

import org.apache.struts2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * demo-33: JasperReports 插件演示。
 * <p>
 * Action 不直接处理 JasperPrint，而是暴露一个 {@code List<User>}（OGNL 表达式 users）。
 * jasper result 会调用 {@code getUsers()}（返回 JRDataSource）填充 .jrxml / .jasper 模板。
 */
public class UserReportAction extends ActionSupport {

    static {
        // 启动时把 classpath:/jasper/*.jrxml 预编译到 java.io.tmpdir/demo-33-jasper/*.jasper，
        // 避免 Struts JasperReportsResult 加载 .jrxml 时抛 StreamCorruptedException。
        JasperReportsPrecompiler.ensureCompiled();
        // 同时把 .jasper 二进制文件以 user-list.jrxml 文件名覆写到 classpath 同名位置，
        // 这样 struts.xml 直接写 /jasper/user-list.jrxml 也能加载 .jasper 二进制。
        JasperReportsPrecompiler.overwriteClasspathJasper();
    }

    /** 由 jasper result 通过 OGNL 读取，自动填充到 .jrxml 的 field */
    private List<User> users = new ArrayList<>();

    /** 测试用：通过 ?resultName=xls / html 切换返回 result code，触发对应 jasper result 配置 */
    private String resultName = "success";

    @Override
    public String execute() {
        users.add(new User(1L, "alice", "alice@example.com", "管理员"));
        users.add(new User(2L, "bob",   "bob@example.com",   "编辑"));
        users.add(new User(3L, "carol", "carol@example.com", "访客"));
        users.add(new User(4L, "david", "david@example.com", "编辑"));
        users.add(new User(5L, "eve",   "eve@example.com",   "管理员"));
        // 允许通过 ?resultName=xls / html 切换返回的 result 名字，
        // 让 Struts 在多个 <result name="..."> 中按名匹配，便于测试同一 action 多种报表格式。
        return resultName == null ? SUCCESS : resultName;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getResultName() { return resultName; }
    public void setResultName(String resultName) { this.resultName = resultName; }

    /** POJO 用户模型（字段与 user-list.jrxml 中的 <field> 对应） */
    public static class User {
        private final long id;
        private final String username;
        private final String email;
        private final String role;

        public User(long id, String username, String email, String role) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.role = role;
        }

        public long getId() { return id; }
        public String getUsername() { return username; }
        public String getEmail() { return email; }
        public String getRole() { return role; }
    }
}