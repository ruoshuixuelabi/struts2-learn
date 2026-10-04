package com.example.learn.struts2.legacy05;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.portlet.action.PortletAction;

/**
 * Portlet Action 的标准写法。
 *
 * PortletAction 把 Action 分为三阶段：
 *  - render（渲染）：对应 view 模式，无副作用
 *  - action（动作）：处理表单提交等状态变更
 *  - resource（资源）：提供 Ajax / 资源数据
 *
 * 配置里分别定义三个 method：默认（render） / action / resource。
 */
public class HelloPortletAction extends PortletAction {

    /** render 阶段：返回页面（默认模式） */
    public String render() {
        return ActionSupport.SUCCESS;
    }

    /** action 阶段：处理表单提交 */
    public String action() {
        return ActionSupport.SUCCESS;
    }

    /** resource 阶段：返回 Ajax 数据 */
    public String resource() {
        return ActionSupport.SUCCESS;
    }
}