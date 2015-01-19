package platformer.coop.collision;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import platformer.coop.entities.StaticGameEntity;

public class Quadtree {

	private static final int TOP_LEFT_QUADRANT = 1;
	private static final int TOP_RIGHT_QUADRANT = 0;
	private static final int BOTTOM_LEFT_QUADRANT = 2;
	private static final int BOTTM_RIGHT_QUADRANT = 3;

	private static final int MAX_OBJECTS = 5;
	private static final int MAX_LEVELS = 3;

	private int level;
	private List<StaticGameEntity> entities;
	private Rectangle bounds;
	private Quadtree[] nodes;

	public Quadtree(int startLevel, Rectangle bounds) {
		this.level = startLevel;
		entities = new ArrayList<StaticGameEntity>(MAX_OBJECTS);
		this.bounds = bounds;
		nodes = new Quadtree[4];
		
		if(level < MAX_LEVELS)
		{
			split();
		}
	}
	
	public void clear() {
		entities.clear();

		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] != null) {
				nodes[i].clear();
				nodes[i] = null;
			}
		}
	}

	private void split() {
		int halvedWidth = (int) (bounds.getWidth() / 2);
		int halvedHeight = (int) (bounds.getHeight() / 2);
		int x = (int) bounds.getX();
		int y = (int) bounds.getY();

		nodes[TOP_RIGHT_QUADRANT] = new Quadtree(level + 1, new Rectangle(x
				+ halvedWidth, y, halvedWidth, halvedHeight));
		nodes[TOP_LEFT_QUADRANT] = new Quadtree(level + 1, new Rectangle(x, y,
				halvedWidth, halvedHeight));
		nodes[BOTTOM_LEFT_QUADRANT] = new Quadtree(level + 1, new Rectangle(x,
				y + halvedHeight, halvedWidth, halvedHeight));
		nodes[BOTTM_RIGHT_QUADRANT] = new Quadtree(level + 1, new Rectangle(x
				+ halvedWidth, y + halvedHeight, halvedWidth, halvedHeight));

//		Iterator<StaticGameEntity> iterator = entities.iterator();
//		while (iterator.hasNext()) {
//			StaticGameEntity entity = iterator.next();
//			int index = getIndex(entity);
//			if (index != -1) {
//				nodes[index].insert(entity);
//				iterator.remove();
//			}
//		}
	}

	/**
	 * Gibt den Index des Quadranten zurück, in den das Rechteck passen würde.
	 * Returns -1 wenn es nirgends vollständig hereinpasst.
	 * 
	 * @param rectangle
	 *            zu überprüfendes Rechteck
	 * @return Index des Quadranten. -1 wenn es nicht vollständig in einen
	 *         Quadranten passt.
	 */
	private int getIndex(StaticGameEntity entitiy) {
		int index = -1;
		double midpointX = bounds.getX() + (bounds.getWidth() / 2);
		double midpointY = bounds.getY() + (bounds.getHeight() / 2);

		Rectangle rectangle = entitiy.getCollisionBox();
		
		// Rechteck passt komplett in die obere Hälfte des Quadtrees
		boolean topQuadrant = (rectangle.getY() < midpointY && rectangle.getY()
				+ rectangle.getHeight() < midpointY);
		// Rechteck passt komplett in die untere Hälfte des Quadtrees
		boolean bottomQuadrant = (rectangle.getY() > midpointY);

		// Rechteck passt komplett in die linke Hälfte des Quadtrees
		if (rectangle.getX() < midpointX
				&& rectangle.getX() + rectangle.getWidth() < midpointX) {
			if (topQuadrant) {
				index = TOP_LEFT_QUADRANT;
			} else if (bottomQuadrant) {
				index = BOTTOM_LEFT_QUADRANT;
			}
		}
		// Rechteck passt komplett in die rechte Hälfte des Quadtrees
		else if (rectangle.getX() > midpointX) {
			if (topQuadrant) {
				index = TOP_RIGHT_QUADRANT;
			} else if (bottomQuadrant) {
				index = BOTTM_RIGHT_QUADRANT;
			}
		}

		return index;
	}

	/**
	 * Fügt eine Entity in den Quadtree ein.
	 * 
	 * @param rectangle
	 */
	public void insert(StaticGameEntity entity) {

		int index = getIndex(entity);

		if (index != -1 && nodes[index] != null) {
			nodes[index].insert(entity);

		} else {
			entities.add(entity);
		}
	}

	/**
	 * Gibt alle Rechtecke zurück, die mit dem übergebenen Rechteck kollidieren
	 * könnten.
	 * 
	 * @param returnObjects
	 *            Ergebnisliste für rekursiven Aufruf. Darf initial null sein.
	 * @param entity
	 *            zu überprüfendes Rechteck
	 * @return Liste mit Rechtecken, die mit dem übergebenen kollidieren könnten
	 */
	public List<StaticGameEntity> retrieve(
			List<StaticGameEntity> returnObjects, StaticGameEntity entity) {

		if (returnObjects == null) {
			returnObjects = new ArrayList<>(MAX_OBJECTS);
		}

		int index = getIndex(entity);

		if (index == -1) {

			returnObjects.addAll(entities);
			
			for (int i = 0; i < nodes.length; i++) {
				if (nodes[i] != null) {
					nodes[i].retrieve(returnObjects, entity);
				}
			}

		} else {
			if (nodes[index] != null) {
				nodes[index].retrieve(returnObjects, entity);
			}
			else {
				returnObjects.addAll(entities);
			}
		}
		return returnObjects;
	}

}
