package com.example.learn.struts2.demo11;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.action.UploadedFilesAware;
import org.apache.struts2.dispatcher.multipart.UploadedFile;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * Struts 7.x 文件上传 Action（actionFileUpload 拦截器模式）。
 *
 * 与经典三字段模式的区别：
 *   - 文件不由 params 拦截器注入，而是拦截器回调 withUploadedFiles() 直接送达
 *   - 原始文件名 / MIME / 大小都在 UploadedFile 对象上自带，不再需要 uploadFileName 等伴随字段
 */
public class UploadAction extends ActionSupport implements UploadedFilesAware {

    private UploadedFile upload;
    private String savedPath;

    /** 拦截器回调：文件列表直接交给你，不需要任何注解 */
    @Override
    public void withUploadedFiles(List<UploadedFile> files) {
        if (files != null && !files.isEmpty()) {
            this.upload = files.get(0);
        }
    }

    @Override
    public String execute() throws IOException {
        if (upload == null) {
            addActionError("请选择文件");
            return INPUT;
        }

        ServletContext ctx = ServletActionContext.getServletContext();
        Path dir = Path.of(ctx.getRealPath("/WEB-INF/uploads"));
        Files.createDirectories(dir);

        // 原始文件名用 getOriginalName()；内容用 getInputStream()（7.3.0 推荐，不落临时盘）
        Path target = dir.resolve(upload.getOriginalName());
        try (InputStream in = upload.getInputStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }
        savedPath = target.toAbsolutePath().toString();

        return SUCCESS;
    }
    public UploadedFile getUpload() { return upload; }
    public String getSavedPath() { return savedPath; }
}