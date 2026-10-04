package com.example.learn.struts2.demo28;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.ConstraintsViolatedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ActionInvocation;
import org.apache.struts2.action.Action;
import org.apache.struts2.interceptor.Interceptor;
import org.apache.struts2.interceptor.ValidationAware;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * OVal 校验拦截器（Struts 7.x 适配版）。
 *
 * <p>struts2-oval-plugin:6.10.0 内置的 {@code OValValidationInterceptor} 继承自
 * {@code com.opensymphony.xwork2.interceptor.MethodFilterInterceptor}，
 * 而该父类已在 Struts 7.x（xwork2 合并入 struts2-core 后）移除。本类直接实现
 * {@link Interceptor} 并内联 method-filter 逻辑，绕开 MethodFilterInterceptor 依赖。</p>
 *
 * <p>支持的拦截器参数：</p>
 * <ul>
 *   <li>{@code excludeMethods}：不触发校验的方法名列表（逗号分隔）</li>
 *   <li>{@code includeMethods}：仅触发校验的方法名列表（逗号分隔），未设置时视为全量</li>
 * </ul>
 *
 * <p>校验失败时，把每个 ConstraintViolation 的 message 通过
 * {@code addFieldError(fieldName, message)} 注入 Action（如 Action 实现了 ValidationAware），
 * 然后返回 {@code INPUT}，让 workflow 拦截器把请求 forward 到 input 视图。</p>
 */
public class OValValidationInterceptor implements Interceptor {

    private static final Logger LOG = LogManager.getLogger(OValValidationInterceptor.class);

    private String excludeMethods;
    private String includeMethods;

    private Set<String> excludeMethodsSet = Collections.emptySet();
    private Set<String> includeMethodsSet = Collections.emptySet();

    @Override
    public void init() {
        excludeMethodsSet = splitCsv(excludeMethods);
        includeMethodsSet = splitCsv(includeMethods);
        LOG.debug("OValValidationInterceptor initialized: excludeMethods={}, includeMethods={}",
                excludeMethodsSet, includeMethodsSet);
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Object action = invocation.getAction();
        String method = invocation.getProxy().getMethod();
        if (!shouldValidate(method)) {
            return invocation.invoke();
        }

        Validator validator = new Validator();
        Collection<ConstraintViolation> violations;
        try {
            violations = validator.validate(action);
        } catch (ConstraintsViolatedException ex) {
            violations = Arrays.asList(ex.getConstraintViolations());
        }

        if (violations != null && !violations.isEmpty()) {
            if (action instanceof ValidationAware) {
                ValidationAware va = (ValidationAware) action;
                for (ConstraintViolation v : violations) {
                    String field = extractFieldName(v.getContext(), action);
                    String message = v.getMessage();
                    va.addFieldError(field, message);
                    va.addActionError(message);
                }
            } else {
                LOG.warn("Action {} does not implement ValidationAware; OVal violations cannot be reported.",
                        action.getClass().getName());
            }
            return Action.INPUT;
        }
        return invocation.invoke();
    }

    @Override
    public void destroy() {
    }

    public void setExcludeMethods(String excludeMethods) {
        this.excludeMethods = excludeMethods;
    }

    public void setIncludeMethods(String includeMethods) {
        this.includeMethods = includeMethods;
    }

    private boolean shouldValidate(String method) {
        if (method == null) {
            method = "execute";
        }
        if (excludeMethodsSet.contains(method)) {
            return false;
        }
        return includeMethodsSet.isEmpty() || includeMethodsSet.contains(method);
    }

    private static Set<String> splitCsv(String csv) {
        if (csv == null || csv.trim().isEmpty()) {
            return Collections.emptySet();
        }
        Set<String> result = new LinkedHashSet<>();
        for (String s : csv.split(",")) {
            String trimmed = s.trim();
            if (!trimmed.isEmpty()) {
                result.add(trimmed);
            }
        }
        return result;
    }

    private static String extractFieldName(OValContext ctx, Object action) {
        if (ctx instanceof FieldContext) {
            return ((FieldContext) ctx).getField().getName();
        }
        return action.getClass().getName();
    }
}