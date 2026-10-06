<%@page import="java.util.ArrayList"%>
<%@page import="com.fuxi.struts.valuestack.City"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
</head>
<body>
	<%
		List<City> cities=new ArrayList<City>();
		cities.add(new City(101,"AA"));
		cities.add(new City(102,"BB"));
		cities.add(new City(103,"CC"));
		cities.add(new City(104,"DD"));
		request.setAttribute("cities",cities);
 	%>
		<!-- 
				表单使用:
				1.使用和HTML的form标签的感觉差不多。
				2.Struts 2的form标签会生成一个table,以进行自动的排版。
				3.可以对表单提交的值进行回显:从栈顶对象开始匹配属性,并把匹配到的属性值赋值到标签的value中,
				若栈顶对象没有对应的属性,则依次向下找相对应的属性。
 		-->
	<s:debug></s:debug>
	<s:form action="save">
		<s:hidden name="userId"></s:hidden>
		<s:textfield name="userName" label="UserName"></s:textfield>
		<s:password name="password" label="Password" ></s:password>
		<s:textarea name="desc" label="Desc"></s:textarea>
		<s:checkbox name="married" label="Married"></s:checkbox>
		<!-- 构建一个性别,性别是单选,注意些标签不要忘记name属性 -->
		<s:radio name="gender" list="#{'1':'Male','0':'Female'}" label="Gender"></s:radio>
		<!-- 服务器端需要使用集合类型,以保证能够被正常的回显,如果服务端是数组类型则不能回显 -->
		<s:checkboxlist list="#request.cities" listKey="cityId"
			listValue="cityName" label="City" name="city"></s:checkboxlist>
		<s:select list="{11,12,13,14,15,16}" headerKey="" headerValue="请选择"
			name="age" label="Age">
			<!-- 
				s:optgroup可以用作s:select子标签,用于显示更多的下拉框
				注意:必须指定键值对,而不能使用一个集合,让其既作为键又作为值
			-->
			<s:optgroup label="11-11" list="#{11:11}"></s:optgroup>
			<s:optgroup label="12-12" list="#{12:12}"></s:optgroup>
		</s:select>
		<s:submit></s:submit>
	</s:form>
</body>
</html>