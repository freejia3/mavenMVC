package test.java;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;
import main.java.ForMain;

public class ForMainTest extends TestCase{

	@Test
	public void testCal() {
		//fail("Not yet implemented");
		ForMain fm = new ForMain();

		fm.cal();
	}

	@Test
	public void testCal2() {
		//fail("Not yet implemented");
		ForMain fm = new ForMain();

		int res = fm.cal2();

		//100==res 라면
		Assert.assertEquals(100, res);

	}


}
