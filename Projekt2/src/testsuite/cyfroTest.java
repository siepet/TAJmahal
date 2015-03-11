package testsuite;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import src.Psikus;
import src.PsikusImpl;

public class cyfroTest {
	Psikus ps = new PsikusImpl();
	
	@Test
	public void cyfrokradKrad() {
		assertEquals(null, ps.cyfrokrad(5));
		assertThat(ps.cyfrokrad(23), either(is(3)).or(is(2)));
		assertThat(ps.cyfrokrad(65), either(is(6)).or(is(5)));
	}

}
