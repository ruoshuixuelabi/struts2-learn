package com.fuxi.struts.app;
import com.fuxi.struts2.model.Customer;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * @author Administrator
 */
public class ConversionAction extends ActionSupport implements ModelDriven<Customer>{
	private static final long serialVersionUID = 1L;
	@Override
	public String execute() {
		System.out.println("model="+model);
		return "success";
	}
	private Customer model;
	@Override
	public Customer getModel() {
		model=new Customer();
		return model;
	}
}
