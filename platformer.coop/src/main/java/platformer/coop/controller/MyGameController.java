package platformer.coop.controller;

import de.ts.gameengine.controller.GameController;
import de.ts.gameengine.controls.KeyBindings;
import de.ts.gameengine.entities.Player;
import de.ts.gameengine.entities.movement.AnalogMoveActionHandler;
import de.ts.gameengine.entities.movement.GameInputHandler;
import de.ts.gameengine.entities.movement.HorizontalMovementHandler;
import de.ts.gameengine.entities.movement.JumpActionHandler;
import de.ts.gameengine.entities.movement.SpecialMoveActionHandler;
import platformer.coop.entities.MyPlayer;

public class MyGameController extends GameController {

	public MyGameController() {
		super("EPIC GAME");
	}

	@Override
	protected void configurePlayers() {
		SpecialMoveActionHandler specialMoveActionHandlerOne = new JumpActionHandler();
		AnalogMoveActionHandler analogMoveActionHandlerOne = new HorizontalMovementHandler();
		GameInputHandler gameInputHandlerOne = new GameInputHandler(analogMoveActionHandlerOne,
				specialMoveActionHandlerOne);
		SpecialMoveActionHandler specialMoveActionHandlerTwo = new JumpActionHandler();
		AnalogMoveActionHandler analogMoveActionHandlerTwo = new HorizontalMovementHandler();
		GameInputHandler gameInputHandlerTwo = new GameInputHandler(analogMoveActionHandlerTwo,
				specialMoveActionHandlerTwo);
		Player playerOne = new MyPlayer(this, gameInputHandlerOne, 1);
		playerOne.setName("Player One");
		Player playerTwo = new MyPlayer(this, gameInputHandlerTwo, 2);
		playerTwo.setName("Player Two");
		getPlayers().add(playerOne);
		keyBindings = new KeyBindings(panel);
		keyBindings.setPlayerOneInput(playerOne.getMoveActions());
		keyBindings.setPlayerTwoInput(playerTwo.getMoveActions());
	}

}
