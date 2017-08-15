package com.venkat.jaxbxsdexample.parsers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class MyJAXBParser {

	@SuppressWarnings("unchecked")
	public static <T> T unMarshaller(File file, Class<T> clazz) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (T) jaxbUnmarshaller.unmarshal(file);
	}

	public static void marshaller(Class<?> claz, Object obj, OutputStream stream) throws Exception {
		JAXBContext contextObj = JAXBContext.newInstance(claz);
		Marshaller marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshallerObj.marshal(obj, stream);
	}

	public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(xsdPath));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(xmlPath)));
		} catch (IOException | SAXException e) {
			System.out.println("Exception: " + e.getMessage());
			return false;
		}
		return true;
	}

}
