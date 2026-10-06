package com.fuxi.struts.valuestack.app;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.RequestAware;
import lombok.Data;
@Data
public class Employee implements RequestAware{
	private Map<String, Object> requestMap = null;
	private Dao dao = new Dao();
	private String name;
	private String password;
	private String gender;
	private String dept;
	private List<String> roles;
	private String desc;
	/**
	 * save方法用来测试通配符映射
	 */
	public String save(){
		System.out.println("save: " + this);
		return "save";
	}
	public String input(){
		requestMap.put("depts", dao.getDepartments());
		requestMap.put("roles", dao.getRoles());
		return "input";
	}
	@Override
	public void setRequest(Map<String, Object> request) {
		this.requestMap = request;
	}
}
