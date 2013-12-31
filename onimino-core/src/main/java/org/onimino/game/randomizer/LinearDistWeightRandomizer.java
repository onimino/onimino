package org.onimino.game.randomizer;

public class LinearDistWeightRandomizer extends DistanceWeightRandomizer {

	public LinearDistWeightRandomizer() {
		super();
	}

	public LinearDistWeightRandomizer(boolean[] pieceEnable, long seed) {
		super(pieceEnable, seed);
	}

	public int getWeight(int i) {
		return weights[i];
	}

	public boolean isAtDistanceLimit(int i) {
		return false;
	}

}
