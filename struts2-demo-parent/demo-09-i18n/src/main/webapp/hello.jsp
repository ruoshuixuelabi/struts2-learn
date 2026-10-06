<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>i18n Demo</title>
    <style>
        body { font-family: -apple-system, sans-serif; margin: 2em; }
        .lang-switch { float: right; }
        .lang-switch a { margin-left: 1em; }
    </style>
</head>
<body>

<div class="lang-switch">
    <s:text name="lang.switch"/>
    <s:url action="locale" var="zhUrl">
        <s:param name="request_locale">zh_CN</s:param>
    </s:url>
    <s:url action="locale" var="enUrl">
        <s:param name="request_locale">en_US</s:param>
    </s:url>
    <a href="${zhUrl}"><s:text name="lang.zh"/></a>
    <a href="${enUrl}"><s:text name="lang.en"/></a>
</div>

<h1><s:text name="welcome"/></h1>

<p><s:text name="hello.user">
    <s:param>${userName}</s:param>
</s:text></p>

<p><s:text name="nonexistent.key" /></p>

<hr/>

<h3>Action getText() 取值（Java 端）</h3>
<ul>
    <li>welcomeText = ${welcomeText}</li>
    <li>greetingText = ${greetingText}</li>
    <li>defaultText = ${defaultText}</li>
    <li>currentLocale = ${currentLocale}</li>
</ul>

<h3>使用 Resource Bundle（s:i18n）</h3>
<s:i18n name="globalMessages">
    <p>Re-read: <s:text name="welcome"/></p>
</s:i18n>

<h3>包级资源查找</h3>
<p><s:text name="package.greeting"/></p>

</body>
</html>