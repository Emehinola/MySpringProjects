package com.demo.SpringDemo;

public class Alien {
	private int age = 34;
	private Computer com;
	
	public Alien() {
		System.out.println("Alien default constructor");
	}
	
	public int getAge() {
		return age;
	};
	
	public void setAge(int age) {
		System.out.println("Set age");
		this.age = age;
	}
	
	public Computer getCom() {
		return com;
	}
	
	public void setCom(Computer com) {
		this.com = com;
	}
 	
	public void code() {
		System.out.println("I'm coding!");
		com.compile();
	}
	
}
