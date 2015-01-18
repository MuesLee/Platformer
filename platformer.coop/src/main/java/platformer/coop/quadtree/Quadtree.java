package platformer.coop.quadtree;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import platformer.coop.entities.StaticGameEntity;

public class Quadtree {

	private static final int TOP_LEFT_QUADRANT = 1;
	private static final int TOP_RIGHT_QUADRANT = 0;
	private static final int BOTTOM_LEFT_QUADRANT = 2;
	private static final int BOTTM_RIGHT_QUADRANT = 3;

	private int MAX_OBJECTS = 5;
	private int MAX_LEVELS = 3;

	private int level;
	private List<StaticGameEntity> entities;
	private Rectangle bounds;
	private Quadtree[] nodes;

	public Quadtree(int startLevel, Rectangle bounds) {
		this.level = startLevel;
		entities = new ArrayList<StaticGameEntity>();
		this.bounds = bounds;
		nodes = new Quadtree[4];
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

		Iterator<StaticGameEntity> iterator = entities.iterator();
		while (iterator.hasNext()) {
			StaticGameEntity entity = iterator.next();
			int index = getIndex(entity);
			if (index != -1) {
				nodes[index].insert(entity);
				iterator.remove();
			}
		}
	}

	/**
	 * Gibt den Index des Quadranten zur�ck, in den das Rechteck passen w�rde.
	 * Returns -1 wenn es nirgends vollst�ndig hereinpasst.
	 * 
	 * @param entity
	 *            zu �berpr�fendes Rechteck
	 * @return Index des Quadranten. -1 wenn es nicht vollst�ndig in einen
	 *         Quadranten passt.
	 */
	private int getIndex(StaticGameEntity entity) {
		int index = -1;
		double midpointX = bounds.getX() + (bounds.getWidth() / 2);
		double midpointY = bounds.getY() + (bounds.getHeight() / 2);

		// Rechteck passt komplett in die obere H�lfte des Quadtrees
		boolean topQuadrant = (entity.getY() < midpointY && entity.getY()
				+ entity.getHeight() < midpointY);
		// Rechteck passt komplett in die untere H�lfte des Quadtrees
		boolean bottomQuadrant = (entity.getY() > midpointY);

		// Rechteck passt komplett in die linke H�lfte des Quadtrees
		if (entity.getX() < midpointX
				&& entity.getX() + entity.getWidth() < midpointX) {
			if (topQuadrant) {
				index = TOP_LEFT_QUADRANT;
			} else if (bottomQuadrant) {
				index = BOTTOM_LEFT_QUADRANT;
			}
		}
		// Rechteck passt komplett in die rechte H�lfte des Quadtrees
		else if (entity.getX() > midpointX) {
			if (topQuadrant) {
				index = TOP_RIGHT_QUADRANT;
			} else if (bottomQuadrant) {
				index = BOTTM_RIGHT_QUADRANT;
			}
		}

		return index;
	}

	/**
	 * F�gt das Rectangle in den Quadtree ein. Wenn die maximale Gr��e dadurch
	 * �berschritten wird, teilt sich der Quadtree.
	 * 
	 * @param rectangle
	 */
	public void insert(StaticGameEntity entity) {

		int index = getIndex(entity);

		if (index != -1) {
			if (nodes[index] == null) {
				split();
			}
			nodes[index].insert(entity);

		} else {
			entities.add(entity);
		}

		if (entities.size() > MAX_OBJECTS && level < MAX_LEVELS) {
			if (nodes[0] == null) {
				split();
			}
		}
	}

	/**
	 * Gibt alle Rechtecke zur�ck, die mit dem �bergebenen Rechteck kollidieren
	 * k�nnten.
	 * 
	 * @param returnObjects
	 *            Ergebnisliste f�r rekursiven Aufruf. Darf initial null sein.
	 * @param entity
	 *            zu �berpr�fendes Rechteck
	 * @return Liste mit Rechtecken, die mit dem �bergebenen kollidieren k�nnten
	 */
	public List<StaticGameEntity> retrieve(
			List<StaticGameEntity> returnObjects, StaticGameEntity entity) {

		if (returnObjects == null) {
			returnObjects = new ArrayList<>(MAX_OBJECTS);
		}

		int index = getIndex(entity);

		if (index == -1) {

			for (int i = 0; i < nodes.length; i++) {
				if (nodes[i] != null) {
					returnObjects.addAll(entities);
					nodes[i].retrieve(returnObjects, entity);
				}
			}

		} else {
			if (nodes[index] != null) {
				nodes[index].retrieve(returnObjects, entity);
			}
		}
		return returnObjects;
	}

}
