package platformer.coop.controller;

import java.awt.Graphics2D;

import platformer.coop.gamestates.AbstractGameState;
import platformer.coop.gamestates.GameStates;
import platformer.coop.gamestates.MenuState;
import platformer.coop.util.GameStateFactory;

public class GameStateManager
{

	public static int REAL_FPS;

	private AbstractGameState currentState;

	private static GameStateManager instance;

	private GameStateManager()
	{
		currentState = new MenuState(this);
	}

	public static GameStateManager getInstance()
	{
		if (instance == null)
		{
			instance = new GameStateManager();
		}

		return instance;
	}

	public void init()
	{
		getCurrentState().init();
	}

	public void draw(Graphics2D g)
	{
		getCurrentState().draw(g);
	}

	public void update()
	{
		getCurrentState().update();
	}

	private AbstractGameState getCurrentState()
	{
		return currentState;
	}

	public void loadState(int number)
	{
		GameStates stateByNumber = GameStates.getStateByNumber(number);
		currentState = GameStateFactory.createGameState(stateByNumber);
	}

}
