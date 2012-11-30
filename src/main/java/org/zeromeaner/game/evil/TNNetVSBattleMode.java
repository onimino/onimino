package org.zeromeaner.game.evil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eviline.randomizer.MaliciousRandomizer.MaliciousRandomizerProperties;
import org.eviline.randomizer.Randomizer;
import org.eviline.randomizer.RandomizerFactory;
import org.zeromeaner.game.component.Block;
import org.zeromeaner.game.component.Piece;
import org.zeromeaner.game.event.EventReceiver;
import org.zeromeaner.game.play.GameEngine;
import org.zeromeaner.game.subsystem.mode.NetVSBattleMode;
import org.zeromeaner.game.subsystem.wallkick.StandardWallkick;
import org.zeromeaner.util.GeneralUtil;

public class TNNetVSBattleMode extends NetVSBattleMode {
	protected boolean waiting = false;
	
	protected EventReceiver receiver;
	
	protected Map<GameEngine, TNRandomizer> randomizers = new HashMap<GameEngine, TNRandomizer>();
	
	public TNNetVSBattleMode() {
		LINE_ATTACK_TABLE =
			new int[][] {
				// 1-2P, 3P, 4P, 5P, 6P
				{1, 1, 1, 1, 1},	// Single
				{2, 2, 2, 2, 2},	// Double
				{3, 3, 3, 3, 3},	// Triple
				{4, 4, 4, 4, 4},	// Four
				{2, 2, 2, 2, 2},	// T-Mini-S
				{1, 1, 1, 1, 1},	// T-Single
				{3, 3, 3, 3, 3},	// T-Double
				{5, 5, 5, 5, 5},	// T-Triple
				{3, 3, 3, 3, 3},	// T-Mini-D
				{1, 1, 0, 0, 0},	// EZ-T
			};
		LINE_ATTACK_TABLE_ALLSPIN =
			new int[][] {
				// 1-2P, 3P, 4P, 5P, 6P
				{1, 1, 1, 1, 1},	// Single
				{2, 2, 2, 2, 2},	// Double
				{3, 3, 3, 3, 3},	// Triple
				{4, 4, 4, 4, 4},	// Four
				{2, 2, 2, 2, 2},	// T-Mini-S
				{1, 1, 1, 1, 1},	// T-Single
				{3, 3, 3, 3, 3},	// T-Double
				{5, 5, 5, 5, 5},	// T-Triple
				{3, 3, 3, 3, 3},	// T-Mini-D
				{1, 1, 0, 0, 0},	// EZ-T
			};
	}
	
	@Override
	public String getName() {
		return "NET-EVILINE-VS-BATTLE";
	}

	@Override
	public void playerInit(GameEngine engine, int playerID) {
		super.playerInit(engine, playerID);
		receiver = engine.owner.receiver;
		engine.ruleopt = new TNRuleOptions(engine.ruleopt);
		engine.randomizer = new TNConcurrentRandomizer() {
			@Override
			public void setEngine(GameEngine engine) {
				super.setEngine(engine);
				MaliciousRandomizerProperties mp = new MaliciousRandomizerProperties(2, .01, true, 30);
				mp.put(RandomizerFactory.CONCURRENT, "true");
				mp.put(RandomizerFactory.NEXT, "1");
				Randomizer r = new RandomizerFactory().newRandomizer(mp);
				field.setProvider(r);
			}
			
			@Override
			public String getName() {
				return "NET EVIL";
			}
		};
		randomizers.put(engine, (TNRandomizer) engine.randomizer);
		((TNRandomizer) engine.randomizer).setEngine(engine);
		engine.wallkick = new StandardWallkick();
	}
	
	@Override
	public boolean onSetting(GameEngine engine, int playerID) {
		boolean ret = super.onSetting(engine, playerID);
		engine.randomizer = randomizers.get(engine);
		if(engine.randomizer == null)
			return ret;
		engine.nextPieceArraySize = 1;
		if(engine.nextPieceArrayID != null)
			engine.nextPieceArrayID = Arrays.copyOf(engine.nextPieceArrayID, 1);
		if(engine.nextPieceArrayObject != null)
			engine.nextPieceArrayObject = Arrays.copyOf(engine.nextPieceArrayObject, 1);
		return ret;
	}
	
	public static Piece newPiece(int id) {
		if(id != Piece.PIECE_NONE)
			return new Piece(id);
		Piece p = new Piece();
//		p.id = Piece.PIECE_NONE;
		p.dataX = new int[0][0];
		p.dataY = new int[0][0];
		return p;
	}
	
	@Override
	public boolean onMove(GameEngine engine, int playerID) {
		engine.randomizer = randomizers.get(engine);
		retaunt(engine);
		if(!waiting)
			return super.onMove(engine, playerID);
		
		int next = engine.randomizer.next();
		if(next == -1)
			return true;
		
		regenerate(engine);
//		engine.statARE();
		waiting = false;
		
		return true;
	}
	
	public void retaunt(GameEngine engine) {
		String taunt = ((TNRandomizer) engine.randomizer).field.getProvider().getTaunt();
		if(taunt == null || taunt.isEmpty())
			taunt = " ";
		
		engine.nextPieceArraySize = taunt.length();
		if(engine.nextPieceArrayID != null)
			engine.nextPieceArrayID = Arrays.copyOf(engine.nextPieceArrayID, taunt.length());
		if(engine.nextPieceArrayObject != null)
			engine.nextPieceArrayObject = Arrays.copyOf(engine.nextPieceArrayObject, taunt.length());
		
		for(int i = 1; i < taunt.length(); i++) {
			switch(taunt.charAt(i)) {
			case 'T': 
				engine.nextPieceArrayID[i] = Piece.PIECE_T;
				break;
			case 'S':
				engine.nextPieceArrayID[i] = Piece.PIECE_S;
				break;
			case 'Z':
				engine.nextPieceArrayID[i] = Piece.PIECE_Z;
				break;
			case 'L':
				engine.nextPieceArrayID[i] = Piece.PIECE_L;
				break;
			case 'J':
				engine.nextPieceArrayID[i] = Piece.PIECE_J;
				break;
			case 'O':
				engine.nextPieceArrayID[i] = Piece.PIECE_O;
				break;
			case 'I':
				engine.nextPieceArrayID[i] = Piece.PIECE_I;
				break;
			}
		}
		
		engine.ruleopt.nextDisplay = taunt.length() - 1;

		try {
			for(int i = 0; i < engine.nextPieceArrayObject.length; i++) {
				engine.nextPieceArrayObject[i] = newPiece(engine.nextPieceArrayID[i]);
				engine.nextPieceArrayObject[i].direction = engine.ruleopt.pieceDefaultDirection[engine.nextPieceArrayObject[i].id];
				if(engine.nextPieceArrayObject[i].direction >= Piece.DIRECTION_COUNT) {
					engine.nextPieceArrayObject[i].direction = engine.random.nextInt(Piece.DIRECTION_COUNT);
				}
				engine.nextPieceArrayObject[i].connectBlocks = engine.connectBlocks;
				engine.nextPieceArrayObject[i].setColor(engine.ruleopt.pieceColor[engine.nextPieceArrayObject[i].id]);
				engine.nextPieceArrayObject[i].setSkin(engine.getSkin());
				engine.nextPieceArrayObject[i].updateConnectData();
				engine.nextPieceArrayObject[i].setAttribute(Block.BLOCK_ATTRIBUTE_VISIBLE, true);
				engine.nextPieceArrayObject[i].setAttribute(Block.BLOCK_ATTRIBUTE_BONE, engine.bone);
			}
			if (engine.randomBlockColor)
			{
				if (engine.blockColors.length < engine.numColors || engine.numColors < 1)
					engine.numColors = engine.blockColors.length;
				for(int i = 0; i < engine.nextPieceArrayObject.length; i++) {
					int size = engine.nextPieceArrayObject[i].getMaxBlock();
					int[] colors = new int[size];
					for (int j = 0; j < size; j++)
						colors[j] = engine.blockColors[engine.random.nextInt(engine.numColors)];
					engine.nextPieceArrayObject[i].setColor(colors);
					engine.nextPieceArrayObject[i].updateConnectData();
				}
			}
		} catch(RuntimeException re) {
		}
	
	}


	public void regenerate(GameEngine engine) {
		int next = engine.randomizer.next();

		engine.nextPieceArrayID[0] = next;
		engine.nextPieceCount = 0;

		retaunt(engine);
		
	}
	
	@Override
	public void pieceLocked(GameEngine engine, int playerID, int lines) {
		engine.randomizer = randomizers.get(engine);
		((TNRandomizer) engine.randomizer).regenerate = true;

		regenerate(engine);
		
		waiting = true;
		
		super.pieceLocked(engine, playerID, lines);
	}
	
	@Override
	public void renderLast(GameEngine engine, int playerID) {
		engine.randomizer = randomizers.get(engine);
		super.renderLast(engine, playerID);
		if(engine.randomizer == null)
			return;
		double[] evil = ((TNRandomizer) engine.randomizer).score();
//		receiver.drawScoreFont(engine, playerID, 0, 17, ((TNRandomizer) engine.randomizer).getName(), EventReceiver.COLOR_BLUE);
//		receiver.drawScoreFont(engine, playerID, 0, 18, "" + ((int) evil[0]) + "(" + ((int) evil[1]) + ")", EventReceiver.COLOR_WHITE);
	}

}
