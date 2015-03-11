package testsuite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import src.Psikus;
import src.PsikusImpl;

public class niekszTest {
	Psikus ps = new PsikusImpl();
	@Test
	public void nieksztaltekTest(){
		assertEquals((Integer)5, ps.nieksztaltek(5));
		assertEquals((Integer)8, ps.nieksztaltek(3));
		assertEquals((Integer)888, ps.nieksztaltek(333));
		assertEquals((Integer)111, ps.nieksztaltek(777));
		assertNotEquals((Integer)888, ps.nieksztaltek(123));
	}
}
