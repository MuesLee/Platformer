package platformer.coop.controller;

import java.awt.Graphics2D;

import javax.swing.plaf.IconUIResource;

import platformer.coop.gamestates.AbstractGameState;
import platformer.coop.gamestates.GameState;
import platformer.coop.gamestates.MenuState;
import platformer.coop.util.GameStateFactory;
import platformer.coop.view.Camera;

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
		AbstractGameState currentGameState = getCurrentState();
		currentGameState.setPlayers(gameController.getPlayers());
		currentGameState.init();
	}

	public void draw(Graphics2D g) {

		AbstractGameState tempCurrentState = getCurrentState();

		tempCurrentState.drawBackground(g);
		Camera camera = tempCurrentState.getCamera();

		g.translate(-camera.getX(), -camera.getY());

		tempCurrentState.draw(g);

		g.translate(0, 0);
	}

	public void update() {
		getCurrentState().update();
	}

	private AbstractGameState getCurrentState() {
		return currentState;
	}

	public void loadState(int number) {
		gameController.pauseGameloop();
		
		GameState stateByNumber = GameState.getStateByNumber(number);
		final AbstractGameState nextState = GameStateFactory.createGameState(
				stateByNumber, getGameController());
				currentState = nextState;
				init();
				
		gameController.unpauseGameloop();
	}

	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}
}
