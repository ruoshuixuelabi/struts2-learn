# Demo 22: Plugin - JSON

`struts2-json-plugin` 演示：Action 返回 JSON 数据（AJAX 接口核心）。

## 启动

```bash
mvn -pl demo-22-plugin-json jetty:run
```

访问：http://localhost:8080/demo-22-plugin-json/user-api.action

返回 JSON：
```json
{
    "id": 1,
    "username": "alice",
    "email": "alice@example.com"
}
```

`password` 字段由 `excludeProperties` 过滤掉。

## 文件清单

- `UserAction.java`：提供 `getUser()` getter；通过 `struts.xml` 配置 `type="json"` 序列化
- `User.java`：数据模型（含 `password` 敏感字段）
- `struts.xml`：使用 `json-default` 包 + `<result type="json">` + `<param name="root">` + `<param name="excludeProperties">`
- `web.xml`：注册 Struts 过滤器

## 配置关键点

```xml
<package name="default" namespace="/" extends="json-default">
    <action name="user-api" class="...UserAction">
        <result type="json">
            <param name="root">user</param>
            <param name="excludeProperties">user.password</param>
        </result>
    </action>
</package>
```

## 前端调用示例

```javascript
fetch('/demo-22-plugin-json/user-api.action?userId=1')
    .then(response => response.json())
    .then(data => console.log(data.username));
```

## 对应文档

参见 `struts2-learn/23-插件-JSON.md`
