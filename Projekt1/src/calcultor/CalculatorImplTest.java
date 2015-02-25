package calcultor;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorImplTest {
	
	Calculator calc = new CalculatorImpl();

	@Test
	public void addTest(){
		assertEquals(5, calc.add(2,3));
	}
	
	@Test 
	public void addTestDubl(){
		assertEquals(5.0, calc.add(2.0, 3.0), 0.1);
	}
	
	@Test
	public void subTest(){
		assertEquals(5, calc.sub(6,1));
	}
	
	@Test
	public void subTestDubl(){
		assertEquals(5.0, calc.mul(6.0, 1.0), 1.0);
	}
	
	@Test
	public void mulTest(){
		assertEquals(5, calc.mul(5,1));
	}
	
	@Test
	public void mulTestDubl(){
		assertEquals(5.0, calc.mul(5.0, 1.0), 1.0);
	}
	
	@Test
	public void divTest(){
		assertEquals(5, calc.div(10,2));
	}
	
	@Test
	public void divTestDubl(){
		assertEquals(5.0, calc.div(10.0, 2.0), 1.0);
	}
	

	@Test(expected = ArithmeticException.class) 
	public void divExc() throws ArithmeticException{
			calc.div(4,0);		
	}
	
	@Test
	public void greaterTest(){
		assertEquals(true, calc.greater(5,4));
	}
	
	@Test
	public void greaterTestDubl(){
		assertEquals(true, calc.greater(5.0,4.0));
	}
}
