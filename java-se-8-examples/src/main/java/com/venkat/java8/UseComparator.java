package com.venkat.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UseComparator {
	
	public static void main(String[] args) {
		List<String> sList = new ArrayList<String>();	
		sList.add("AAA");
		sList.add("bbb");
		sList.add("CCC");
		sList.add("ddd");
		sList.add("EEE");
		//
		Collections.sort(sList);
		System.out.println("Simple Sort");
		//
		for(String str : sList){
			System.out.println(str);
		}
		//
		/*Collections.sort(sList,new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			}
		});*/
		//
		Comparator<String> cmp = (o1,o2)->o1.compareToIgnoreCase(o2);
		Collections.sort(sList,cmp);
		//
		System.out.println("Sort With Comparator");
		for(String str : sList){
			System.out.println(str);
		}

	}
}
