package com.example.easymockdemo;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

public class DynamicProxyAppTest {

	@Test
	public void checkSending() {

		InvocationHandler ih = new ICalculatorHandler();
		ICalculator calcMock = (ICalculator) Proxy.newProxyInstance(
				ICalculator.class.getClassLoader(),
				new Class[] { ICalculator.class }, ih);

		MyApp myApp = new MyApp(calcMock);

		assertEquals(2.5, myApp.calculateAvg(2, 3), 0.000001);
	}
	
	class ICalculatorHandler implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			if ("divide".equals(method.getName())) {
				return 2.5;
			}
			if ("add".equals(method.getName())) {
				return 5.0;
			}
			return 0.0;
		}
	}
}
