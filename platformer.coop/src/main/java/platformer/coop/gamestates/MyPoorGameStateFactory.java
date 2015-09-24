package platformer.coop.gamestates;

import de.ts.gameengine.gamestates.AbstractGameState;
import de.ts.gameengine.gamestates.GameState;
import de.ts.gameengine.gamestates.GameStateFactory;

public class MyPoorGameStateFactory implements GameStateFactory
{

	public AbstractGameState createGameState(GameState stateByNumber)
	{
		AbstractGameState gameState = null;

		switch (stateByNumber)
		{
			case MENU_STATE:
				gameState = new MainMenuState();
			break;
			case LEVEL_1:
				gameState = new Level1State();
			break;
			default:
			break;

		}

		return gameState;

	}

}
