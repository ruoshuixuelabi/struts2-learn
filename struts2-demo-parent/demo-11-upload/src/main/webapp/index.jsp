<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Upload &amp; Download Demo</title>
    <style>
        body { font-family: -apple-system, sans-serif; margin: 2em; max-width: 640px; }
        section { margin-bottom: 2em; padding: 1em; border: 1px solid #ddd; border-radius: 6px; }
        h2 { margin-top: 0; }
    </style>
</head>
<body>

<h1>Demo 11: 文件上传 &amp; 下载</h1>

<section>
    <h2>上传文件</h2>
    <s:form action="upload" enctype="multipart/form-data" method="post">
        <s:file name="upload" label="选择文件"/>
        <s:submit value="上传"/>
    </s:form>
    <s:actionerror/>
    <s:fielderror/>
</section>

<section>
    <h2>下载示例文件</h2>
    <ul>
        <li>
            <s:url action="download" var="dl">
                <s:param name="fileName">hello.txt</s:param>
            </s:url>
            <a href="${dl}">下载 hello.txt</a>
        </li>
        <li>
            <s:url action="download" var="dl2">
                <s:param name="fileName">readme.md</s:param>
            </s:url>
            <a href="${dl2}">下载 readme.md</a>
        </li>
    </ul>
</section>

</body>
</html>