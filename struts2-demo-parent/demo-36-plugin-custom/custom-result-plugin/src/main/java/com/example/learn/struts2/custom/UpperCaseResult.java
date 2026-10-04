package com.example.learn.struts2.custom;

import org.apache.struts2.result.StrutsResultSupport;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class UpperCaseResult extends StrutsResultSupport {
    @Override
    protected void doExecute(String finalLocation, jakarta.servlet.http.HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        response.setContentType("text/plain;charset=UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            String upper = finalLocation.toUpperCase();
            writer.write(upper);
        }
    }
}