package testsuite;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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
		assertThat(ps.hultajchochla(32), either(is(32)).or(is(23)));
		assertThat(ps.hultajchochla(123), either(is(213)).or(is(321)).or(is(132)));
	}
}
