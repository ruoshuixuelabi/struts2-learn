package com.fuxi.struts2.helloworld;

import org.apache.struts2.interceptor.parameter.StrutsParameter;

public class Product {
    private Integer productId;
    private String productName;
    private String productDesc;
    private Double productPrice;   // 顺手把 double 改成 Double，表单留空不炸

    public String save() {
        return "details";
    }

    @StrutsParameter
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @StrutsParameter
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @StrutsParameter
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    @StrutsParameter
    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    // getter 不用加注解——日志里能看到渲染阶段的读取是默认放行的
    public Integer getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getProductDesc() { return productDesc; }
    public Double getProductPrice() { return productPrice; }
}