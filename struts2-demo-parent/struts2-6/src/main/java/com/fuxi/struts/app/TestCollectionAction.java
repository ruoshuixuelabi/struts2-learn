package com.fuxi.struts.app;
import java.util.Collection;
import com.fuxi.struts2.model.Manager;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
/**
 * @author Administrator
 */
@Getter
@Setter
public class TestCollectionAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Collection<Manager> mgrs = null;
	@Override
	public String execute() {
		System.out.println(mgrs);
		return SUCCESS;
	}
}
