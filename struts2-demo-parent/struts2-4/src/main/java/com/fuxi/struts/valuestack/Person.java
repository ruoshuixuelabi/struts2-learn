package com.fuxi.struts.valuestack;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Person {
	private String name;
	private int age;
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public Person() {
		
	}
}
