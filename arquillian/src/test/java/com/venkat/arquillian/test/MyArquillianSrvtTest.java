package com.venkat.arquillian.test;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.venkat.arquillian.MyArquillianSrvt;

@RunWith(Arquillian.class)
public class MyArquillianSrvtTest {

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "arquillian-servlet-test.war").addClass(MyArquillianSrvt.class);
	}

	@Test
	public void alertsTest() {
		Client client = ClientBuilder.newClient();

		String responseData = client.target("http://localhost:8080/arquillian-servlet-test/MyArquillianSrvt").queryParam("user_id", "777").request(MediaType.APPLICATION_JSON).get(String.class);

		JsonReader reader = Json.createReader(new StringReader(responseData));

		JsonObject response = reader.readObject();

		assertEquals(5, response.getJsonArray("alerts").size());
	}
}