package test;
import src.NieudanyPsikusException;
import src.Psikus;
import src.PsikusImpl;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PsikusTest {
	
	Psikus ps = new PsikusImpl();
	
	@Test
	public void cyfrokradKrad() {
		assertEquals(null, ps.cyfrokrad(5));
		assertThat(ps.cyfrokrad(23), either(is(3)).or(is(2)));
		assertThat(ps.cyfrokrad(65), either(is(6)).or(is(5)));
	}
	
	@Test(expected= NieudanyPsikusException.class)
	public void hultajchochlaExcTest() throws NieudanyPsikusException{
		ps.hultajchochla(5);
	}
	
	@Test
	public void hultajchochlaTest() throws NieudanyPsikusException{
		assertEquals((Integer)22, ps.hultajchochla(22));
	}
	
	@Test
	public void nieksztaltekTest(){
		assertEquals((Integer)5, ps.nieksztaltek(5));
		assertEquals((Integer)888, ps.nieksztaltek(333));
		assertEquals((Integer)111, ps.nieksztaltek(777));
		assertNotEquals((Integer)888, ps.nieksztaltek(123));
	}

}
