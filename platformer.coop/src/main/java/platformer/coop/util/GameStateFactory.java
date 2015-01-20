package platformer.coop.util;

import platformer.coop.controller.GameController;
import platformer.coop.gamestates.AbstractGameState;
import platformer.coop.gamestates.GameState;
import platformer.coop.gamestates.GameStateManager;
import platformer.coop.gamestates.Level1State;
import platformer.coop.gamestates.MenuState;

public class GameStateFactory
{

	public static AbstractGameState createGameState(GameState stateByNumber, GameController gameController)
	{
		AbstractGameState gameState = null;

		switch (stateByNumber)
		{
			case MENU_STATE:
				gameState = new MenuState(GameStateManager.getInstance(gameController));
			break;
			case LEVEL_1:
				gameState = new Level1State(GameStateManager.getInstance(gameController));
			break;
			default:
			break;

		}

		return gameState;

	}

}
