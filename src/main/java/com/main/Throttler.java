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

	public static synchronized Throttler getInstance() {
		if (instance == null)
			instance = new Throttler();
		return instance;
	}
	public int getRate() {
		return rate;
	}

	public synchronized Throttler setRate(int rate) {
		this.rate = rate;
		return this;
	}


	public float getMaximunRate() {
		return maximunRate;
	}

	public synchronized Throttler setMaximunRate(int maximunRate) {
		this.maximunRate = maximunRate;
		return this;
	}

}
