package platformer.coop.gamestates;

import de.ts.gameengine.gamestates.GameStateManager;
import de.ts.gameengine.gamestates.GameStateManagerFactory;

public class MyGameStateManagerFactory implements GameStateManagerFactory{

	private GameStateManager instance;
	
	@Override
	public GameStateManager createGamestateManager() {
		
		if(instance == null)
		{
			instance = new MyGameStateManager();
		}
		
		return instance;
	}

}
