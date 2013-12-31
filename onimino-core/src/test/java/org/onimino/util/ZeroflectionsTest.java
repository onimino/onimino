package org.onimino.util;

import org.junit.Assert;
import org.junit.Test;
import org.onimino.util.Zeroflections;

public class ZeroflectionsTest {
	@Test
	public void testGetAIs() {
		Assert.assertTrue(Zeroflections.getAIs().size() > 0);
	}
	
	@Test
	public void testGetModes() {
		Assert.assertTrue(Zeroflections.getModes().size() > 0);
	}
	
	@Test
	public void testGetRandomizers() {
		Assert.assertTrue(Zeroflections.getRandomizers().size() > 0);
	}
	
	@Test
	public void testGetWallkicks() {
		Assert.assertTrue(Zeroflections.getWallkicks().size() > 0);
	}
	
	@Test
	public void testGetRules() {
		Assert.assertTrue(Zeroflections.getRules().size() > 0);
	}
}
