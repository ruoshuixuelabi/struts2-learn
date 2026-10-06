package com.fuxi.struts.valuestack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.struts2.interceptor.parameter.StrutsParameter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private Integer cityId;
    private String cityName;

    @StrutsParameter
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @StrutsParameter
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}