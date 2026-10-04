package com.example.learn.struts2.demo08;

/** 表单对应的 POJO：name / password / age / gender / vip / city / bio */
public class User {
    private String name;
    private String password;
    private int age;
    private String gender;
    private boolean vip;
    private String city;
    private String bio;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public boolean isVip() { return vip; }
    public void setVip(boolean vip) { this.vip = vip; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
}
