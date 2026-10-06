package com.fuxi.struts.app;
import com.fuxi.struts2.model.Department;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * @author Administrator
 */
public class TestComplextPropertyAction  extends ActionSupport implements ModelDriven<Department>{
	private static final long serialVersionUID = 1L;
	@Override
	public String execute() {
		System.out.println(department);
		return SUCCESS;
	}
    private Department department;
	@Override
	public Department getModel() {
		department=new Department();
		return department;
	}
}
