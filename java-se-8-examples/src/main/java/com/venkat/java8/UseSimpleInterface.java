package com.venkat.java8;

import com.venkat.java8.interfaces.SimpleInterface;

public class UseSimpleInterface {
	
	public static void main(String[] args) {
		SimpleInterface obj = () -> {
			System.out.println("Lord Venkateswara");
			System.out.println("Test Message");
		};
		
		obj.doSomething();
	}
}
