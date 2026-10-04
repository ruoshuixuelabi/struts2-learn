<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Struts 2 + Pell Multipart 文件上传</title>
</head>
<body>
<h2>Struts 2 + Pell Multipart 文件上传（7.x 已移除此插件）</h2>
<s:form action="upload" method="post" enctype="multipart/form-data">
    <s:file name="upload" label="选择文件"/>
    <s:submit value="上传"/>
</s:form>
</body>
</html>
