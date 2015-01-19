package platformer.coop.collision;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;

import platformer.coop.controller.GameController;
import platformer.coop.entities.DynamicGameEntity;
import platformer.coop.entities.StaticGameEntity;
import platformer.coop.gamestates.AbstractGameLevelState;
import platformer.coop.view.Camera;

public class CollisionManager {

	private Quadtree dynamicQuadtree;

	private Quadtree staticQuadtree;
	
	private GameController gameController;

	private AbstractGameLevelState gameState;

	public CollisionManager(AbstractGameLevelState gameState) {

		this.gameController = GameController.getInstance();
		this.gameState = gameState;
	}
	
	public void initDynamicQuadtree()
	{
		Camera camera = gameState.getCamera();
		
		this.dynamicQuadtree = createNewQuadtree(camera.getViewRectangle());
	}
	
	
	public void initStaticQuadtree(Rectangle bounds, List<StaticGameEntity> entities)
	{
		this.staticQuadtree = createNewQuadtree(bounds);
		
		for (StaticGameEntity staticGameEntity : entities) {
			staticQuadtree.insert(staticGameEntity);
		}
		
	}
	

	private Quadtree createNewQuadtree(Rectangle bounds) {
		return new Quadtree(0, bounds);
	}

	/**
	 * F�gt eine Entity dem dynamischen Quadtree hinzu. 
	 * 
	 * @param entity
	 */
	public void addDynamicEntity(DynamicGameEntity entity) {
		dynamicQuadtree.insert(entity);
	}
	
	
	/**
	 * F�gt eine statische, unbewegliche Entity dem statischen Quadtree hinzu.
	 * 
	 * @param entity
	 */
	public void addStaticEntity(StaticGameEntity entity)
	{
		staticQuadtree.insert(entity);
	}

	public void clearDynamicQuadtree() {
		this.dynamicQuadtree = createNewQuadtree(gameState.getCamera().getViewRectangle());
	}

	/**
	 * Gibt eine Liste mit Entities zur�ck, die mit der �bergebenen Entity
	 * kollidieren. Zus�tzlich wird f�r jede Kollision ein Attribut
	 * zur�ckgeliefert, welches angibt, an welcher Seite des �bergebenen Objekts
	 * diese Kollision besteht.
	 * 
	 * Kollisionen werden �berpr�ft f�r Entities im statischen und dynamischen Quadtree.
	 * 
	 * @param entity
	 * @return Zuordnung ber�hrter Objekte und der Seite, mit der dieses Objekt ber�hrt wurde.
	 */
	public HashMap<StaticGameEntity, Collision> retrieveCollisions(
			StaticGameEntity entity) {

		HashMap<StaticGameEntity, Collision> collidedEntities = new HashMap<>();

		List<StaticGameEntity> possibleCollisions = dynamicQuadtree.retrieve(null,
				entity);
		
		possibleCollisions = dynamicQuadtree.retrieve(possibleCollisions, entity);

		Rectangle entityBotLine = entity.getBotLine();
		Rectangle entityTopLine = entity.getTopLine();
		Rectangle entityLeftLine = entity.getLeftLine();
		Rectangle entityRightLine = entity.getRightLine();

		for (StaticGameEntity other : possibleCollisions) {

			// Rectangle otherBotLine = entity.getBotLine();
			// Rectangle otherTopLine = entity.getTopLine();
			// Rectangle otherLeftLine = entity.getLeftLine();
			// Rectangle otherRightLine = entity.getRightLine();
			//
			if (other.intersects(entityTopLine)) {
				collidedEntities.put(other, Collision.TOP);
			} else if (other.intersects(entityBotLine)) {
				collidedEntities.put(other, Collision.BOT);
				
			} else if (other.intersects(entityRightLine)) {
				collidedEntities.put(other, Collision.RIGHT);
				
			} else if (other.intersects(entityLeftLine)) {
				collidedEntities.put(other, Collision.LEFT);
			}
		}

		return collidedEntities;

	}

}
