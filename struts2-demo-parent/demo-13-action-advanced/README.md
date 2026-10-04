# Demo 13: 高级 Action 特性（Struts 7.3.0）

演示 Struts 2.x 三大高级 Action 特性：

1. **Preparable** — Action 执行前的 `prepare()` 钩子
2. **ModelDriven** — 参数绑定到 `getModel()` 返回的对象
3. **ValidationAware** — `addFieldError()` / `addActionError()` 错误收集

## 启动

```bash
mvn -pl demo-13-action-advanced jetty:run
```

测试 URL：

- http://localhost:8080/demo-13-action-advanced/index.jsp （导航）
- http://localhost:8080/demo-13-action-advanced/preparable!edit.action?id=42 （prepare 钩子被调用，user 字段已加载）
- http://localhost:8080/demo-13-action-advanced/model-driven!save.action （参数绑定到 User 模型）
- http://localhost:8080/demo-13-action-advanced/validation-aware!check.action （编程式校验）

## 文件清单

- `model/User.java`：ModelDriven 用的 POJO（id / name / age）
- `PreparableAction.java`：实现 Preparable，prepare() 钩子加载 user
- `ModelDrivenAction.java`：实现 ModelDriven，参数绑定到 User
- `ValidationAwareAction.java`：实现 ValidationAware 的 addFieldError 演示（继承 ActionSupport 自动获得）
- `struts.xml`：4 个 action 映射
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `index.jsp` / `preparable-edit.jsp` / `model-driven.jsp` / `validation.jsp`：页面
- `*Test.java`：JUnit 5 测试

## 对应文档

参见 `struts2-learn/15-高级Action特性.md`

## Action 生命周期（简化）

```
请求到达
  ↓
prepare()         ← Preparable 接口
  ↓
params 拦截器注入  ← 优先到 ModelDriven 的 model
  ↓
execute()/业务方法
  ↓
ValidationAware.addFieldError() 收集错误
  ↓
Result 处理
```