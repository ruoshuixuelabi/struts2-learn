package com.example.learn.struts2.demo30;

import org.apache.struts2.ActionSupport;

public class RawApiAction extends ActionSupport {
    private String payload = "{ \"status\": \"ok\" }";

    public String raw() {
        return SUCCESS;
    }

    public String getPayload() { return payload; }
}