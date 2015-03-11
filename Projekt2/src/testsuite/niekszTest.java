package testsuite;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import src.Psikus;
import src.PsikusImpl;

public class niekszTest {
	Psikus ps = new PsikusImpl();
	@Test
	public void nieksztaltekTest(){
		assertEquals((Integer)5, ps.nieksztaltek(5));
		assertEquals((Integer)8, ps.nieksztaltek(3));
		assertThat(ps.nieksztaltek(53), is(58));
		assertThat(ps.nieksztaltek(37), either(is(31)).or(is(87)));
		assertThat(ps.nieksztaltek(367), either(is(867)).or(is(397)).or(is(361)));
	}
}
