package com.venkat.annotations.test;

import java.lang.reflect.Method;

import org.junit.Test;

import com.venkat.annotations.pojos.Book;
import com.venkat.annotations.types.Author;

public class SampleAnnotationTest {

	@Test
	public void myTest() {
		Book bookObj;
		Class<?> classRef;
		Method mtdRef;
		Author anntRef;
		try {
			bookObj = new Book();
			classRef = bookObj.getClass();
			//
			anntRef = classRef.getAnnotation(Author.class);
			printAuthorInfo(anntRef);
			//
			mtdRef = classRef.getDeclaredMethod("annotatedMethod");
			anntRef = mtdRef.getAnnotation(Author.class);
			printAuthorInfo(anntRef);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

	}

	private void printAuthorInfo(Author author) {
		System.out.println("Author Name : [" + author.authorName() + "] Book Title : [" + author.title() + "]");
	}
}
