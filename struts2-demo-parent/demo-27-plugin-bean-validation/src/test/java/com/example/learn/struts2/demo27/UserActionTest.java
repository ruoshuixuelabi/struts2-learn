package com.example.learn.struts2.demo27;

import jakarta.servlet.ServletException;
import org.apache.struts2.StrutsJUnit5Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;

public class UserActionTest extends StrutsJUnit5Test {
    @Override
    protected String getConfigPath() { return "struts.xml"; }

    /**
     * 父类 executeAction 直接把整 URI 设到 request.setRequestURI，
     * query string 会让 getActionMapping 返回 null。这里手动剥掉 ? 之后的部分。
     */
    @Override
    protected String executeAction(String uri) throws ServletException, UnsupportedEncodingException {
        int q = uri.indexOf('?');
        if (q >= 0) uri = uri.substring(0, q);
        return super.executeAction(uri);
    }

    @Test
    public void testValidInput() throws Exception {
        request.setParameter("username", "alice");
        request.setParameter("email", "alice@example.com");
        request.setParameter("age", "20");
        request.setParameter("nestedUser.username", "bob");
        request.setParameter("nestedUser.email", "bob@example.com");
        String result = executeAction("/bean-validation/save.action");
        assertEquals("success", result);
    }

    @Test
    public void testMissingUsernameShouldReturnInput() throws Exception {
        request.setParameter("email", "a@b.com");
        String result = executeAction("/bean-validation/save.action");
        assertEquals("input", result);
    }
}