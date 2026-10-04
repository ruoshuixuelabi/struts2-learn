package com.example.learn.struts2.legacy04;

/**
 * Plexus 组件实现。
 * Plexus 通过 @Component 注解（plexus-component-annotations）
 * 或 META-INF/plexus/components.xml 扫描注册。
 */
public class UserServiceImpl implements UserService {
    @Override
    public String greet(String name) {
        return "Hello, " + name + " (from Plexus-managed UserServiceImpl)";
    }
}