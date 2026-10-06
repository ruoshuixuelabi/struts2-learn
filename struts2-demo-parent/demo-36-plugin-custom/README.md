# Demo 36: Plugin - Custom（自定义插件）

演示如何**自己写一个 Struts 2 插件**：包含自定义 Result 类型，
并打成独立 JAR 通过 `META-INF/struts-plugin.xml` 注册到 Struts 容器。

## 启动

```bash
# 编译并安装自定义插件到本地 Maven 仓库
mvn -pl demo-36-plugin-custom -am install -DskipTests

# 跑插件的单元测试
mvn -pl demo-36-plugin-custom/custom-result-plugin test
```

业务应用引入 `custom-result-plugin.jar` 后即可使用 `type="uppercase"`。

## 子模块结构

- `pom.xml`：聚合 pom，packaging=pom，包含 `custom-result-plugin` 子模块
- `custom-result-plugin/`：自定义插件的 Maven 子模块（最终打成独立 JAR）
  - `pom.xml`：packaging=jar，依赖 `struts2-core` + `jakarta.servlet-api`
  - `UpperCaseResult.java`：继承 `StrutsResultSupport`，把 Action 返回的位置字符串转大写后输出
  - `struts-plugin.xml`：定义 `custom-default` 包，注册 `uppercase` result-type
  - `UpperCaseResultTest.java`：单元测试，验证大写转换逻辑

## 自定义 Result 实现

```java
package com.example.learn.struts2.custom;

import org.apache.struts2.result.StrutsResultSupport;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class UpperCaseResult extends StrutsResultSupport {
    @Override
    protected void doExecute(String finalLocation, jakarta.servlet.http.HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        response.setContentType("text/plain;charset=UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.write(finalLocation.toUpperCase());  // 转大写输出
        }
    }
}
```

## 插件注册文件

`META-INF/struts-plugin.xml`：

```xml
<struts>
    <package name="custom-default" extends="struts-default">
        <result-types>
            <result-type name="uppercase"
                         class="com.example.learn.struts2.custom.UpperCaseResult"/>
        </result-types>
    </package>
</struts>
```

## 业务应用如何使用

业务 WAR 引入 `custom-result-plugin.jar` 后，`struts.xml` 继承 `custom-default` 即可使用：

```xml
<package name="app" extends="custom-default">
    <action name="shout" class="...ShoutAction">
        <result type="uppercase">hello world</result>  <!-- 输出：HELLO WORLD -->
    </action>
</package>
```

## 关键点

- **继承 `StrutsResultSupport`**：比直接实现 `Result` 接口少写 80% 样板代码
- **`struts-plugin.xml` 自动加载**：Struts 2 启动时扫描所有 jar 的 `META-INF/struts-plugin.xml`
- **包继承**：业务包继承插件定义的 `custom-default`，复用其注册的 result-type / interceptor
- **命名空间**：插件包名建议加前缀（如 `custom-default`）避免与业务包冲突
- **插件复用**：自定义 Result 可被 N 个业务模块共用，是典型的"框架 + 业务"分层

## 对应文档

参见 `struts2-learn/36-自定义插件.md`（如未实现）。
