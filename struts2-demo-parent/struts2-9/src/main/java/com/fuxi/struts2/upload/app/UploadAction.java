package com.fuxi.struts2.upload.app;
import java.io.File;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UploadAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private List<File> ppt;
	private List<String> pptContentType;
	private List<String> pptFileName;
	private List<String> pptDesc;
	@Override
	public String execute() throws Exception {
		System.out.println(ppt);
		System.out.println(pptContentType);
		System.out.println(pptFileName);
		System.out.println(pptDesc);
//		ServletContext servletContext = ServletActionContext.getServletContext();
//		String dir = servletContext.getRealPath("/files/" + pptFileName);
//		System.out.println(dir);
//		FileOutputStream out = new FileOutputStream(dir);
//		FileInputStream in = new FileInputStream(ppt);
//		byte [] buffer = new byte[1024];
//		int len = 0;
//		while((len = in.read(buffer)) != -1){
//			out.write(buffer, 0, len);
//		}
//		out.close();
//		in.close();
		return "input";
	}
}
