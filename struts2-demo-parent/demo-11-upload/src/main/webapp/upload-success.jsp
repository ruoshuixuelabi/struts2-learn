<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head><title>Upload Success</title></head>
<body>

<h1>上传成功</h1>

<table>
    <tr><th>文件名</th><td>${uploadFileName}</td></tr>
    <tr><th>MIME</th><td>${uploadContentType}</td></tr>
    <tr><th>保存路径</th><td>${savedPath}</td></tr>
</table>

<p><a href="index.jsp">返回</a></p>

</body>
</html>