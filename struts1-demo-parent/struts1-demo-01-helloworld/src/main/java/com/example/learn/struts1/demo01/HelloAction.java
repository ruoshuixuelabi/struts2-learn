package com.example.learn.struts1.demo01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Struts 1 的 Action 类。
 * 对比 Struts 2：Struts 2 的 Action 是 POJO，不需要继承（虽然可以继承 ActionSupport）。
 */
public class HelloAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HelloForm helloForm = (HelloForm) form;
        String greeting = "Hello, " + helloForm.getName() + "! (from Struts 1.3.10)";
        request.setAttribute("greeting", greeting);
        return mapping.findForward("success");
    }
}
