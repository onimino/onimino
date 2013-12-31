package org.onimino.game.event;

import java.util.EventListener;

import org.onimino.game.subsystem.mode.GameMode;

public interface EngineListener extends EventListener {
	/**
	 * @see GameMode#playerInit(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void enginePlayerInit(EngineEvent e);
	
	/**
	 * @see GameMode#startGame(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void engineGameStarted(EngineEvent e);
	
	/**
	 * @see GameMode#onFirst(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void engineFrameFirst(EngineEvent e);
	
	/**
	 * @see GameMode#onLast(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void engineFrameLast(EngineEvent e);
	
	/**
	 * @see GameMode#onSetting(org.onimino.game.play.GameEngine, int)
	 * @param e
	 * @return
	 */
	public boolean engineSettings(EngineEvent e);
	
	/**
	 * @see GameMode#onReady(org.onimino.game.play.GameEngine, int)
	 * @param e
	 * @return
	 */
	public boolean engineReady(EngineEvent e);
	
	/**
	 * @see GameMode#onMove(org.onimino.game.play.GameEngine, int)
	 * @param e
	 * @return
	 */
	public boolean engineMove(EngineEvent e);
	
	/**
	 * @see GameMode#onLockFlash(org.onimino.game.play.GameEngine, int)
	 * @param e
	 * @return
	 */
	public boolean engineLockFlash(EngineEvent e);
	
	/**
	 * @see GameMode#onLineClear(org.onimino.game.play.GameEngine, int)
	 * @param e
	 * @return
	 */
	public boolean engineLineClear(EngineEvent e);
	
	/**
	 * @see GameMode#onARE(org.onimino.game.play.GameEngine, int)
	 * @param e
	 * @return
	 */
	public boolean engineARE(EngineEvent e);
	
	/**
	 * @see GameMode#onEndingStart(org.onimino.game.play.GameEngine, int)
	 * @param e
	 * @return
	 */
	public boolean engineEndingStart(EngineEvent e);
	
	/**
	 * @see GameMode#onCustom(org.onimino.game.play.GameEngine, int)
	 * @param e
	 * @return
	 */
	public boolean engineCustom(EngineEvent e);
	
	/**
	 * @see GameMode#onExcellent(org.onimino.game.play.GameEngine, int)
	 * @param e
	 * @return
	 */
	public boolean engineExcellent(EngineEvent e);
	
	/**
	 * @see GameMode#onGameOver(org.onimino.game.play.GameEngine, int)
	 * @param e
	 * @return
	 */
	public boolean engineGameOver(EngineEvent e);
	
	/**
	 * @see GameMode#onResult(org.onimino.game.play.GameEngine, int)
	 * @param e
	 * @return
	 */
	public boolean engineResults(EngineEvent e);
	
	/**
	 * @see GameMode#onFieldEdit(org.onimino.game.play.GameEngine, int)
	 * @param e
	 * @return
	 */
	public boolean engineFieldEditor(EngineEvent e);
	
	/**
	 * @see GameMode#renderFirst(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void engineRenderFirst(EngineEvent e);
	
	/**
	 * @see GameMode#renderLast(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void engineRenderLast(EngineEvent e);
	
	/**
	 * @see GameMode#renderSetting(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void engineRenderSettings(EngineEvent e);
	
	/**
	 * @see GameMode#renderReady(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void engineRenderReady(EngineEvent e);
	
	/**
	 * @see GameMode#renderMove(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void engineRenderMove(EngineEvent e);
	
	/**
	 * @see GameMode#renderLockFlash(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void engineRenderLockFlash(EngineEvent e);
	
	/**
	 * @see GameMode#renderLineClear(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void engineRenderLineClear(EngineEvent e);
	
	/**
	 * @see GameMode#renderARE(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void engineRenderARE(EngineEvent e);
	
	/**
	 * @see GameMode#renderEndingStart(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void engineRenderEndingStart(EngineEvent e);
	
	/**
	 * @see GameMode#renderCustom(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void engineRenderCustom(EngineEvent e);
	
	/**
	 * @see GameMode#renderExcellent(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void engineRenderExcellent(EngineEvent e);
	
	/**
	 * @see GameMode#renderGameOver(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void engineRenderGameOver(EngineEvent e);
	
	/**
	 * @see GameMode#renderResult(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void engineRenderResults(EngineEvent e);
	
	/**
	 * @see GameMode#renderFieldEdit(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void engineRenderFieldEditor(EngineEvent e);
	
	/**
	 * @see GameMode#renderInput(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void engineRenderInput(EngineEvent e);
	
	/**
	 * @see GameMode#blockBreak(org.onimino.game.play.GameEngine, int, int, int, org.onimino.game.component.Block)
	 * @param e
	 */
	public void engineBlockBreak(EngineEvent e);
	
	/**
	 * @see GameMode#calcScore(org.onimino.game.play.GameEngine, int, int)
	 * @param e
	 */
	public void engineCalcScore(EngineEvent e);
	
	/**
	 * @see GameMode#afterSoftDropFall(org.onimino.game.play.GameEngine, int, int)
	 * @param e
	 */
	public void engineAfterSoftDropFall(EngineEvent e);
	
	/**
	 * @see GameMode#afterHardDropFall(org.onimino.game.play.GameEngine, int, int)
	 * @param e
	 */
	public void engineAfterHardDropFall(EngineEvent e);
	
	/**
	 * @see GameMode#fieldEditExit(org.onimino.game.play.GameEngine, int)
	 * @param e
	 */
	public void engineFieldEditorExit(EngineEvent e);
	
	/**
	 * @see GameMode#pieceLocked(org.onimino.game.play.GameEngine, int, int)
	 * @param e
	 */
	public void enginePieceLocked(EngineEvent e);
	
	/**
	 * @see GameMode#lineClearEnd(org.onimino.game.play.GameEngine, int)
	 * @param e
	 * @return
	 */
	public boolean engineLineClearEnd(EngineEvent e);
	
	/**
	 * @see GameMode#saveReplay(org.onimino.game.play.GameEngine, int, org.onimino.util.CustomProperties)
	 * @param e
	 */
	public void engineSaveReplay(EngineEvent e);
	
	/**
	 * @see GameMode#loadReplay(org.onimino.game.play.GameEngine, int, org.onimino.util.CustomProperties)
	 * @param e
	 */
	public void engineLoadReplay(EngineEvent e);
}
