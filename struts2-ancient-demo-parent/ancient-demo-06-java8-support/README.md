# ancient-demo-06-java8-support

**主题**：Struts 2.3.37 Java 8 lambda 支持（struts2-java8-support-plugin）

2.3.x 时代的 Struts 默认依赖 JDK 6 编译，拦截器栈与 Action 用的是
旧式匿名内部类。Java 8 Support 插件让 Action 方法可以直接返回 lambda
和 stream 风格的实现，是为 2.5 全面拥抱 JDK 8 铺路的过渡插件。

## 启动

```bash
mvn -pl ancient-demo-06-java8-support -am install -DskipTests

# 部署到 Tomcat 8.5+，访问：
# http://localhost:8080/ancient-demo-06-java8-support/java8.action
```

## 文件清单

- `Java8DemoAction.java`：实现 `com.opensymphony.xwork2.Action` 接口；
  `execute()` 内部使用 lambda（`Runnable`）+ `Stream` API
- `Java8DemoActionTest.java`：单元测试，验证 Action 执行成功
- `struts.xml`：标准 `struts-default` 包；`<result>/java8-result.jsp</result>`
- `web.xml`：Struts 2 过滤器
- `java8-result.jsp`：展示 `${lambdaResult}` 与 `${streamResult}`

## 关键点

- **依赖**：`struts2-java8-support-plugin-2.3.x.jar`
- **编译目标**：父项目 `<maven.compiler.source>1.8</maven.compiler.source>`
- **插件作用**：让 `Interceptor` 接口的 `intercept()` 可以用 lambda 简化
- **示例代码**：
  ```java
  Runnable runner = () -> System.out.println("Lambda 跑起来了");
  List<String> items = Arrays.asList("a", "b", "c");
  streamResult = items.stream().map(String::toUpperCase).collect(Collectors.joining(","));
  ```
- **2.5 移除原因**：2.5 把基线 JDK 提到 8 后，插件的能力并入核心；插件本身变成空壳

## 对应文档

参见 `struts2-learn/远古-Struts2.3历史插件.md`。
