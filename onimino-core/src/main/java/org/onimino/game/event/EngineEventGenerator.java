package org.onimino.game.event;

import org.onimino.game.subsystem.mode.GameMode;

public interface EngineEventGenerator {
	public void addEngineListener(EngineListener l);
	public void addGameMode(GameMode mode);
	public void addReceiver(EventRenderer receiver);
	public void removeEngineListener(EngineListener l);
}
