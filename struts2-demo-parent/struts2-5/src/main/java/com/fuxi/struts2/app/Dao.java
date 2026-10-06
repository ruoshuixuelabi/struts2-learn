package com.fuxi.struts2.app;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Dao {
	//为了让id是有顺序的,我们这里使用LinkedHashMap
	private static Map<Integer, Employee> emps = new LinkedHashMap<Integer, Employee>();
	static {
		emps.put(1001, new Employee(1001, "AA", "aa", "aa@qq.com"));
		emps.put(1002, new Employee(1002, "BB", "bb", "bb@qq.com"));
		emps.put(1003, new Employee(1003, "CC", "cc", "cc@qq.com"));
		emps.put(1004, new Employee(1004, "DD", "dd", "dd@qq.com"));
		emps.put(1005, new Employee(1005, "EE", "ee", "ee@qq.com"));
	}

	/** 查询所有的Employee */
	public List<Employee> getEmployee() {
		return new ArrayList<Employee>(emps.values());
	}

	/** 根据id删除员工 */
	public void delete(Integer empId) {
		emps.remove(empId);
	}

	/** 添加Employee的方法 */
	public void save(Employee emp) {
		long time = System.currentTimeMillis();
		emp.setEmployeeId((int) time);
		emps.put(emp.getEmployeeId(), emp);
	}

	/** 根据id查询Employee */
	public Employee get(Integer empId) {
		return emps.get(empId);
	}

	/** 更新Employee的方法 */
	public void update(Employee emp) {
		emps.put(emp.getEmployeeId(), emp);
	}
}
