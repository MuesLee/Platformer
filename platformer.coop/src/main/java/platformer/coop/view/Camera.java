package platformer.coop.view;

import java.util.List;

import platformer.coop.controller.GameController;
import platformer.coop.entities.Player;
import platformer.coop.tilemap.TileMap;

public class Camera {

	private int x;
	private int y;

	private int viewWidth;
	private int viewHeight;

	private TileMap tileMap;

	private List<Player> players;

	private double moveFactor;

	public Camera(List<Player> players, TileMap tileMap, double moveFactor) {
		super();
		this.x = 0;
		this.y = 0;
		this.players = players;
		this.moveFactor = moveFactor;
		this.viewHeight = GameController.HEIGHT;
		this.viewWidth = GameController.WIDTH;
		this.tileMap = tileMap;
	}

	public void update() {

		int xMax = 0;
		for (Player player : players) {
			xMax = Math.max(player.getX(), xMax);
		}

		this.x = Math.max(0, xMax - viewWidth / 2);
		this.tileMap.setPosition(x, y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getMoveFactor() {
		return moveFactor;
	}

	public void setMoveFactor(double moveFactor) {
		this.moveFactor = moveFactor;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public int getViewWidth() {
		return viewWidth;
	}

	public void setViewWidth(int viewWidth) {
		this.viewWidth = viewWidth;
	}

	public int getViewHeight() {
		return viewHeight;
	}

	public void setViewHeight(int viewHeight) {
		this.viewHeight = viewHeight;
	}

	public TileMap getTileMap() {
		return tileMap;
	}

	public void setTileMap(TileMap tileMap) {
		this.tileMap = tileMap;
	}

}
