# Demo 12: 结果类型 ResultType（Struts 7.3.0）

演示 Struts 2.x 四种 ResultType：**dispatcher**（默认转发）/ **redirect**（302 重定向）/ **chain**（链式 Action）/ **json**（JSON 响应）。

## 启动

```bash
mvn -pl demo-12-result jetty:run
```

测试 URL：

- http://localhost:8080/demo-12-result/index.action （导航页，列出 4 个 demo 链接）
- http://localhost:8080/demo-12-result/dispatcher.action （dispatcher 转发 → dispatcher.jsp）
- http://localhost:8080/demo-12-result/redirect.action （302 重定向 → redirect-target.jsp）
- http://localhost:8080/demo-12-result/chain-first.action （chain 链式 → chain-second.action → chain-result.jsp）
- http://localhost:8080/demo-12-result/json.action （JSON Result → 直接返回 JSON 字符串）

## 文件清单

- `ResultAction.java`：单一 Action 暴露 4 个业务方法（dispatch / redirect / chainFirst / json）
- `struts.xml`：4 个 action + chain 链式声明 + 全局 result
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `index.jsp`：导航页
- `dispatcher.jsp`：dispatcher result 目标（同一 request，可读 message）
- `redirect-target.jsp`：redirect result 目标（新 request，message 从 session 来）
- `chain-result.jsp`：链式 Action 最终目标
- `ResultActionTest.java`：JUnit 5 单元测试

## 对应文档

参见 `struts2-learn/13-结果类型ResultType.md`

## 四种 ResultType 关键差异

| 类型 | URL | 请求次数 | request 域可见 |
|---|---|---|---|
| dispatcher | 不变 | 1 | 是 |
| redirect | 改变 | 2 | 否（用 session 替代） |
| chain | 不变 | 1（同值栈） | 是 |
| json | 不变 | 1 | 是 |