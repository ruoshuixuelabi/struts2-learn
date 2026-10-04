package com.example.learn.struts2.ancient06;

import com.opensymphony.xwork2.Action;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Java8DemoAction implements Action {
    private String lambdaResult;
    private String streamResult;

    public String execute() {
        // 演示 lambda
        Runnable runner = () -> System.out.println("Lambda 跑起来了");
        runner.run();

        // 演示 stream
        List<String> items = Arrays.asList("a", "b", "c");
        streamResult = items.stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining(","));

        lambdaResult = "Lambda + Stream 已执行";
        return SUCCESS;
    }

    public String getLambdaResult() { return lambdaResult; }
    public String getStreamResult() { return streamResult; }
}