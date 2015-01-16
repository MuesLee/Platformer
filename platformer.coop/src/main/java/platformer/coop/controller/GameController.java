package platformer.coop.controller;

import java.awt.BorderLayout;
import java.util.ArrayList;

import platformer.coop.controls.KeyBindings;
import platformer.coop.entities.MoveActions;
import platformer.coop.entities.Player;
import platformer.coop.util.Clock;
import platformer.coop.util.ClockListener;
import platformer.coop.view.GameFrame;
import platformer.coop.view.GamePanel;

public class GameController implements ClockListener {

	public static final String GAME_TITLE = "EPIC NAME";

	public static final int WIDTH = 640;
	public static final int HEIGHT = 320;

	private Clock clock;

	private GameStateManager gameStateManager;

	private GameFrame frame;

	private KeyBindings keyBindings;

	private ArrayList<Player> players;

	public GameController() {

		setPlayers(new ArrayList<Player>());

		Clock gameClock = new Clock(40);
		Clock frameClock = new Clock(60);
		setClock(gameClock);

		setFrame(new GameFrame(GAME_TITLE));
		final GamePanel gamePanel = new GamePanel(
				GameStateManager.getInstance(this));
		getFrame().setLayout(new BorderLayout());
		getFrame().add(gamePanel, BorderLayout.CENTER);
		getFrame().setClock(frameClock);
		getFrame().pack();

		Player playerOne = new Player(this, 1);
		playerOne.setName("Player One");
		Player playerTwo = new Player(this, 2);
		playerTwo.setName("Player Two");
		getPlayers().add(playerOne);
		keyBindings = new KeyBindings(gamePanel);
		keyBindings.setPlayerOneInput(playerOne.getMoveActions());
		keyBindings.setPlayerTwoInput(playerTwo.getMoveActions());
		init();

		gameClock.start();
		frameClock.start();
		getFrame().setVisible(true);
	}

	@Override
	public void tick() {
		gameStateManager.update();
	}

	public Clock getClock() {
		return clock;
	}

	public void setClock(Clock clk) {
		if (this.clock != null) {
			this.clock.removeClockListener(this);
		}

		this.clock = clk;
		this.clock.addClockListener(this);
	}

	public void init() {
		gameStateManager = GameStateManager.getInstance(this);
		gameStateManager.init();
	}

	public KeyBindings getKeyBindings() {
		return keyBindings;
	}

	public void setKeyBindings(KeyBindings keyBindings) {
		this.keyBindings = keyBindings;
	}

	public GameFrame getFrame() {
		return frame;
	}

	public void setFrame(GameFrame frame) {
		this.frame = frame;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public MoveActions getInputForPlayer(int playerID) {

		switch (playerID) {
		case 1:
			return keyBindings.getPlayerOneInput();
		case 2:
			return keyBindings.getPlayerTwoInput();
		default:
			return keyBindings.getPlayerOneInput();
		}

	}
}
