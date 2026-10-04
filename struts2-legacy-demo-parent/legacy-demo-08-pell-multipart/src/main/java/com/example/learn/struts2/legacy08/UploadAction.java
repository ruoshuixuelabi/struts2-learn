package com.example.learn.struts2.legacy08;

import com.opensymphony.xwork2.ActionSupport;

import java.io.File;

/**
 * Pell Multipart 文件上传 Action。
 *
 * Pell 解析器替代 commons-fileupload 处理 multipart/form-data 请求。
 * Struts 2.5 默认使用 jakarta-stream（基于 Servlet 3.0 Part API），
 * Pell 插件 6.0 已弃用、7.x 已移除。
 *
 * 字段命名遵循 Struts 2 默认约定：
 *   upload        : File（已上传文件）
 *   uploadFileName: String（原始文件名）
 *   uploadContentType: String（MIME）
 */
public class UploadAction extends ActionSupport {

    private File upload;
    private String uploadFileName;
    private String uploadContentType;

    private UploadFile uploaded;

    public String upload() {
        if (upload == null) {
            addActionError("请选择一个文件");
            return INPUT;
        }
        uploaded = UploadFile.from(upload, uploadFileName, uploadContentType);
        return SUCCESS;
    }

    public File getUpload() { return upload; }
    public void setUpload(File upload) { this.upload = upload; }

    public String getUploadFileName() { return uploadFileName; }
    public void setUploadFileName(String uploadFileName) { this.uploadFileName = uploadFileName; }

    public String getUploadContentType() { return uploadContentType; }
    public void setUploadContentType(String uploadContentType) { this.uploadContentType = uploadContentType; }

    public UploadFile getUploaded() { return uploaded; }
    public void setUploaded(UploadFile uploaded) { this.uploaded = uploaded; }
}
