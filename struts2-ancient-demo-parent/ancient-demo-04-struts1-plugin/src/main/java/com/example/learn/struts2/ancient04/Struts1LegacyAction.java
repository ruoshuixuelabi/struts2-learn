package com.example.learn.struts2.ancient04;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 一个 Struts 1 时代的 Action（继承自 org.apache.struts.action.Action）。
 * 通过 struts1-plugin，这个 Action 能在 Struts 2 应用中被复用。
 */
public class Struts1LegacyAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setAttribute("message", "Hello from Struts 1 Action called by Struts 2!");
        return mapping.findForward("success");
    }
}