package com.fuxi.struts.valuestack.app;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class Department {
	private Integer deptId;
	private String deptName;
	public Department(Integer deptId, String deptName) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
	}
	public Department() {
	}
}
