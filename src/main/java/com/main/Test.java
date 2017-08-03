package com.main;

import java.util.Date;
import java.util.stream.Stream;

import com.google.common.util.concurrent.RateLimiter;

public class Test {

	public static void main(String[] args) {
		
		Stream<String> stream = Stream.generate(() -> "test").limit(10);
		
		Throttler t = Throttler.getInstance();
		t.setMaximunRate(1);
		t.setRate(1);
		t.throttle(stream);
		Throttler a = Throttler.getInstance();
		
		RateLimiter rateLimiter = RateLimiter.create(1);
		long startTime = new Date().getTime();
		rateLimiter.acquire(2);
		extracted1();
		System.out.println(new Date().getTime() - startTime);
		rateLimiter.acquire(1);
		extracted2();
		System.out.println(new Date().getTime() - startTime);
	}

	private static void extracted1() {
		System.out.println("A");
	}

	private static void extracted2() {
		System.out.println("B");
	}
	
	
	
}
