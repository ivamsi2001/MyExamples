package com.venkat.java8;

import java.util.Arrays;
import java.util.stream.Stream;

import com.venkat.java8.model.Person;

public class ArrayToStream {
	public static void main(String[] args) {
		Person[] personsAry = { new Person("A", 43), new Person("B", 30), new Person("C", 73) };
		//
		System.out.println("Loop Display");
		for (Person p : personsAry) {
			System.out.println(p.personInfo());
		}
		//
		System.out.println("Stream Of Display");
		Stream<Person> stream = Stream.of(personsAry);
		stream.forEach(p -> System.out.println(p.personInfo()));
		//
		System.out.println("Arrays Stream");
		stream = Arrays.stream(personsAry);
		stream.forEach(p->System.out.println(p.personInfo()));
	}
}
