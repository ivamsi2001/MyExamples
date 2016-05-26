package com.venkat.examples.annotations;

import java.lang.reflect.Method;

public class SampleAnnotationTest {

	public static void main(String[] args) {
		Book bookObj;
		Class<?> classRef;
		Method mtdRef;
		Author anntRef;
		try {
			bookObj = new Book();
			classRef = bookObj.getClass();
			//
			anntRef =	classRef.getAnnotation(Author.class);
			printAuthorInfo(anntRef);
			//
			mtdRef = classRef.getDeclaredMethod("annotatedMethod");
			anntRef = mtdRef.getAnnotation(Author.class);
			printAuthorInfo(anntRef);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void printAuthorInfo(Author author){
		System.out.println("Author Name : [" + author.authorName() + "] Book Title : ["+author.title() + "]");
	}
}
