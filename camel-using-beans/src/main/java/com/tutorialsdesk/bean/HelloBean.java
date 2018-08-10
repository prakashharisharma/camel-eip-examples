package com.tutorialsdesk.bean;

public class HelloBean {
	
	public String hello(String name) {
		
		System.out.println("Inside Bean " + name);
		
		return "Hello " + name;
		}
}
