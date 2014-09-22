package platformer.coop.controller;

import java.awt.BorderLayout;
import java.util.ArrayList;

import platformer.coop.controls.KeyBindings;
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

		players = new ArrayList<Player>();

		Player playerOne = new Player(tileMapPlayerOne);

		playerOne.setName("Player One");
		players.add(playerOne);

		Clock gameClock = new Clock(20);
		Clock frameClock = new Clock(60);
		setClock(gameClock);

		setFrame(new GameFrame(GAME_TITLE));
		final GamePanel gamePanel = new GamePanel(
				GameStateManager.getInstance(this));
		getFrame().setLayout(new BorderLayout());
		getFrame().add(gamePanel, BorderLayout.CENTER);
		getFrame().setClock(frameClock);
		getFrame().pack();
		keyBindings = new KeyBindings(gamePanel);
		keyBindings.setPlayerOne(playerOne);
		// TODO: Implement player Two
		keyBindings.setPlayerTwo(playerOne);

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
}
