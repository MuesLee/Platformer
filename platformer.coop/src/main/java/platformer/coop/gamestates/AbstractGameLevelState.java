package platformer.coop.gamestates;

import java.awt.Graphics2D;
import java.util.ArrayList;

import platformer.coop.collision.CollisionManager;
import platformer.coop.controller.GameStateManager;
import platformer.coop.entities.Enemy;
import platformer.coop.entities.Player;
import platformer.coop.entities.Tile;
import platformer.coop.view.Camera;
import platformer.coop.view.TileMap;

public class AbstractGameLevelState extends AbstractGameState {

	protected TileMap tileMap;
	protected CollisionManager collisionManager;

	protected Camera camera;

	protected ArrayList<Enemy> enemies;

	public AbstractGameLevelState(GameStateManager gameStateManager) {
		super(gameStateManager);
		this.collisionManager = new CollisionManager(this);
		this.enemies = new ArrayList<Enemy>();
	}

	@Override
	public void draw(Graphics2D g2d) {

		drawBackground(g2d);

		g2d.translate(-camera.getX(), -camera.getY());

		drawTileMap(g2d);
		drawPlayer(g2d);
		drawEnemies(g2d);
	}

	private void drawEnemies(Graphics2D g2d) {
		for (Enemy enemy : enemies) {
			enemy.draw(g2d);
		}
	}

	protected void drawTileMap(Graphics2D g2d) {
		tileMap.draw(g2d);
	}

	protected void drawPlayer(Graphics2D g2d) {
		for (Player player : getPlayers()) {
			player.draw(g2d);
		}
	}

	@Override
	public void update() {
		background.update();
		camera.update();
		for (Player player : getPlayers()) {
			player.update();
		}
		for (Enemy enemy : enemies) {
			enemy.update();
		}

	}

	@Override
	public void init() {

		Tile[][] tiles = tileMap.getTiles();

		for (Tile[] tiles2 : tiles) {
			for (Tile tile : tiles2) {
				collisionManager.addStaticEntity(tile);
			}
		}

	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

}
