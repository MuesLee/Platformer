package platformer.coop.quadtree;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;


public class QuadtreeTest {

	
	private Rectangle rectangleTopLeft;
	private Rectangle rectangleTopRight;
	private Rectangle rectangleBotLeft;
	private Rectangle rectangleBotRight;
	private Quadtree quadtree;

	@Test
	public void shouldRetrieveTopLeftPossibleCollisions() throws Exception {
		
		Rectangle givenRectangle = rectangleTopLeft;
		List<Rectangle> actual = quadtree.retrieve(null, givenRectangle);
		List<Rectangle> expected =  new ArrayList<Rectangle>();
		expected.add(givenRectangle);
		assertEquals(expected, actual);
	}
	@Test
	public void shouldRetrieveTopRightPossibleCollisions() throws Exception {
		
		Rectangle givenRectangle = rectangleTopRight;
		List<Rectangle> actual = quadtree.retrieve(null, givenRectangle);
		List<Rectangle> expected =  new ArrayList<Rectangle>();
		expected.add(givenRectangle);
		assertEquals(expected, actual);
	}
	@Test
	public void shouldRetrieveBotLeftPossibleCollisions() throws Exception {
		
		Rectangle givenRectangle = rectangleBotLeft;
		List<Rectangle> actual = quadtree.retrieve(null, givenRectangle);
		List<Rectangle> expected =  new ArrayList<Rectangle>();
		expected.add(givenRectangle);
		assertEquals(expected, actual);
	}
	@Test
	public void shouldRetrieveBotRightPossibleCollisions() throws Exception {
		
		Rectangle givenRectangle = rectangleBotRight;
		List<Rectangle> actual = quadtree.retrieve(null, givenRectangle);
		List<Rectangle> expected =  new ArrayList<Rectangle>();
		expected.add(givenRectangle);
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldRetrieveAllRectanglesAsPossibleCollisions() throws Exception {
		
		Rectangle givenRectangle = new Rectangle(0,0,100,100);
		List<Rectangle> actual = quadtree.retrieve(null, givenRectangle);
		List<Rectangle> expected =  new ArrayList<Rectangle>();
		expected.add(rectangleBotLeft);
		expected.add(rectangleBotRight);
		expected.add(rectangleTopLeft);
		expected.add(rectangleTopRight);
		assertTrue(CollectionUtils.isEqualCollection(expected, actual));
	}

	@Before
	public void initDefaultQuadtree() {
		Rectangle bounds = new Rectangle(0,0,100,100);
		quadtree = new Quadtree(5, bounds);
		
		rectangleTopLeft = new Rectangle(0,0,10,10);
		rectangleTopRight = new Rectangle(90,0,10,10);
		rectangleBotLeft = new Rectangle(0,90,10,10);
		rectangleBotRight = new Rectangle(90,90,10,10);
		quadtree.insert(rectangleTopLeft);
		quadtree.insert(rectangleTopRight);
		quadtree.insert(rectangleBotLeft);
		quadtree.insert(rectangleBotRight);
		
	}
}
