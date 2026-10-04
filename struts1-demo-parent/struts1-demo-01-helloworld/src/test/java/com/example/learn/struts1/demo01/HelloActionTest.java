package com.example.learn.struts1.demo01;

import org.junit.Test;
import static org.junit.Assert.*;

public class HelloActionTest {
    @Test
    public void testFormBean() {
        HelloForm form = new HelloForm();
        form.setName("Struts 1");
        assertEquals("Struts 1", form.getName());
    }
}
