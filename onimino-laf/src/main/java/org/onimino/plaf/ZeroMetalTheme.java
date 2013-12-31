package org.onimino.plaf;

import java.awt.Color;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

public class ZeroMetalTheme extends DefaultMetalTheme {

	@Override
	public String getName() {
		return "0mino";
	}

	@Override
	public ColorUIResource getPrimary1() {
		return new ColorUIResource(Color.GRAY);
	}

	@Override
	public ColorUIResource getPrimary2() {
		return new ColorUIResource(Color.LIGHT_GRAY);
	}

	@Override
	public ColorUIResource getPrimary3() {
		return new ColorUIResource(Color.WHITE);
	}

	@Override
	public ColorUIResource getSecondary1() {
		return new ColorUIResource(48, 48, 48);
	}

	@Override
	public ColorUIResource getSecondary2() {
		return new ColorUIResource(128, 128, 128);
	}

	@Override
	public ColorUIResource getSecondary3() {
		return new ColorUIResource(64, 64, 64);
	}

	@Override
	public ColorUIResource getBlack() {
		return new ColorUIResource(255, 255, 255);
	}
	
	@Override
	public ColorUIResource getUserTextColor() {
		return new ColorUIResource(0, 0, 0);
	}
	
	@Override
	public ColorUIResource getTextHighlightColor() {
		return new ColorUIResource(128, 128, 128);
	}
	
	@Override
	public ColorUIResource getControlDarkShadow() {
		return getPrimary1();
	}
	
}
