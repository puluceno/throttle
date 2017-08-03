package com.main;

import java.util.stream.Stream;

import com.google.common.util.concurrent.RateLimiter;

public class Throttler {

	private static Throttler instance;
	private volatile int rate;
	private volatile int maximunRate;

	private Throttler() {
	}

	public <T> void throttle(Stream<T> stream) {
		RateLimiter rateLimiter = RateLimiter.create(this.getMaximunRate());
		stream.forEach(a -> {
			rateLimiter.acquire(this.getRate());
			System.out.println(a);
		});
	}

	public int getRate() {
		return rate;
	}

	public synchronized void setRate(int rate) {
		this.rate = rate;
	}

	public static synchronized Throttler getInstance() {
		if (instance == null)
			instance = new Throttler();
		return instance;
	}

	public float getMaximunRate() {
		return maximunRate;
	}

	public synchronized void setMaximunRate(int maximunRate) {
		this.maximunRate = maximunRate;
	}

}
