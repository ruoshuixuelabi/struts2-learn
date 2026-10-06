# Demo 11: 文件上传与下载（Struts 7.3.0）

演示 Struts 2.x 文件上传（`actionFileUpload` 拦截器，7.x 推荐新名）+ 文件下载（`stream` Result）。

## 启动

```bash
mvn -pl demo-11-upload jetty:run
```

测试 URL：

- http://localhost:8080/demo-11-upload/index.action （上传表单）
- http://localhost:8080/demo-11-upload/download.action?fileName=hello.txt （下载示例文件，target/hello.txt 已预置）

## 文件清单

- `UploadAction.java`：3 个属性（upload / uploadFileName / uploadContentType），由 `actionFileUpload` 拦截器自动注入
- `DownloadAction.java`：提供 `InputStream` getter，配合 `<result type="stream">`
- `struts.xml`：声明 actionFileUpload 拦截器 + 全局大小/类型常量 + stream result
- `web.xml`：注册 `StrutsPrepareAndExecuteFilter`
- `index.jsp`：上传表单（`enctype="multipart/form-data"`）
- `upload-success.jsp`：上传成功页（展示文件名、MIME、保存路径）
- `upload-error.jsp`：上传失败回退页（对应 result name="input"）
- `WEB-INF/uploads/hello.txt`：示例下载文件
- `UploadActionTest.java`：JUnit 5 + struts2-junit-plugin 单元测试

## 对应文档

参见 `struts2-learn/12-文件上传与下载.md`

## 7.x 关键点

- 拦截器名：`actionFileUpload`（2.x 的 `fileUpload` 是其兼容别名）
- 配置常量：`struts.actionFileUpload.maxSize`（不是 `struts.multipart.maxSize`）
- 推荐为接收参数的属性加 `@StrutsParameter` 注解（演示中省略，可按需补充）