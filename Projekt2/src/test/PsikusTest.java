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
		assertThat(ps.hultajchochla(32), either(is(32)).or(is(23)));
		assertThat(ps.hultajchochla(123), either(is(213)).or(is(321)).or(is(132)));
	}
	
	@Test
	public void nieksztaltekTest(){
		assertEquals((Integer)5, ps.nieksztaltek(5));
		assertEquals((Integer)8, ps.nieksztaltek(3));
		assertThat(ps.nieksztaltek(53), is(58));
		assertThat(ps.nieksztaltek(37), either(is(31)).or(is(87)));
		assertThat(ps.nieksztaltek(367), either(is(867)).or(is(397)).or(is(361)));
	}

}
