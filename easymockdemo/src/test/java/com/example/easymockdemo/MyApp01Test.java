package com.example.easymockdemo;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MyApp01Test {

	// SUT
	private MyApp myApp;
	private ICalculator mock;

	@Before
	public void setUp() {
		mock = createMock(ICalculator.class);
		myApp = new MyApp(mock);
	}

	@Test
	public void calculationCheck() {
		expect(mock.add(2.0, 3.0)).andReturn(5.0).anyTimes();
		expect(mock.divide(5.0, 2.0)).andReturn(2.5).atLeastOnce();
		replay(mock);
		assertEquals(2.5, myApp.calculateAvg(2.0, 3.0), 0.0001);
		verify(mock);
	}
}
