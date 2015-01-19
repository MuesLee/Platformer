package platformer.coop.controller;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import platformer.coop.controls.KeyBindings;
import platformer.coop.entities.Player;
import platformer.coop.view.GameFrame;
import platformer.coop.view.GamePanel;

public class GameController implements Runnable {

	public static final String GAME_TITLE = "EPIC NAME";

	public static final int WIDTH = 640;
	public static final int HEIGHT = 320;

	private static final int UPS = 30;

	private static final int FPS = 30;

	private static final boolean RENDER_TIME = true;

	private GameStateManager gameStateManager;

	private GameFrame frame;

	private KeyBindings keyBindings;

	private ArrayList<Player> players;

	private AtomicBoolean running = new AtomicBoolean(false);
	private AtomicBoolean paused = new AtomicBoolean(false);

	private GamePanel panel;

	public GameController() {

		setPlayers(new ArrayList<Player>());

		setFrame(new GameFrame(GAME_TITLE));
		panel = new GamePanel(GameStateManager.getInstance(this));
		getFrame().setLayout(new BorderLayout());
		getFrame().add(panel, BorderLayout.CENTER);
		getFrame().pack();

		Player playerOne = new Player(this, 1);
		playerOne.setName("Player One");
		Player playerTwo = new Player(this, 2);
		playerTwo.setName("Player Two");
		getPlayers().add(playerOne);
		keyBindings = new KeyBindings(panel);
		keyBindings.setPlayerOneInput(playerOne.getMoveActions());
		keyBindings.setPlayerTwoInput(playerTwo.getMoveActions());
		init();

		getFrame().setVisible(true);
	}

	public void startGameloop() {
		running.set(true);
		paused.set(false);
		run();
	}
	
	public void pauseGameloop ()
	{
		paused.set(true);
	}
	public void unpauseGameloop ()
	{
		paused.set(false);
	}

	public void stopGameloop() {
		running.set(false);
	}

	public void run() {

		long initialTime = System.nanoTime();
		final double timeU = 1000000000 / UPS;
		final double timeF = 1000000000 / FPS;
		double deltaU = 0, deltaF = 0;
		int frames = 0, ticks = 0;
		long timer = System.currentTimeMillis();

		while (running.get()) {

			while (!paused.get()) {

				long currentTime = System.nanoTime();
				deltaU += (currentTime - initialTime) / timeU;
				deltaF += (currentTime - initialTime) / timeF;
				initialTime = currentTime;

				if (deltaU >= 1) {
					getInput();
					update();
					ticks++;
					deltaU--;
				}

				if (deltaF >= 1) {
					render();
					frames++;
					deltaF--;
				}

				if (System.currentTimeMillis() - timer > 1000) {
					if (RENDER_TIME) {
						System.out.println(String.format("UPS: %s, FPS: %s",
								ticks, frames));
					}
					frames = 0;
					ticks = 0;
					timer += 1000;
				}
			}
		}
	}

	private void update() {
		gameStateManager.update();
	}

	private void getInput() {
		// TODO Auto-generated method stub

	}

	private void render() {
		panel.repaint();
	}

	private void init() {
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
}
