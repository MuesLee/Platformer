package platformer.coop.gamestates;

import java.awt.Graphics2D;
import java.util.ArrayList;

import platformer.coop.collision.CollisionManager;
import platformer.coop.collision.Quadtree;
import platformer.coop.controller.GameStateManager;
import platformer.coop.entities.Player;
import platformer.coop.tilemap.Background;
import platformer.coop.view.Camera;

public abstract class AbstractGameState {

	protected GameStateManager manager;
	protected Background background;
	
	protected ArrayList<Player> players;

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


	public void drawBackground(Graphics2D g2d) {
		if(background!= null)
		{
			getBackground().draw(g2d);
		}
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

}
