package platformer.coop.gamestates;

import de.ts.gameengine.gamestates.AbstractGameState;
import de.ts.gameengine.gamestates.GameStateFactory;

public class MyPoorGameStateFactory implements GameStateFactory
{

	public AbstractGameState createGameState(int stateByNumber)
	{
		AbstractGameState gameState = null;

		switch (stateByNumber)
		{
			case 0:
				gameState = new MainMenuState();
			break;
			case 1:
				gameState = new Level1State();
			break;
			default:
			break;

		}

		return gameState;

	}


}
