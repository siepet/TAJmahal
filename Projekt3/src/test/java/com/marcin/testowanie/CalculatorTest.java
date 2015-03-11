package com.marcin.testowanie;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {
	
	@Test
	public void checkAdd(){
		new Calculator();
		assertEquals(4, Calculator.add(2, 2));
	}

}
