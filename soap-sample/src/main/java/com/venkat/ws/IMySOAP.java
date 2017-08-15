package com.venkat.ws;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name="MySOAPService")
@SOAPBinding(style=SOAPBinding.Style.RPC)
public interface IMySOAP {
	
	public GreetingType sayHello(String msg);
}
