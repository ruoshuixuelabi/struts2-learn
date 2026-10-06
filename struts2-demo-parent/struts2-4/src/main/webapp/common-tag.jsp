<%@page import="com.fuxi.struts.valuestack.PersonComparator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.fuxi.struts.valuestack.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:debug></s:debug>
	s:property :打印值栈当中的属性值的:对于对象栈是打印值栈中对象的属性值
	<br>
	<s:property value="productName" />
	<br>
	<br> 对于Map栈,打印request,session,application的某个属性值或者某个请求参数的值
	<br>
	<s:property value="#session.date" />
	<br>
	<s:property value="#parameters.name[0]" />
	<br> s:url:创建一个url字符串
	<br>
	<s:url value="/getProduct" var="url">
		<!-- 指定url包含的请求参数,2001不可能是一个属性名,struts2把2001直接作为属性值 -->
		<s:param name="productId" value="2001"></s:param>
	</s:url>
	${url}
	<br>
	<s:url value="/getProduct" var="url2">
		<!-- 对于value值会自动的进行OGNL解析 -->
		<s:param name="productId" value="productId"></s:param>
		<s:param name="date" value="#session.date"></s:param>
	</s:url>
	${url2}
	<br>
	<s:url value="/getProduct" var="url3">
		<!-- 对于value值会自动的进行OGNL解析,若不希望进行OGNL解析,可以使用单引号引起来! -->
		<s:param name="productId" value="'abcedf'"></s:param>
	</s:url>
	${url3}
	<br>
	<!-- 构建一个请求action的地址 -->
	<s:url action="testUrlAction" namespace="/fuxi" method="save"
		var="url4"></s:url>
	${url4}
	<br>
	<!--  
		includeParams该属性有三个值:none,get,all,默认值为get。
		当该属性值为get时,该url会将访问其所在jsp的请求的所有get方法的参数添加到自身来当它的属性值
		为all时更是将get和post的的参数值全部添加到自身来一般我们并不需要额外的参数,所以定义为none 
	-->
	<s:url action="testUrl" var="url5" includeParams="get"></s:url>
	${url5}
	<br>
	<br>s:set:向page,session,request,application域对象中加入一个属性值
	<!-- 对value属性值自动的进行OGNL解析 -->
	<s:set name="productName" value="productName" scope="request"></s:set>
	<br>productName:${requestScope.productName}
	<br>
	<br> s:push:把一个对象在标签开始后压入到值栈中,标签结束时弹出值栈
	<%
		Person person = new Person();
		person.setName("张三");
		person.setAge(10);
		request.setAttribute("person", person);
	%>
	<br>
	<!-- 注意这里必须在标签里面打印,不然就没了 -->
	<s:push value="#request.person">
  		${name}
  	</s:push>
	<br>
	<br> s:if, s:else 和 s:elseif 标签
	<!-- 可以直接使用值栈的属性进行判断 -->
	<br>
	<s:if test="productPrice>1000">
 		 i7处理器
  	</s:if>
	<s:elseif test="productPrice>800">
  		I5处理器
  	</s:elseif>
	<s:else>
  		I3处理器
  	</s:else>
	<br>
	<s:if test="#request.person.age>10">
  		大于10岁
  	</s:if>
	<s:else>
  		小于等于十岁
  	</s:else>
	<br>
	<br> s:iterator 遍历集合的
	<br>
	<%
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("AA", 10));
		persons.add(new Person("BB", 20));
		persons.add(new Person("CC", 30));
		persons.add(new Person("DD", 40));
		request.setAttribute("persons", persons);
	%>
	<!-- index是遍历元素的下标,count是数量,它等于index+1-->
	<s:iterator value="#request.persons" status="status">
			index=${status.index}---count=${status.count }---name=${name}---age=${age}<br>
	</s:iterator>
	<br> s:sort可以对集合中的元素进行排序
	<br>
	<%
		PersonComparator pc = new PersonComparator();
		request.setAttribute("pc", pc);//放入request域对象
	%>
	<!-- 
	 		comparator:在排序过程中使用的比较器
	 		source:将要进行排序的可遍历对象
	 		var:用来引用因排序而新生成的可遍历对象的变量
	  -->
	<s:sort comparator="#request.pc" source="#request.persons"
		var="persons2"></s:sort>
	<s:iterator value="#attr.persons2">
	  		${name}
	</s:iterator>
	<br><br> s:date可以对Date对象进行排版
	<br>
	<s:date name="#session.date" format="yyyy-MM-dd" var="date2" />
	date2:${date2 }
	<br>
	<s:iterator value="#request.persons">
		<!--可以使用%{}把属性包装起来,强制的进行OGNL解析-->
		<s:a href="getPerson.action?name=%{name}">${name}</s:a>
	</s:iterator>
</body>
</html>