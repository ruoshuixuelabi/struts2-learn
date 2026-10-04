<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Bean-Validation 演示表单</title>
    <style>
        .error { color: red; }
        .row { margin: 8px 0; }
    </style>
</head>
<body>
    <h1>Bean-Validation 插件演示</h1>
    <p>字段由 JSR-303 注解约束（@NotNull / @Size / @Email / @Min / @Max）；嵌套对象由 @Valid 级联验证。</p>

    <s:if test="hasFieldErrors()">
        <div class="error">
            <s:fielderror />
        </div>
    </s:if>

    <s:form action="save" namespace="/bean-validation" method="post">
        <div class="row">
            用户名（必填，长度 3-20）：<s:textfield name="username" />
        </div>
        <div class="row">
            邮箱（必填，邮箱格式）：<s:textfield name="email" />
        </div>
        <div class="row">
            年龄（0-150）：<s:textfield name="age" />
        </div>
        <hr/>
        <h3>嵌套对象（@Valid 级联验证）</h3>
        <div class="row">
            nested.username：<s:textfield name="nestedUser.username" />
        </div>
        <div class="row">
            nested.email：<s:textfield name="nestedUser.email" />
        </div>
        <s:submit value="提交" />
    </s:form>
</body>
</html>