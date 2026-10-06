<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		修改主题:
		      通过UI标签的theme属性。
		      在一个表单里,若没有给出某个UI标签的theme属性,它将使用这个表单的主题。
		      在page,request,session或application中添加一个theme属性。
		      修改struts.properties文件中的struts.ui.theme属性。
	 -->
	<% 
		request.setAttribute("theme", "xhtml");//这里测试往request域对象添加一个theme属性来修改主题
	%>
	<s:form action="emp-save" theme="simple">
		<s:textfield name="name" label="Name"></s:textfield>
		<s:password name="password" label="Password"></s:password>
		<s:radio name="gender" list="#{'1':'Male','0':'Female'}" label="Gender"></s:radio>
		<s:select list="#request.depts" listKey="deptId"
			listValue="deptName" name="dept" label="Department"></s:select>
		<s:checkboxlist list="#request.roles" listKey="roleId"
			listValue="roleName" name="roles" label="Role"></s:checkboxlist>
		<s:textarea name="desc" label="Desc"></s:textarea>
		<s:submit></s:submit>
	</s:form>
</body>
</html>