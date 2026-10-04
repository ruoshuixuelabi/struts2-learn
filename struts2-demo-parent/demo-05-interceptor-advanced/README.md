# Demo 05: 拦截器高级用法

演示 Struts 7.3.0 拦截器高级特性：**拦截器栈顺序** + **`includeMethods` / `excludeMethods` 方法过滤** + **`MethodFilterInterceptor`**。

本 demo 模拟"权限校验"场景：
- `LogInterceptor`（继承 `AbstractInterceptor`）记录所有方法调用
- `AuthInterceptor`（继承 `MethodFilterInterceptor`）仅对 `add/update/delete` 写操作生效，跳过 `list/view` 读操作
- `authStack` 自定义拦截器栈：`log → auth → defaultStack`

## 启动

```bash
mvn -pl demo-05-interceptor-advanced jetty:run
```

访问：
- http://localhost:8080/demo-05-interceptor-advanced/user-list.action （跳过 Auth）
- http://localhost:8080/demo-05-interceptor-advanced/user-add.action （触发 Auth，未登录跳到 login）

## 文件清单

- `UserAction.java`：包含 list / view / add / update / delete 多个方法
- `LogInterceptor.java`：通用日志拦截器（继承 `AbstractInterceptor`）
- `AuthInterceptor.java`：按方法过滤的权限拦截器（继承 `MethodFilterInterceptor`，含 `requiredRole` 参数）
- `struts.xml`：拦截器栈 + includeMethods / excludeMethods + 自定义 param
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `*.jsp`：list.jsp / view.jsp / login.jsp / forbidden.jsp

## 对应文档

参见 `struts2-learn/14-拦截器高级用法.md`
