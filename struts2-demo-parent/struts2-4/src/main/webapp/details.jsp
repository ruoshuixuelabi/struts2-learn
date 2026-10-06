<%@page import="java.util.HashMap" %>
<%@page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<s:debug></s:debug>
ProductName: ^<s:property value="[0].productName"/>
<br><br>
ProductDesc: <s:property value="[1].productDesc"/>
<br><br>
ProductPrice: ${productPrice }
<br><br>
ProductPrice(可以用标签): ^<s:property value="[0].productPrice"/>
<br><br>
<!-- 上面的那个下标可以省略的比如这样,他们是等同的 -->
ProductPrice(省略下标): ^^<s:property value="productPrice"/>
<br><br>
ProductName1: ${sessionScope.product.productName}
<s:property value="#session.product.productName"/>
<br><br>
ProductName2: ${requestScope.test.productName}
<s:property value="#request.test.productName"/>
<br><br>
<!-- 使用OGNL调用public类的public类型的静态字段和静态方法 -->
<s:property value="@java.lang.Math@PI"/><br>
<s:property value="@java.lang.Math@cos(0)"/>
<br>
<!-- 调用对象栈的方法为一个属性赋值 -->
<s:property value="setProductName('xuexi')"/>
<br>
ProductName: ^<s:property value="productName"/>
<br>
<!-- 调用数组对象的属性 -->
<%
    String[] names = new String[]{"aa", "bb", "cc"};
    request.setAttribute("names", names);
%>
length: <s:property value="#attr.names.length"/><br>
names[2]:<s:property value="#attr.names[1]"/><br>
<%
    Map<String, String> map = new HashMap<String, String>();
    request.setAttribute("map", map);
    map.put("aa", "a");
    map.put("bb", "b");
    map.put("cc", "c");
%>
<!-- 使用OGNL访问map -->
<s:property value="#attr.map.size"/><br>
AA:<s:property value="#attr.map['aa']"/><br>
</body>
</html>