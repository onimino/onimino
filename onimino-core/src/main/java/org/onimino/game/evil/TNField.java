package org.onimino.game.evil;

import org.eviline.core.Engine;
import org.eviline.core.Field;
import org.eviline.core.Shape;
import org.eviline.core.ShapeType;
import org.eviline.core.XYShape;
import org.eviline.core.ss.EvilBag7NShapeSource;
import org.onimino.game.component.Block;
import org.onimino.game.play.GameEngine;
import org.onimino.game.randomizer.Randomizer;

public class TNField extends Engine {
	
	protected GameEngine engine;
	
	public TNField(GameEngine engine) {
		super();
		this.engine = engine;
		shapes = new EvilBag7NShapeSource();
	}
	
	public void update(int lookahead) {
		if(engine.field == null)
			return;
		for(int y = -4; y < Field.HEIGHT; y++) {
			for(int x = 0; x < Field.WIDTH; x++) {
				org.onimino.game.component.Block npblock = engine.field.getBlock(x, y);
				ShapeType b = null;
				if(npblock != null) {
					switch(npblock.color) {
					case Block.BLOCK_COLOR_NONE: b = null; break;
					case Block.BLOCK_COLOR_YELLOW: b = ShapeType.O; break;
					case Block.BLOCK_COLOR_CYAN: b = ShapeType.I; break;
					case Block.BLOCK_COLOR_GREEN: b = ShapeType.S; break;
					case Block.BLOCK_COLOR_BLUE: b = ShapeType.J; break;
					case Block.BLOCK_COLOR_PURPLE: b = ShapeType.T; break;
					case Block.BLOCK_COLOR_RED: b = ShapeType.Z; break;
					case Block.BLOCK_COLOR_ORANGE: b = ShapeType.L; break;
					default:
						b = ShapeType.G;
					}
					if(npblock.getAttribute(Block.BLOCK_ATTRIBUTE_GARBAGE))
						b = ShapeType.G;
				}
				if(b != null)
					field.setBlock(x, y, new org.eviline.core.Block(b));
				else
					field.setBlock(x, y, null);
			}
		}
		
		next = new ShapeType[Math.min(engine.nextPieceArraySize, lookahead)];
		for(int i = 0; i < next.length; i++) {
			next[i] = TNPiece.fromNullpo(engine.getNextID(engine.nextPieceCount + i));
		}
	}
	
	public void updateShape() {
		Shape nps = TNPiece.fromNullpo(engine.nowPieceObject);
		shape = new XYShape(nps, engine.nowPieceX, engine.nowPieceY);
	}
}
