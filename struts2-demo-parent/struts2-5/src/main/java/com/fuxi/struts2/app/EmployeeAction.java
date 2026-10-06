package com.fuxi.struts2.app;
import java.util.Map;
import org.apache.struts2.interceptor.RequestAware;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
public class EmployeeAction implements RequestAware,ModelDriven<Employee>,Preparable {
	private Dao dao=new Dao(); 
	private Employee employee;
	/**
	 * 更新数据的方法,根据页面回传的数据更新
	 */
	public String update(){
		dao.update(employee);
		return "success";
	}
	/**
	 * 为更新数据准备模型的方法,此处直接new出一个
	 */
	public void prepareUpdate(){
		employee=new Employee();
	}
	/**
	 * 跳转到编辑页面的方法,编辑页面需要回显
	 */
	public String edit(){
		//1.获取传入的employeeId:	employee.getEmployeeId()
		//2.根据employeeId获取Employee对象
//		Employee emp=dao.get(employee.getEmployeeId());
		//3.把栈顶对象属性封装好:此时栈顶对象是Employee
		//目前的Employee对象只有employeeId属性,其他属性为null
		/*struts2表单回显时:从值栈栈顶开始查找匹配的属性,若找到就添加到value属性中*/
		//不能进行表单的回显,因为经过重写赋值的employee对象已经不再是栈顶对象了,再加上下面的那句话可以
		//employee=dao.get(employee.getEmployeeId());
		//手动的把数据库中获取的Employee对象放到值栈的栈顶,这样是可以的
		//但此时,值栈栈顶以及第二个对象均为Employee对象,不够完美。我们可以在getModel方法判断一下是修改还是新建
//		ActionContext.getContext().getValueStack().push(dao.get(employee.getEmployeeId()));
//		employee.setEmail(emp.getEmail());
//		employee.setFirstName(emp.getFirstName());
//		employee.setLastName(emp.getLastName());
		return "edit";
	}
	/**
	 * 由于需要显示因此需要从数据库查询出来
	 */
	public void prepareEdit(){
		employee=dao.get(employeeId);
	}
	public String save(){
		//1.获取请求参数:通过定义对应属性的方式
		//2.调用dao 的save方法
		dao.save(employee);
		//3.通过redirectAction的方式响应结果给emp-list
		return "success";
	}
	/**
	 * 为save()方法准备模型 私人订制(为prepare()方法定制的)
	 */
	public void prepareSave(){
		employee=new Employee();
	}
	public String delete(){
		dao.delete(employeeId);
		/**
		 * 返回结果的类型应为redirectAction。
		 * 也可以是chain:实际上chain是没有必要的,因为不需要在下一个Action中保留当前Action的状态。
		 * 还有,若使用chain,则到达目标页面后,地址栏显示的依然是删除的那个链接,刷新时会有重复提交。
		 */
		return "success";
	}
	public String list(){
		request.put("emps", dao.getEmployee());
		return "list";
	}
	private Map<String, Object>request;
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request=arg0;
	}
	//需要在当前的EmployeeAction中定义employeeId属性,以接受请求参数
	private Integer employeeId;
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	@Override
	public Employee getModel() {
		/**
		 * 判断是Create还是Edit
		 * 若为Create则employee=new Employee();
		 * 若为Edit则employee=dao.get(employeeId);
		 * 判断标准为是否有employeeId这个参数,如果有这个参数则为Edit,反之为Create
		 * 若通过employeeId来判断,则需要在ModelDriven拦截器之前先执行一个params拦截器
		 * 而这可以通过使用paramsPrepareParamsStack拦截器栈实现
		 * 需要在Struts 2配置文件中配置使用paramsPrepareParamsStack为默认的拦截器栈
		 */
//		if (employeeId==null) {
//			employee=new Employee();
//		}else {
//			employee=dao.get(employeeId);
//		}
		return employee;
	}
	/**
	 * prepare方法主要作用:为getModel()方法准备model的,其实这个方法可以不被调用,优先于modelDriven执行。
	 */
	@Override
	public void prepare() throws Exception {
//		if (employeeId==null) {
//			employee=new Employee();
//		}else {
//			employee=dao.get(employeeId);
//		}
		//测试是否执行了prepare()方法
		System.out.println("prepare方法");
	}
}
