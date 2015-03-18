package com.example.easymockdemo;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;

public class MyApp02Test {

	@Rule
	public EasyMockRule mocks = new EasyMockRule(this);

	@Mock
	private ICalculator mock;

	@TestSubject
	private MyApp myApp = new MyApp(mock);

	@Test
	public void calculationCheck() {
		expect(mock.add(2.0, 3.0)).andReturn(5.0);
		expect(mock.divide(5.0, 2.0)).andReturn(2.5);
		replay(mock);
		assertEquals(2.5, myApp.calculateAvg(2.0, 3.0), 0.0001);
		verify(mock);
	}
}
