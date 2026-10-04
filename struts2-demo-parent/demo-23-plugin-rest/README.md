# Demo 23: Plugin - REST

`struts2-rest-plugin` 演示：RESTful 风格 URL，通过 HTTP method + `!method` 后缀区分 Action 业务方法。

## 启动

```bash
mvn -pl demo-23-plugin-rest jetty:run
```

访问：
- GET 列表：`http://localhost:8080/demo-23-plugin-rest/users!index`
- GET 详情：`http://localhost:8080/demo-23-plugin-rest/users!show?id=1`
- POST 创建：`http://localhost:8080/demo-23-plugin-rest/users!create`
- PUT 更新：`http://localhost:8080/demo-23-plugin-rest/users!update?id=1`
- DELETE 删除：`http://localhost:8080/demo-23-plugin-rest/users!destroy?id=1`

## 文件清单

- `UserResourceAction.java`：含 `index/show/create/update/destroy` 5 个方法
- `struts.xml`：使用 `struts.mapper.action.prefix` + `rest-default` 包 + JSON result
- `web.xml`：注册 Struts 过滤器

## 关键配置

```xml
<constant name="struts.mapper.action.prefix" value="!"/>
<package name="default" namespace="/" extends="rest-default">
    <action name="users" class="...UserResourceAction">
        <result name="success" type="json">
            <param name="root">users</param>
        </result>
        <result name="create" type="json"/>
        <result name="destroy" type="json"/>
    </action>
</package>
```

`!method` 风格（传统 Action 用 `!` 前缀切换方法）比 RestfulActionMapper 简单，浏览器表单也能直接 POST。

## 对应文档

参见 `struts2-learn/24-插件-REST.md`
