package com.fuxi.struts2.model;
import lombok.Data;
/**
 * 1. Department 是模型,实际录入的 Department. deptName 
 * 可以直接写到 s:textfield 的 name 属性中.那 mgr 属性如何处理呢 ?
 * Struts 2 表单标签的 name 值可以被赋为属性的属性:name=mgr.name, name=mgr.birth
 * 2. mgr中有一个 Date 类型的 birth 属性,Struts 2可以完成自动的类型转换吗?
 * 全局的类型转换器可以正常工作!
 * @author Administrator
 */
@Data
public class Department {
	private Integer id;
	private String deptName;
	private Manager mgr;
}
