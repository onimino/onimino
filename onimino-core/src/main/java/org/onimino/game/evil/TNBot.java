package org.onimino.game.evil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.eviline.core.Command;
import org.eviline.core.Engine;
import org.eviline.core.ShapeType;
import org.eviline.core.XYShape;
import org.eviline.core.ai.AIPlayer;
import org.eviline.core.ai.DefaultAIKernel;
import org.eviline.core.ai.Fitness;
import org.eviline.core.ai.NextFitness;
import org.onimino.game.component.Block;
import org.onimino.game.component.Controller;
import org.onimino.game.play.GameEngine;
import org.onimino.game.subsystem.ai.AbstractAI;


public class TNBot extends AbstractAI {

	public static int controllerButtonId(Command cmd) {
		switch(cmd) {
		case SHIFT_DOWN:
		case SOFT_DROP:
			return Controller.BUTTON_DOWN;
		case ROTATE_LEFT:
			return Controller.BUTTON_B;
		case ROTATE_RIGHT:
			return Controller.BUTTON_A;
		case SHIFT_LEFT:
		case AUTOSHIFT_LEFT:
			return Controller.BUTTON_LEFT;
		case SHIFT_RIGHT:
		case AUTOSHIFT_RIGHT:
			return Controller.BUTTON_RIGHT;
		case HARD_DROP:
			return Controller.BUTTON_UP;
		}
		throw new InternalError("switch fallthrough when all cases covered");
	}

	public static class Dig extends TNBot {
		protected int garbageY = -1;
		
		@Override
		public String getName() {
			return super.getName() + " [Dig Challenge]";
		}
		
		@Override
		public void init(GameEngine engine, int playerID) {
			super.init(engine, playerID);
		}

		@Override
		public void setControl(GameEngine engine, int playerID, Controller ctrl) {
			org.onimino.game.component.Field field = engine.field;
			boolean garbage = false;
			for(int y = 0; y < field.getHeight(); y++) {
				for(int x = 0; x < field.getWidth(); x++) {
					Block b = field.getBlock(x, field.getHeight() - 1);
					if(b.getAttribute(Block.BLOCK_ATTRIBUTE_GARBAGE)) {
						garbage = true;
						break;
					}
				}
				if(garbage) {
					if(garbageY != y) {
						garbageY = y;
						ctrl.clearButtonState();
						recompute(engine);
					}
					break;
				}
			}
			if(!garbage)
				return;
			super.setControl(engine, playerID, ctrl);
		}
	}
	
	protected static int MAX_RECOMPUTES = 5;

	protected static ExecutorService POOL = Executors.newCachedThreadPool();

	protected TNField field;
	protected DefaultAIKernel kernel = new DefaultAIKernel();
	protected boolean pressed = false;

	protected Future<List<Command>> futureActions;

	protected int lookahead = 1;
	protected int das = 0;
	protected Integer buttonId;
	
	@Override
	public String getName() {
		return "Eviline AI";
	}

	@Override
	public void init(GameEngine engine, int playerID) {
		field = new TNField(engine);
		engine.aiShowHint = false;
		kernel.setFitness(new NextFitness());
	}

	protected List<Command> actions() {
		if(futureActions == null || futureActions.isCancelled() || !futureActions.isDone())
			return null;
		try {
			return futureActions.get();
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
			//			throw new RuntimeException(ex);
		}
	}
	
	protected void recompute(final GameEngine engine) {

		try {

			field.update(lookahead);
			field.updateShape();

			Callable<List<Command>> task = new Callable<List<Command>>() {
				@Override
				public List<Command> call() throws Exception {
					AIPlayer pl = new AIPlayer(kernel, field);
					List<Command> cmds = new ArrayList<>();
					cmds.add(pl.tick());
					pl.tick();
					cmds.addAll(pl.getCommands());
					return cmds;
				}
			};

			if(engine.aiUseThread) {
				futureActions = POOL.submit(task);
			} else {
				FutureTask<List<Command>> ft = new FutureTask<List<Command>>(task);
				ft.run();
				futureActions = ft;
			}
		} catch(RuntimeException|Error e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void newPiece(GameEngine engine, int playerID) {
		recompute(engine);
	}

	@Override
	public void onFirst(GameEngine engine, int playerID) {
	}

	@Override
	public void onLast(GameEngine engine, int playerID) {
	}

	@Override
	public void setControl(GameEngine engine, int playerID, Controller ctrl) {
		if((engine.nowPieceObject != null) && (engine.stat == GameEngine.Status.MOVE) && (engine.statc[0] > 0))
			;
		else
			return;

		if(buttonId != null) {
			buttonId = null;
			return;
		}

		if(actions() == null)
			return;

		if(actions().size() == 0) {
			recompute(engine);
			return;
		}

		Command pa = actions().remove(0);
		field.updateShape();
		
		if(pa == Command.SOFT_DROP) {
			if(!field.getField().intersects(field.getShape().shiftedDown())) {
				actions().add(0, pa);
			} else
				pa = null;
		}
		
		if(pa == Command.AUTOSHIFT_LEFT) {
			if(!field.getField().intersects(field.getShape().shiftedLeft())) {
				actions().add(0, pa);
			} else
				pa = null;
		}
		
		if(pa == Command.AUTOSHIFT_RIGHT) {
			if(!field.getField().intersects(field.getShape().shiftedRight())) {
				actions().add(0, pa);
			} else
				pa = null;
		}
		
		if(pa != null) {
			buttonId = controllerButtonId(pa);
			ctrl.setButtonPressed(buttonId);
		}
	}

	@Override
	public void shutdown(GameEngine engine, int playerID) {
	}

	@Override
	public void renderState(GameEngine engine, int playerID) {
		super.renderState(engine, playerID);
	}

}
