package com.example.learn.struts2.demo11;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import jakarta.servlet.ServletContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 文件下载 Action。
 *
 * stream ResultType 会调用 getInputStream() 拿到 InputStream 写到响应。
 * 文件名通过 fileName 参数传入（实际生产中应使用数据库 ID 映射，防路径穿越）。
 */
public class DownloadAction extends ActionSupport {

    private String fileName;
    private InputStream inputStream;

    @Override
    public String execute() throws Exception {
        if (fileName == null || fileName.isBlank()) {
            fileName = "hello.txt";
        }

        // 白名单校验：只允许下载预先准备好的示例文件（防路径穿越）
        if (fileName.contains("/") || fileName.contains("\\") || fileName.contains("..")) {
            throw new IllegalArgumentException("非法文件名: " + fileName);
        }

        ServletContext ctx = ServletActionContext.getServletContext();
        File file = new File(ctx.getRealPath("/WEB-INF/uploads"), fileName);
        if (!file.exists()) {
            throw new IllegalStateException("文件不存在: " + fileName);
        }
        inputStream = new FileInputStream(file);
        return SUCCESS;
    }

    public InputStream getInputStream() { return inputStream; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
}