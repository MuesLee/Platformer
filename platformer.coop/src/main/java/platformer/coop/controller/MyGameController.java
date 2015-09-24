package platformer.coop.controller;

import de.ts.gameengine.controller.GameController;
import de.ts.gameengine.controls.KeyBindings;
import de.ts.gameengine.entities.Player;
import platformer.coop.entities.MyPlayer;

public class MyGameController extends GameController {
	
	public MyGameController() {
		super("EPIC GAME");
	}
	
	@Override
	protected void configurePlayers() {
		Player playerOne = new MyPlayer(this, 1);
		playerOne.setName("Player One");
		Player playerTwo = new MyPlayer(this, 2);
		playerTwo.setName("Player Two");
		getPlayers().add(playerOne);
		keyBindings = new KeyBindings(panel);
		keyBindings.setPlayerOneInput(playerOne.getMoveActions());
		keyBindings.setPlayerTwoInput(playerTwo.getMoveActions());
	}

}
