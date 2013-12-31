package org.onimino.util;

import org.junit.Assert;
import org.junit.Test;
import org.onimino.util.Localization;

import static org.onimino.util.Localization.*;

public class LocalizationTest {
	private static Localization lz = new Localization();
	
	@Test
	public void testCallerClass() {
		Assert.assertEquals(LocalizationTest.class, lz.getBase());
		Assert.assertEquals(LocalizationTest.class, lz().getBase());
	}
	
	public void testLocalized() {
		Assert.assertEquals("«unlocalized»", lz.s("unlocalized"));
		Assert.assertEquals("«unlocalized»", lz("unlocalized"));
		Assert.assertEquals("howdy", lz.s("localized"));
		Assert.assertEquals("howdy", lz("localized"));
	}
}
