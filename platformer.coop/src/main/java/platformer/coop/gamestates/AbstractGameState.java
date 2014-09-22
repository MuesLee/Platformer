package platformer.coop.gamestates;

import java.awt.Graphics2D;
import java.util.ArrayList;

import platformer.coop.controller.GameStateManager;
import platformer.coop.entities.Player;
import platformer.coop.tilemap.Background;

public abstract class AbstractGameState {

	protected ArrayList<Player> players;
	protected GameStateManager manager;
	protected Background background;

	public AbstractGameState(GameStateManager manager) {
		this.setManager(manager);
	}

	public abstract void draw(Graphics2D g);

	public abstract void update();

	public abstract void init();

	public GameStateManager getManager() {
		return manager;
	}

	public void setManager(GameStateManager manager) {
		this.manager = manager;
	}

	public Background getBackground() {
		return background;
	}

	public void setBackground(Background background) {
		this.background = background;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

}
