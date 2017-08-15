package com.venkat.ws;

import javax.jws.WebService;

@WebService(endpointInterface = "com.venkat.ws.IMySOAP",name="MyABCSOAPService")
public class MySOAPImpl implements IMySOAP {

	@Override
	public GreetingType sayHello(String msg) {
		GreetingType gr = new GreetingType();
		gr.setMsg(String.format("Hello, %s", msg));
		return gr;
	}
}
