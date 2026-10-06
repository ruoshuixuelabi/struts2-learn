package com.fuxi.struts2.hellowprld;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("*.action")
public class FilterDispatcher implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 父接口没有getServletPath()方法,因此需要转换成子接口
        HttpServletRequest rq = (HttpServletRequest) request;
        // 1.获取 ServletPath
        String servletPath = rq.getServletPath();
        System.out.println(servletPath);
        // 定义一个变量,保存转发的地址
        String path = null;
        // 2.判断servletPath,若其等于/product-input.action则转发到WEB-INF/pages/input.jsp
        if ("/product-input.action".equals(servletPath)) {
            path = "WEB-INF/pages/input.jsp";
        }
        // 3.若其等于/product-save.action则
        if ("/product-save.action".equals(servletPath)) {
            //当form表当中的请求为post请求的时候,解决一下乱码问题
            request.setCharacterEncoding("UTF-8");
            // .1)获取请求参数
            String productName = request.getParameter("productName");
            String productDesc = request.getParameter("productDesc");
            String productPrice = request.getParameter("productPrice");
            // .2)把请求信息封装为一个Product对象
//            Product product = new Product(null, productName, productDesc, Double.parseDouble(productPrice));
            // .3)执行保存操作
//            System.out.println("保存成功" + product);
//            product.setProductId(1011);
            // .4)把product对象保存到request中。本来我们可以这样显示${param.productName},但是我们现在想要${requestScope.product.productName}
            //因为id并不在转发的里面,其实$(param.user)相当于<%=request.getParameter("user")%>
//            request.setAttribute("product", product);
            path = "WEB-INF/pages/details.jsp";
        }
        //如果转发的地址不是空就开始执行转发
        if (path != null) {
            request.getRequestDispatcher(path).forward(request, response);
            // 方法结束,我们转发之后就是有了响应了,不结束会有异常,因为后面还有chain.doFilter(request, response);
            return;
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public FilterDispatcher() {
    }

    public void destroy() {
    }
}