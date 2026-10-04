package com.example.learn.struts2.legacy03;

import com.opensymphony.xwork2.ActionSupport;

/**
 * OSGi demo action.
 *
 * Struts 2.5.30 提供 struts2-osgi-plugin，让 Action 可以作为 OSGi bundle 部署：
 *  - Action 通过 OSGi 服务注册中心（Service Registry）发现其他 bundle 提供的服务
 *  - 支持 Action 热部署：bundle 更新后无需重启 Web 容器
 *  - Struts 框架本身也作为 OSGi bundle 运行
 *
 * 6.0 弃用 + 7.x 移除（S2-048 漏洞 + 维护成本）。
 *
 * 注意：本 demo 是「配置示例」，完整跑通需要：
 *  1. Apache Felix / Equinox OSGi 容器
 *  2. Spring OSGi / Gemini Blueprint（用于 OSGi 与 Spring 集成）
 *  3. 通过 felix:run 或类似插件启动
 */
public class HelloAction extends ActionSupport {

    private String bundleName;

    public String execute() {
        if (bundleName == null) {
            bundleName = "demo-bundle-1.0.0";
        }
        return SUCCESS;
    }

    public String getBundleName() { return bundleName; }
    public void setBundleName(String bundleName) { this.bundleName = bundleName; }
}