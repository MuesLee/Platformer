package platformer.coop.controller;

import java.awt.Graphics2D;

import platformer.coop.gamestates.AbstractGameState;
import platformer.coop.gamestates.GameStates;
import platformer.coop.gamestates.MenuState;
import platformer.coop.util.GameStateFactory;
import platformer.coop.view.Camera;

public class GameStateManager {

	public static int REAL_FPS;

	private AbstractGameState currentState;

	private static GameStateManager instance;

	private GameController gameController;

	private Camera camera;

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
		AbstractGameState currentGameState = getCurrentState();
		currentGameState.setPlayers(gameController.getPlayers());
		currentGameState.init();
	}

	public void draw(Graphics2D g) {
		g.translate(-camera.getX(), -camera.getY());

		getCurrentState().draw(g);

		g.translate(camera.getX(), camera.getY());
	}

	public void update() {
		getCurrentState().update();
		camera.update();
	}

	private AbstractGameState getCurrentState() {
		return currentState;
	}

	public void loadState(int number) {
		GameStates stateByNumber = GameStates.getStateByNumber(number);
		final AbstractGameState nextState = GameStateFactory.createGameState(
				stateByNumber, getGameController());
		currentState = nextState;
		init();
	}

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

}
