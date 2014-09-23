package platformer.coop.gamestates;

import java.awt.Graphics2D;

import platformer.coop.controller.GameStateManager;
import platformer.coop.entities.Player;
import platformer.coop.tilemap.Background;
import platformer.coop.tilemap.TileMap;
import platformer.coop.view.Camera;

public class Level1State extends AbstractGameState {

	private TileMap tileMap;

	public Level1State(GameStateManager manager) {
		super(manager);
	}

	@Override
	public void draw(Graphics2D g2d) {
		// super.getBackground().draw(g2d);

		tileMap.draw(g2d);
		for (Player player : players) {
			player.draw(g2d);
		}
	}

	@Override
	public void update() {
		super.getBackground().update();
		getCamera().update();
		for (Player player : players) {
			player.update();
		}
	}

	@Override
	public void init() {
		final Background background = new Background(
				"Tiles/Background/background_day_clouds.jpg", 1);
		background.setVector(5, 0);
		setBackground(background);

		tileMap = new TileMap(30);
		tileMap.loadTiles("Tiles/grass_flowers_30.png");
		tileMap.loadMap("Maps/level1.map");
		tileMap.setPosition(0, 0);

		int spawnBoxSize = 8;
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			player.setTileMap(tileMap);
			player.setPosition(0 + spawnBoxSize * i, 0);
		}

		setCamera(new Camera(players, tileMap, 1.0));
	}

}
