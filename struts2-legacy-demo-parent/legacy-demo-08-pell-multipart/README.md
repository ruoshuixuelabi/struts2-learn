# legacy-demo-08-pell-multipart

**主题**：Struts 2.5.30 Pell Multipart 上传（struts2-pell-multipart-plugin）

**状态**：**仅配置示例**（Pell 解析器需要额外 servlet 容器配置）

Pell Multipart 是轻量级 multipart 解析器（仅一个 jar，约 13KB），
替代 commons-fileupload 处理 `multipart/form-data` 请求。
Struts 2 通过常量 `struts.multipart.parser=pell` 切换。

## 启动

```bash
mvn -pl legacy-demo-08-pell-multipart -am install -DskipTests

# 部署到 Tomcat 9.x，访问：
# http://localhost:8080/legacy-demo-08-pell-multipart/uploadForm.action
```

## 文件清单

- `UploadAction.java`：Action，含 `upload`（File）/ `uploadFileName` / `uploadContentType` 三个自动注入字段
- `UploadFile.java`：上传文件元数据封装（name + size + contentType）
- `struts.xml`：声明 `struts.multipart.parser=pell` + `struts.multipart.maxSize=10MB`
- `web.xml`：Struts 2 过滤器
- `form.jsp`：上传表单（`enctype="multipart/form-data"`）
- `success.jsp`：展示上传成功信息

## 关键点

- **依赖**：`struts2-pell-multipart-plugin-2.5.30.jar`
- **核心常量**：
  ```xml
  <constant name="struts.multipart.parser" value="pell"/>
  <constant name="struts.multipart.maxSize" value="10485760"/>
  ```
- **字段命名约定**：`xxx` (File) / `xxxFileName` / `xxxContentType` —— Struts 2 自动注入
- **Pell 优势**：单 jar、无 commons 依赖、启动更快
- **7.x 移除原因**：Pell 项目已停维护；Struts 2.5 默认 `jakarta-stream` 已足够好
- **现代方案**：保留 `jakarta-stream`（基于 Servlet 3.0 `Part` API），demo-11 已演示

## 对应文档

参见 `struts2-learn/历史插件-PellMultipart.md`（如未实现）。
