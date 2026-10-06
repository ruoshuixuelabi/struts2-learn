package com.fuxi.struts2.app.converters;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.StrutsTypeConverter;
public class DateConverter extends StrutsTypeConverter {
	private DateFormat dateFormat;
	public DateConverter() {
		//这个地方是写死的,下面改从配置文件里面获取
//		dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("DateConverter's constructor...");
	}
	public DateFormat getDateFormat(){
		//如果没有初始化的话我们初始化一下
		if(dateFormat == null){
			/*
			 * 获取当前 WEB 应用的初始化参数 pattern
			 * 其实这个和基于字段以及基于类型转换器有关系,基于类型的话加载Struts2的时候创建,
			 * 这个时候ServletContext还是空的
			 */
			ServletContext servletContext = ServletActionContext.getServletContext();
			System.out.println(servletContext); 
			String pattern = servletContext.getInitParameter("pattern");
			dateFormat = new SimpleDateFormat(pattern);
		}
		return dateFormat;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		System.out.println("convertFromString...");
		if(toClass == Date.class){
			if(values != null && values.length > 0){
				String value = values[0];
				try {
					return getDateFormat().parseObject(value);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		//若没有转换成功,则返回 values
		return values;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object o) {
		System.out.println("convertToString...");
		if(o instanceof Date){
			Date date = (Date) o;
			return getDateFormat().format(date);
		}
		//若转换失败返回 null
		return null;
	}
}
