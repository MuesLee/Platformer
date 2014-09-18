package platformer.coop.util;

import platformer.coop.controller.GameStateManager;
import platformer.coop.gamestates.AbstractGameState;
import platformer.coop.gamestates.GameStates;
import platformer.coop.gamestates.MenuState;

public class GameStateFactory
{

	public static AbstractGameState createGameState(GameStates stateByNumber)
	{
		AbstractGameState gameState = null;

		switch (stateByNumber)
		{
			case MENU_STATE:
				gameState = new MenuState(GameStateManager.getInstance());
			break;
			case LEVEL_1:
			break;
			default:
			break;

		}

		return gameState;

	}

}
