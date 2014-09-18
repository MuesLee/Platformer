package platformer.coop.controller;

import java.awt.BorderLayout;
import java.util.ArrayList;

import platformer.coop.controls.KeyBindings;
import platformer.coop.entities.Player;
import platformer.coop.util.Clock;
import platformer.coop.util.ClockListener;
import platformer.coop.view.GameFrame;
import platformer.coop.view.GamePanel;

public class GameController implements ClockListener
{

	public static final String GAME_TITLE = "EPIC NAME";

	public static final int WIDTH = 640;
	public static final int HEIGHT = 320;

	private Clock clock;

	private GameStateManager gameStateManager;

	private GameFrame frame;

	private KeyBindings keyBindings;

	private ArrayList<Player> players;

	public GameController()
	{

		players = new ArrayList<Player>();

		Player playerOne = new Player();
		players.add(playerOne);

		keyBindings.setPlayerOne(playerOne);
		//TODO: Implement player Two
		keyBindings.setPlayerTwo(playerOne);

		Clock gameClock = new Clock(20);
		Clock frameClock = new Clock(60);
		setClock(gameClock);

		frame = new GameFrame(GAME_TITLE);
		final GamePanel gamePanel = new GamePanel(GameStateManager.getInstance());
		frame.setLayout(new BorderLayout());
		frame.add(gamePanel, BorderLayout.CENTER);
		frame.setClock(frameClock);
		frame.pack();
		keyBindings = new KeyBindings(gamePanel);

		init();

		gameClock.start();
		frameClock.start();
		frame.setVisible(true);
	}

	@Override
	public void tick()
	{
		gameStateManager.update();
	}

	public Clock getClock()
	{
		return clock;
	}

	public void setClock(Clock clk)
	{
		if (this.clock != null)
		{
			this.clock.removeClockListener(this);
		}

		this.clock = clk;
		this.clock.addClockListener(this);
	}

	public void init()
	{
		gameStateManager = GameStateManager.getInstance();
		gameStateManager.init();
	}

	public KeyBindings getKeyBindings()
	{
		return keyBindings;
	}

	public void setKeyBindings(KeyBindings keyBindings)
	{
		this.keyBindings = keyBindings;
	}
}
