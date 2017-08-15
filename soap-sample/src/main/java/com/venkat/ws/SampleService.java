package com.venkat.ws;

import javax.jws.WebService;

@WebService
public class SampleService {

    public GreetingType sayHello(String name) {
    	GreetingType gr = new GreetingType();
    	gr.setMsg(String.format("Hello, %s", name));
    	return gr;
    }
    
    public int add(int a, int b){
    	return a+b;
    }
    
    public int mul(int a, int b){
    	return a*b;
    }
    
}