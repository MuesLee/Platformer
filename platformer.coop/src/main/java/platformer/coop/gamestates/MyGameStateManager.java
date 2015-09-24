package platformer.coop.gamestates;

import de.ts.gameengine.gamestates.GameStateManager;

public class MyGameStateManager extends GameStateManager {

	public MyGameStateManager() {
		super();
		setGameStateFactory(new MyPoorGameStateFactory());
	}
	
	@Override
	public void loadState(int number) {
		super.loadState(number);
		if(currentState instanceof MainMenuState)
		{
			MainMenuState mainMenuState = (MainMenuState) currentState;
			mainMenuState.addKeyListenerToComponent(getGameController().getFrame());
		}
	}
}
