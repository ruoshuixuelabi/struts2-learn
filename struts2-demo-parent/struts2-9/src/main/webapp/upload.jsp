<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<s:debug></s:debug>
	<!-- 注意修改为simple主题,来显示前端页面的错误消息 -->
	<s:form action="testUpload"  method="post" enctype="multipart/form-data" theme="simple">
        <s:fielderror name="ppt"></s:fielderror>
		<s:actionerror/>
		PPTFile: <s:file name="ppt" label="PPTFile"></s:file>
		PPTDesc: <s:textfield name="pptDesc[0]" label="PPTDesc"></s:textfield>
		<br><br>
		PPTFile:<s:file name="ppt" label="PPTFile"></s:file>
		PPTDesc:<s:textfield name="pptDesc[1]" label="PPTDesc"></s:textfield>
		<br><br>
		PPTFile:<s:file name="ppt" label="PPTFile"></s:file>
		PPTDesc:<s:textfield name="pptDesc[2]" label="PPTDesc"></s:textfield>
		<br><br>
		<s:submit></s:submit>
	</s:form>
</body>
</html>