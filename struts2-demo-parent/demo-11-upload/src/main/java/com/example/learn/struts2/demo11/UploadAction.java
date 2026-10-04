package com.example.learn.struts2.demo11;

import org.apache.struts2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import jakarta.servlet.ServletContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Struts 7.x 文件上传 Action。
 *
 * actionFileUpload 拦截器会自动解析 multipart 请求并注入以下 3 个属性：
 *   - upload              (File)         临时文件
 *   - uploadFileName      (String)       原始文件名
 *   - uploadContentType   (String)       MIME
 *
 * Action 自行决定把临时文件 move 到目标位置（这里是 WEB-INF/uploads/）。
 */
public class UploadAction extends ActionSupport {

    private File upload;
    private String uploadFileName;
    private String uploadContentType;
    private String savedPath;

    @Override
    public String execute() throws IOException {
        if (upload == null) {
            addActionError("请选择文件");
            return INPUT;
        }

        // 把临时文件 copy 到 WEB-INF/uploads/（最佳实践：放到 WEB-INF 之外的真实文件系统目录）
        ServletContext ctx = ServletActionContext.getServletContext();
        String targetDir = ctx.getRealPath("/WEB-INF/uploads");
        File dir = new File(targetDir);
        if (!dir.exists() && !dir.mkdirs()) {
            addActionError("无法创建上传目录: " + targetDir);
            return INPUT;
        }

        File target = new File(dir, uploadFileName);
        Files.copy(upload.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
        savedPath = target.getAbsolutePath();

        return SUCCESS;
    }

    // ---- getters / setters for Struts params interceptor ----
    public File getUpload() { return upload; }
    public void setUpload(File upload) { this.upload = upload; }

    public String getUploadFileName() { return uploadFileName; }
    public void setUploadFileName(String uploadFileName) { this.uploadFileName = uploadFileName; }

    public String getUploadContentType() { return uploadContentType; }
    public void setUploadContentType(String uploadContentType) { this.uploadContentType = uploadContentType; }

    public String getSavedPath() { return savedPath; }
}