package testsuite;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import src.Psikus;
import src.PsikusImpl;

import src.NieudanyPsikusException;

public class hultajTest {
	Psikus ps = new PsikusImpl();
	
	@Test(expected= NieudanyPsikusException.class)
	public void hultajchochlaExcTest() throws NieudanyPsikusException{
		ps.hultajchochla(5);
	}
	
	@Test
	public void hultajchochlaTest() throws NieudanyPsikusException{
		assertEquals((Integer)22, ps.hultajchochla(22));
	}
}
