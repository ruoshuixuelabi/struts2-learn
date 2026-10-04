package com.example.learn.struts2.legacy05;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Plain unit test only — Portlet actions need a Portal container
 * (Liferay/Pluto/WebSphere Portal) to bootstrap.
 */
public class HelloActionTest {

    @Test
    public void testGreeting() {
        HelloAction action = new HelloAction();
        action.setName("Portlet");
        assertEquals("Hello from Portlet, Portlet", action.getGreeting());
    }

    @Test
    public void testDefaultGreeting() {
        HelloAction action = new HelloAction();
        assertEquals("Hello from Portlet, World", action.getGreeting());
    }
}