package platformer.coop.quadtree;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Quadtree {

	private static final int TOP_LEFT_QUADRANT = 1;
	private static final int TOP_RIGHT_QUADRANT = 0;
	private static final int BOTTOM_LEFT_QUADRANT = 2;
	private static final int BOTTM_RIGHT_QUADRANT = 3;

	private int MAX_OBJECTS = 10;
	private int MAX_LEVELS = 5;

	private int level;
	private List<Rectangle> rectangles;
	private Rectangle bounds;
	private Quadtree[] nodes;

	public Quadtree(int level, Rectangle bounds) {
		this.level = level;
		rectangles = new ArrayList<Rectangle>();
		this.bounds = bounds;
		nodes = new Quadtree[4];
	}

	public void clear() {
		rectangles.clear();

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

		Iterator<Rectangle> iterator = rectangles.iterator();
		while (iterator.hasNext()) {
			Rectangle currentRectangle = iterator.next();
			int index = getIndex(currentRectangle);
			if (index != -1) {
				nodes[index].insert(currentRectangle);
				iterator.remove();
			}
		}
	}

	/**
	 * Gibt den Index des Quadranten zurück, in den das Rechteck passen würde.
	 * Returns -1 wenn es nirgends vollständig hereinpasst.
	 * 
	 * @param rectangle zu überprüfendes Rechteck
	 * @return Index des Quadranten. -1 wenn es nicht vollständig in einen
	 *         Quadranten passt.
	 */
	private int getIndex(Rectangle rectangle) {
		int index = -1;
		double midpointX = bounds.getX() + (bounds.getWidth() / 2);
		double midpointY = bounds.getY() + (bounds.getHeight() / 2);

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
	 * Fügt das Rectangle in den Quadtree ein. Wenn die maximale Größe dadurch
	 * überschritten wird, teilt sich der Quadtree.
	 * 
	 * @param rectangle
	 */
	public void insert(Rectangle rectangle) {

		int index = getIndex(rectangle);

		if (index == -1) {
			rectangles.add(rectangle);
		} else {
			if (nodes[index] == null) {
				split();
			}
			nodes[index].insert(rectangle);
		}

		if (rectangles.size() > MAX_OBJECTS && level < MAX_LEVELS) {
			if (nodes[0] == null) {
				split();
			}
		}
	}

	/**
	 * Gibt alle Rechtecke zurück, die mit dem übergebenen Rechteck kollidieren
	 * könnten.
	 * 
	 * @param returnObjects
	 *            Ergebnisliste für rekursiven Aufruf. Darf initial null sein.
	 * @param rectangle
	 *            zu überprüfendes Rechteck
	 * @return Liste mit Rechtecken, die mit dem übergebenen kollidieren könnten
	 */
	public List<Rectangle> retrieve(List<Rectangle> returnObjects,
			Rectangle rectangle) {

		if (returnObjects == null) {
			returnObjects = new ArrayList<>(MAX_OBJECTS);
		}

		int index = getIndex(rectangle);

		if (index == -1) {
			
			for (int i = 0; i < nodes.length; i++) {
				if(nodes[i]!= null)
				{
					nodes[i].retrieve(returnObjects, rectangle);
				}
			}

		} else {
			if (nodes[index] != null)
			{
				nodes[index].retrieve(returnObjects, rectangle);
			}
		}

		returnObjects.addAll(rectangles);

		return returnObjects;
	}

}
