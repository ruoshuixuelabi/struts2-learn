package com.example.learn.struts2.legacy03;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test only — no StrutsTestCase since OSGi integration
 * needs an actual Felix/Equinox container to bootstrap.
 */
public class HelloActionTest {
    @Test
    public void testBundleNameDefault() {
        HelloAction action = new HelloAction();
        action.execute();
        assertEquals("demo-bundle-1.0.0", action.getBundleName());
    }

    @Test
    public void testBundleNameOverride() {
        HelloAction action = new HelloAction();
        action.setBundleName("my-bundle");
        action.execute();
        assertEquals("my-bundle", action.getBundleName());
    }
}