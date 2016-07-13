package com.venkat.excel;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DataProvider {
	//
	private String driver;
	private String dbURL;
	private String dbUserName;
	private String dbPassword;
	private String sqlQuery;
	//
	private Connection connection;
	private ResultSet rs;
	

	public DataProvider(String resultId) throws Exception {
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream("dbqueries.xml");
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document xmlDocument = builder.parse(is);
			XPath xPath = XPathFactory.newInstance().newXPath();
			//
			String expression = "/Data/ConnectionDetails";
			Node dbNode  = (Node) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODE);
			if (dbNode!=null && dbNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) dbNode;
				driver = eElement.getElementsByTagName("Driver").item(0).getTextContent();
				dbURL = eElement.getElementsByTagName("DBURL").item(0).getTextContent();
				dbUserName = eElement.getElementsByTagName("DBUserName").item(0).getTextContent();
				dbPassword = eElement.getElementsByTagName("DBPassword").item(0).getTextContent();
			}
			//
			expression = "/Data/ResultSets/ResultSet[@id='" + resultId + "']";
			Node node  = (Node) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODE);
			if (node!=null && node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				sqlQuery = eElement.getElementsByTagName("SqlQuery").item(0).getTextContent();
			}
			System.out.println("Driver : "+driver);
			System.out.println("dbURL : "+dbURL);
			System.out.println("dbUserName : "+dbUserName);
			System.out.println("dbPassword : "+dbPassword);
			System.out.println("sqlQuery : "+sqlQuery);
			//
			
			//
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	public ResultSet getResultSet() throws Exception {
		if (rs == null) {
			Class.forName(driver);
			connection = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
			Statement st = connection.createStatement();
			rs = st.executeQuery(sqlQuery);
		}
		return rs;
	}

	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
