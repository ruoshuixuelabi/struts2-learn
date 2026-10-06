# Demo 06: 值栈 ValueStack

演示 Struts 7.3.0 值栈（ValueStack）三种放数据方式：
1. **Action 属性**（最常用）—— 直接给字段赋值
2. **`stack.push(tempObj)`** —— 把临时对象压栈（OGNL 可访问）
3. **`stack.set(key, value)`** —— 不增加栈深度，按 key 放值

JSP 用 `<s:property>` / `${}` / `<s:iterator>` 三种方式读取。

## 启动

```bash
mvn -pl demo-06-valuestack jetty:run
```

访问：http://localhost:8080/demo-06-valuestack/stack.action

## 文件清单

- `ValueStackAction.java`：演示三种 push / set / 字段赋值
- `ExtraInfo.java`：push 的临时对象（POJO）
- `struts.xml`：注册 action
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `stack.jsp`：使用 `<s:property>` `<s:iterator>` 读取值栈

## 对应文档

参见 `struts2-learn/07-值栈ValueStack.md`

# 错误解决
**你的直觉完全正确，而且这次一查就实锤了。** Struts 7 在 OGNL 安全层引入了一个默认开启的**白名单机制**——这是 6.4 引入、**7.0 起默认强制**的新行为，Struts 2.3 时代根本没有这东西。

## 官方机制（struts.apache.org 安全文档原文）

> "Now, in addition to enforcing the exclusion list, **classes involved in OGNL expression must also belong to a list of allowlisted classes and packages**. By default, all required Struts classes are allowlisted **as well as any classes that are defined in your struts.xml package configurations**."

翻译过来：OGNL 表达式能碰哪些类，现在走**白名单**——默认只有 Struts 自身类 + **你在 struts.xml 里配置过的类**（Action 类）在里面。

## 你的症状逐条对上

| 现象 | 白名单视角的解释 |
|---|---|
| `message`、`items` 能显示 | ValueStackAction 在 struts.xml 的 `<action class=...>` 里定义了 → 自动白名单 ✓ |
| `greeting`（Map）能显示 | JDK 核心集合类在白名单 ✓ |
| `#session.userName` 能显示 | ActionContext 相关类在白名单 ✓ |
| **`label`（ExtraInfo）空白** | ExtraInfo 是你 execute 里 `new` 出来 push 的，**struts.xml 从没提过它** → 不在白名单 → OGNL 访问被拦，**静默返回 null** ✗ |
| debug 页能看到 label 值 | debug 页用 BeanMap 反射展示，**不走 OGNL 白名单检查**——所以它能看见、JSP 看不见 |

而且文档明确说了：被拦时**日志会有 warning**——你 DEBUG 日志都开着，去搜 "allowlist" 或 "ExtraInfo"，一定有"blocked/not allowlisted"字样的记录，那就是案发现场。

## 修复（一行 constant）

struts.xml 加：

```xml
<!-- 白名单：允许 OGNL 访问 ExtraInfo（逐个类） -->
<constant name="struts.allowlist.classes" value="com.example.learn.struts2.demo06.ExtraInfo"/>
```

或者把整个 demo 包都放进去（推荐，后续 demo 的 DTO 都受益）：

```xml
<constant name="struts.allowlist.packageNames" value="com.example.learn.struts2.demo06"/>
```

重启 Jetty，`label` 立刻出现。

## 彩蛋：还你清白

你之前猜 "@StrutsParameter 注解"——方向错了但直觉有依据：官方文档说**被 `@StrutsParameter` 标注的 getter 的返回类型会自动进白名单**。而且迁移指南点名了 "template rendering" 场景需要手动白名单 POJO/DTO——你的 ValueStack demo 正好就是 template rendering。

## 存档结论

**Struts 7 学习三大新规矩**（全是 2.3 老教程不会告诉你的）：

1. **参数注入**：setter 要 `@StrutsParameter`
2. **OGNL 访问**：视图里要读的类要进 `struts.allowlist.classes/packageNames`
3. **EL 回显**：request 包装类的值栈查找默认关闭（`struts.disable.request.attribute.value.stack.lookup`）

建议直接给每个现代 demo 的 struts.xml 都加上 `struts.allowlist.packageNames` 指向自己的包——一了百了。这个案子从 ValueStack 教学升级为 Struts 7 安全模型全套实景课，血赚。