package com.example.learn.struts2.legacy07;

/**
 * 通过 DWR 暴露给浏览器的服务类。
 * DWR 会扫描本类的 public 方法生成对应的 JavaScript 代理
 * （如 Greeter.greet(name) → 前端直接调用）。
 */
public class GreetService {

    /** 简单问候：根据 name 返回不同问候语 */
    public String greet(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "你好，匿名访客！";
        }
        return "你好，" + name + "！这是来自 Struts 2 + DWR 的远程响应。";
    }

    /** 计算两个数字之和（演示 DWR 支持基本类型） */
    public int add(int a, int b) {
        return a + b;
    }
}
