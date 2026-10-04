package com.example.learn.struts2.legacy04;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserServiceImplTest {

    @Test
    public void testGreet() {
        UserService svc = new UserServiceImpl();
        String msg = svc.greet("Plexus");
        assertEquals("Hello, Plexus (from Plexus-managed UserServiceImpl)", msg);
    }

    @Test
    public void testActionWithService() {
        HelloAction action = new HelloAction();
        action.setUserService(new UserServiceImpl());
        action.setName("Struts");
        String msg = action.getMessage();
        assertEquals("Hello, Struts (from Plexus-managed UserServiceImpl)", msg);
    }
}