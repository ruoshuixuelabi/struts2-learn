<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>OVal 演示表单</title>
    <style>
        .error { color: red; }
        .row { margin: 8px 0; }
    </style>
</head>
<body>
    <h1>OVal 插件演示（已弃用，新项目请用 Bean-Validation）</h1>
    <p>字段由 OVal 注解约束（@NotNull / @Length / @Email / @MatchPattern）。</p>

    <s:if test="hasFieldErrors()">
        <div class="error">
            <s:fielderror />
        </div>
    </s:if>

    <s:form action="save" namespace="/oval" method="post">
        <div class="row">
            用户名（必填，长度 3-20）：<s:textfield name="username" />
        </div>
        <div class="row">
            邮箱（必填，邮箱格式）：<s:textfield name="email" />
        </div>
        <hr/>
        <h3>嵌套对象（OVal 自动级联验证）</h3>
        <div class="row">
            nested.username：<s:textfield name="nestedUser.username" />
        </div>
        <div class="row">
            nested.email：<s:textfield name="nestedUser.email" />
        </div>
        <div class="row">
            nested.phone（手机号正则）：<s:textfield name="nestedUser.phone" />
        </div>
        <s:submit value="提交" />
    </s:form>
</body>
</html>