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
