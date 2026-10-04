package com.example.learn.struts2.legacy08;

import java.io.File;

/**
 * 上传文件元数据封装（Struts 2 自动注入 Pell 解析后的 multipart 字段）。
 *
 * Pell 解析器把 multipart/form-data 请求解析成同 jakarta commons-fileupload
 * 兼容的字段结构，因此 Struts 2 的 FileUploadInterceptor 仍然能识别：
 *   xxxFileName, xxxContentType, xxx（File）
 */
public class UploadFile {

    private String name;
    private long size;
    private String contentType;

    public UploadFile() {}

    public UploadFile(String name, long size, String contentType) {
        this.name = name;
        this.size = size;
        this.contentType = contentType;
    }

    /** 从 Pell 解析得到的 File + 文件名 + Content-Type 构造 UploadFile */
    public static UploadFile from(File file, String name, String contentType) {
        if (file == null) {
            return null;
        }
        return new UploadFile(name, file.length(), contentType);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public long getSize() { return size; }
    public void setSize(long size) { this.size = size; }

    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }

    @Override
    public String toString() {
        return "UploadFile{name='" + name + "', size=" + size + ", contentType='" + contentType + "'}";
    }
}
