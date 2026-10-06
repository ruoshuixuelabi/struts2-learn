package com.fuxi.struts2.action;
/**
 * 测试动态方法调用的Action
 */
public class TestDynamicMethodInvocation {
	public String save(){
		System.out.println("save");
		return "success";
	}
	public String update(){
		System.out.println("update");
		return "success";
	}
}
