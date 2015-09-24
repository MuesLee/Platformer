package platformer.coop.gamestates;

import de.ts.gameengine.entities.Player;
import de.ts.gameengine.gamestates.AbstractGameLevelState;
import de.ts.gameengine.view.Background;
import de.ts.gameengine.view.Camera;
import de.ts.gameengine.view.TileMap;

public class Level1State extends AbstractGameLevelState {

	public Level1State() {
		super();
	}



	@Override
	public void init() {
		
		super.init();
		
		
		final Background background = new Background(
				"Tiles/Background/background_day_clouds.jpg", 1);
		background.setVector(5, 0);
		setBackground(background);

		tileMap = new TileMap(30);
		tileMap.loadTiles("Tiles/grass_flowers_30.png");
		tileMap.loadMap("Maps/level1.map");
		tileMap.setPosition(0, 0);

		int spawnBoxSize = 8;
		for (int i = 0; i < getPlayers().size(); i++) {
			Player player = getPlayers().get(i);
			player.setPosition(0 + spawnBoxSize * i, 0);
		}

		setCamera(new Camera(getPlayers(), tileMap, 1.0));
		collisionManager.initStaticQuadtree(tileMap.getBounds(), tileMap.computeStaticGameEntitiesForTiles());
		collisionManager.initDynamicQuadtree();
	}

}
