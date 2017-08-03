package com.main;

import java.time.Instant;
import java.util.stream.Stream;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestThrottle {

	@Test
	public void testRate() {
		long begin = Instant.now().getEpochSecond();
		Throttler instance = Throttler.getInstance().setMaximunRate(1).setRate(1);
		instance.throttle(Stream.generate(() -> "test").limit(10));
		assertEquals(Instant.now().getEpochSecond() - begin, 9);
	}
}
