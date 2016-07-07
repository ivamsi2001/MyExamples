package com.venkat.arquillian;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MyArquillianSrvt", urlPatterns = "/MyArquillianSrvt")
public class MyArquillianSrvt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(MyArquillianSrvt.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		long userId = Long.parseLong(request.getParameter("user_id"));
		logger.log(Level.INFO, "Generating alerts for: {0}", userId);
		try (JsonGenerator generator = Json.createGenerator(out)) {
			generator.writeStartObject();
			generator.write("greeting", "Welcome, will sending available alerts");
			generator.writeStartArray("alerts");
			String[] alerts = { "Fraud alert!", "Outbid alert!", "Item available alert!", "Fees due alert!", "Payment failure alert!" };
			for (String alert : alerts) {
				generator.writeStartObject().write("user", userId).write("text", alert).writeEnd();
			}
			generator.writeEnd();
			generator.write("goodbye", "No more alerts for now, timing out");
			generator.writeEnd();
		}
		out.flush();
		out.close();
	}
}
