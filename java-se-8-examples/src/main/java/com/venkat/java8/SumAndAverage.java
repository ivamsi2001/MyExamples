package com.venkat.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import com.venkat.java8.model.Person;

public class SumAndAverage {
	
	public static void main(String[] args) {
		List<String> numbers = new ArrayList<String>();
		for(int i=0;i<1000000;i++){
			numbers.add("item "+i);
		}
		
		long count = numbers.stream().parallel().count();
		
		System.out.println("Count : "+count);
		
		List<Person> personsList = new ArrayList<Person>();
		//
		personsList.add(new Person("A",43));
		personsList.add(new Person("B",30));
		personsList.add(new Person("C",73));
		//
		int sum = personsList.stream().mapToInt(p->p.getAge()).sum();
		//
		System.out.println("Sum of Ages : "+sum);
		//
		OptionalDouble avg = personsList.stream().mapToInt(p->p.getAge()).average();
		if(avg.isPresent()){
			System.out.println("Ages Avg : "+avg.getAsDouble());	
		}
		//
	}
}
