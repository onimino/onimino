package org.onimino.game.evil;

import org.eviline.Shape;
import org.eviline.randomizer.Randomizer;
import org.eviline.randomizer.RandomizerFactory;
import org.eviline.randomizer.RandomizerPresets;
import org.onimino.game.play.GameEngine;

public class TNConcurrentAggressiveRandomizer extends TNRandomizer {
	@Override
	public void setEngine(GameEngine engine) {
		super.setEngine(engine);
//		MaliciousRandomizerProperties mp = new MaliciousRandomizerProperties(3, .05, true, 30);
//		ThreadedMaliciousRandomizer t = new ThreadedMaliciousRandomizer(mp);
		Randomizer r = new RandomizerFactory().newRandomizer(RandomizerPresets.AGGRESSIVE);
		// FIXME: Be able to set the random
//		t.setRandom(engine.random);
		field.setProvider(r);
	}
	
	@Override
	public String getName() {
		return "FAST AGGRESSIVE";
	}
	
	@Override
	public synchronized int next() {
		if(regenerate)
			field.setShape(Shape.O_DOWN);
		return super.next();
	}

}
