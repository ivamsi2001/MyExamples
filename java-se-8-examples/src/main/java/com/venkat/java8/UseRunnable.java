package com.venkat.java8;

public class UseRunnable {
	public static void main(String[] args) {
		//Without Lambdas
		
		/*
		Runnable r1 = new Runnable() {

			@Override
			public void run() {
				System.out.println("Running Thread 1");
			}
		};

		Runnable r2 = new Runnable() {

			@Override
			public void run() {
				System.out.println("Running Thread 2");
			}
		};
		*/
		
		//With Lambda

		Runnable r1 = () -> {
			try {
				Thread.sleep(1000);
				System.out.println("Running Thread 1");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		};
		
		Runnable r2 = () -> {
			System.out.println("Running Thread 2");
		};

		new Thread(r1).start();
		new Thread(r2).start();
	}

}
