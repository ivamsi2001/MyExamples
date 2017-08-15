package com.venkat.ws.service2;

import java.util.GregorianCalendar;

import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import com.venkat.ws.service2.BookServicePortType;
import com.venkat.ws.service2.BookServiceRequestType;
import com.venkat.ws.service2.BookServiceResponseType;
import com.venkat.ws.service2.BookType;

@WebService(serviceName = "BookService", portName = "BookServicePort", endpointInterface = "com.venkat.ws.service2.BookServicePortType",
			targetNamespace = "http://www.venkat.com/soap-example-2/1.0", wsdlLocation = "WEB-INF/wsdl/bookService.wsdl")
public class BookServicePortImpl implements BookServicePortType {

	@Override
	public BookServiceResponseType fetchBooks(final BookServiceRequestType bookServiceRequest) {
		final BookServiceResponseType response = new BookServiceResponseType();
		for (int i = 0; i < bookServiceRequest.getLimit(); i++) {
			final BookType book = new BookType();
			book.setAuthor("Elvis " + i);
			try {
				book.setPublished(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2011, 8, 14)));
			} catch (DatatypeConfigurationException e) {
				//
			}
			book.setTitle("Programming Java Edition #" + i);
			response.getBook().add(book);
		}
		return response;
	}

}
