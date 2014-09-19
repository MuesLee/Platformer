package platformer.coop.controller;

import java.awt.Graphics2D;

import platformer.coop.gamestates.AbstractGameState;
import platformer.coop.gamestates.GameStates;
import platformer.coop.gamestates.MenuState;
import platformer.coop.util.GameStateFactory;

public class GameStateManager {

	public static int REAL_FPS;

	private AbstractGameState currentState;

	private static GameStateManager instance;

	private GameController gameController;

	private GameStateManager(GameController gameController) {
		this.gameController = gameController;
		final MenuState menuState = new MenuState(this);
		currentState = menuState;
		gameController.getFrame().addKeyListener(menuState);
	}

	public static GameStateManager getInstance(GameController gameController) {
		if (instance == null) {
			instance = new GameStateManager(gameController);
		}

		return instance;
	}

	public void init() {
		getCurrentState().init();
	}

	public void draw(Graphics2D g) {
		getCurrentState().draw(g);
	}

	public void update() {
		getCurrentState().update();
	}

	private AbstractGameState getCurrentState() {
		return currentState;
	}

	public void loadState(int number) {
		GameStates stateByNumber = GameStates.getStateByNumber(number);
		final AbstractGameState nextState = GameStateFactory.createGameState(
				stateByNumber, getGameController());
		nextState.init();
		currentState = nextState;
	}

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

}
