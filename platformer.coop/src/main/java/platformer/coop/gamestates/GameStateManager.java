package platformer.coop.gamestates;

import java.awt.Graphics2D;

import platformer.coop.controller.GameController;
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
		AbstractGameState currentGameState = getCurrentState();
		currentGameState.setPlayers(gameController.getPlayers());
		currentGameState.init();
		
		System.out.println(currentGameState);
		System.out.println("########## MANAGER INIT GAME STATE #############");
	}

	public void draw(Graphics2D g) {

		AbstractGameState tempCurrentState = getCurrentState();

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
