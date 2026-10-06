package com.fuxi.struts.valuestack.app;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Role {
	private Integer roleId;
	private String roleName;
	public Role(Integer roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}
	public Role() {
	}
}
