# Demo 35: Async Plugin（异步 Action）

> 对应章节：[`37-插件-Async.md`](../../../struts2-learn/37-插件-Async.md)

## 主题

`struts2-async-plugin` 把耗时操作从 Servlet 主线程剥离，避免阻塞容器线程池。
本 demo 演示三种异步模式：

1. **`execAndWait` + 等待页 meta refresh**（浏览器直接导航场景）
2. **AJAX 创建任务 + JSON 返回 taskId** + **SSE 实时进度推送**（前端 SPA 场景）

## 关于"@Async 注解"

MD 第 37 章提到 `@Async` 注解，但 `struts2-async-plugin` 7.3.0 实际未提供该注解；
异步能力通过 **`execAndWait` 拦截器**（来自 `struts2-core`，Struts 7.x 改名 camelCase；旧名 `executeAndWait` 已移除）+ **Servlet 3.0 `AsyncContext`** 组合实现。
本 demo 在 Action 类顶部用 Java 注释说明这点。

## 现代 `execAndWait` 拦截器机制（Struts 2.5+/7.x）

> ⚠️ 关键：早期 Struts（2.1.x）的拦截器要求 Action 自己 `new Thread()`；
> 现代版本（2.5+/7.x）的拦截器**自动**把 `execute()` 放到后台线程池执行——HTTP 主线程永不阻塞。
> **拦截器注册名变更**：Struts 7.x 把 `executeAndWait` 改名为 `execAndWait`（camelCase 一致性）。旧名已移除。

```
1. 浏览器 GET /async/long-task.action（第一次）
2. ExecuteAndWaitInterceptor.doIntercept()：
   - session 没有 BackgroundProcess → 创建并 submit 到后台线程池
   - 立即返回 "wait" → 渲染 wait.jsp
3. 后台线程：ExecuteAndWaitInterceptor 调 invocation.invoke() → LongTaskAction.execute()
   - execute() 应**同步**执行实际业务（不再 new Thread！）
   - execute() 返回 SUCCESS → 拦截器存为 bp.getResult()
4. wait.jsp 的 <meta refresh> 2 秒后再请求 /async/long-task.action（同 URL！）
5. 拦截器从 session 读 BackgroundProcess：
   - bp.isDone() == false → 再返回 "wait" → wait.jsp 再渲染
   - bp.isDone() == true  → 返回 bp.getResult()（= SUCCESS）→ result.jsp
```

**易错点**：

- ❌ `execute()` 内自己启线程 → 拦截器后台又会跑一遍 → 双重启动
- ❌ `execute()` 返回 `"wait"` → `bp.getResult()="wait"` → 永远渲染 wait.jsp（链路断裂）
- ✅ `execute()` 同步执行实际业务，返回真实结果码（SUCCESS / ERROR）

**`ExecuteAndWaitHandler` 接口**：Struts 2.5+ 拦截器不再调用 action 的 `getResult()` / `isDone()`，
因此本 demo 不再让 Action 实现该接口（早期版本需要）。

## 关键点

- `web.xml` 必须显式开启 `<async-supported>true</async-supported>`
- 继承 `struts-default` 即可（无需 async-default）
- 任务状态建议存 Redis / DB，本 demo 用进程内 `TaskStore` 演示
- 简单场景用轮询；实时性高用 SSE
- SSE 流必须带心跳 `:keepalive\n\n`，否则代理 60 秒断开
- 异步线程不要用 `Thread.sleep` 循环，用 `ScheduledExecutorService`
- `AsyncListener` 的 `onComplete` / `onError` / `onTimeout` 必须取消调度任务，防止泄漏
- `JSON Result` 用 `includeProperties` 限制字段，避免泄露内部状态

## 改进点（vs 上一版 demo）

| 项 | 旧实现 | 新实现 |
|---|---|---|
| LongTaskAction.execute() | 启线程 + 返回 "wait"（双重启动） | 同步执行 + 返回 SUCCESS |
| LongTaskAction 接口 | implements ExecuteAndWaitHandler | 不实现（拦截器已不再调用） |
| wait.jsp meta refresh | → progress.action（链路断） | → long-task.action + 原参数 |
| SSE 心跳 | 无（>60s 任务必出问题） | 每 15 秒 `:keepalive` |
| SSE 调度 | Thread.sleep 循环 | ScheduledExecutorService |
| SSE 资源清理 | 无 | AsyncListener 取消 ScheduledFuture |
| TaskStore 内存 | 无限增长 | TTL 10 分钟自动清理 |
| AJAX 入口 | regex 解析 wait.jsp HTML | task-create.action 返回 JSON |
| JSON 字段 | 全部泄露 | includeProperties 白名单 |
| URL 硬编码 | `/demo-35-plugin-async/...` | `<s:url>` / `${pageContext.request.contextPath}` |

## 运行

```bash
mvn jetty:run
# 浏览：
#   http://localhost:8080/demo-35-plugin-async/
#   http://localhost:8080/demo-35-plugin-async/async/long-task.action
```

## 文件结构

```
demo-35-plugin-async/
├── pom.xml
├── README.md
├── src/main/java/com/example/learn/struts2/demo35/
│   ├── TaskStore.java              # 任务状态 + TTL 清理
│   ├── TaskRunner.java             # 长任务业务逻辑（两种入口复用）
│   ├── LongTaskAction.java         # execAndWait 模式（浏览器导航）
│   ├── TaskCreateAction.java       # AJAX 入口（返回 JSON taskId）
│   ├── ProgressAction.java         # 进度查询（JSON）
│   └── ProgressSSEAction.java      # SSE 实时推送（心跳 + 调度器 + Listener）
├── src/main/resources/struts.xml
├── src/main/webapp/WEB-INF/web.xml
├── src/main/webapp/WEB-INF/content/{wait,result}.jsp
├── src/main/webapp/index.html
└── src/test/java/com/example/learn/struts2/demo35/LongTaskActionTest.java
```