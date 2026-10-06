<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--  
	问题1: 如何覆盖默认的错误消息?
			1).在对应的Action类所在的包中新建ActionClassName.properties文件,
			ActionClassName即为包含着输入字段的Action类的类名
			2).在属性文件中添加如下键值对:invalid.fieldvalue.fieldName=xxx
	问题2:如果是simple主题,还会自动显示错误消息吗?如果不会显示,怎么办?
		1).通过debug标签,可知若转换出错,则在值栈的Action(实现了ValidationAware接口)对象中有一个fieldErrors属性。
		该属性的类型为Map<String,List<String>>键:字段(属性名),值:错误消息组成的List。
		所以可以使用EL或OGNL的方式来显示错误消息:${fieldErrors.age[0]}
		2).还可以使用s:fielderror标签来显示。可以通过 fieldName 属性显示指定字段的错误。
	问题3:若是simple主题,且使用<s:fielderror fieldName="age"></s:fielderror>来显示错误消息,则该消息在一个 
		ul, li, span 中,如何去除 ul, li, span 呢?
		在template.simple下面的fielderror.ftl定义了simple主题下,s:fielderror 标签显示错误消息的样式。所以修改该
		配置文件即可。在src下新建template.simple包,新建fielderror.ftl文件,把原生的fielderror.ftl中的内容
		复制到新建的fielderror.ftl 中,然后剔除 ul, li, span 部分即可。
	问题4. 如何自定义类型转换器?
			1). 为什么需要自定义的类型转换器?因为Struts不能自动完成字符串到引用类型的转换。
			2). 如何定义类型转换器:
				I.  开发类型转换器的类:扩展StrutsTypeConverter类。
				II. 配置类型转换器: 
				有两种方式
				①. 基于字段的配置:
					> 在字段所在的Model(可能是Action,可能是一个JavaBean)的包下,新建一个ModelClassName-conversion.properties文件
					> 在该文件中输入键值对:fieldName=类型转换器的全类名。
					> 第一次使用该转换器时创建实例。
					> 类型转换器是单实例的!
				②. 基于类型的配置:
					> 在src下新建xwork-conversion.properties
					> 键入:待转换的类型=类型转换器的全类名。
					> 在当前 Struts2 应用被加载时创建实例(启动的时候就创建好了)。
					> 类型转换器不是单实例的!
-->
	<s:debug></s:debug>
	<s:form action="testConversion" theme="simple">
		Age: <s:textfield name="age" label="Age"></s:textfield>
		${fieldErrors.age[0]}
		^<s:fielderror fieldName="age"></s:fielderror>
		<br><br>
		Birth: <s:textfield name="birth"></s:textfield>
		<s:fielderror fieldName="birth"></s:fielderror>
		<br><br>
		<s:submit></s:submit>
	</s:form>
</body>
</html>