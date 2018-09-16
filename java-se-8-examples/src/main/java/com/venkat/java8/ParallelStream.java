package com.venkat.java8;

import java.util.ArrayList;
import java.util.List;

public class ParallelStream {
	public static void main(String[] args) {
		List<String> numbers = new ArrayList<String>();
		for(int i=0;i<100;i++){
			numbers.add("item "+i);
		}
		//
		numbers.stream().forEach(p->System.out.println(p));
		//
		numbers.parallelStream().forEach(p->System.out.println(p));
	}
}
