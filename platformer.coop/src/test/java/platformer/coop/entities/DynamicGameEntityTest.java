package platformer.coop.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DynamicGameEntityTest {

	private DynamicGameEntity classUnderTest;
	private MoveActions moveActions;

	@Before
	public void init() throws Exception {
		this.classUnderTest = new DynamicGameEntity();
		
		classUnderTest.setMoveSpeedMax(15);
		classUnderTest.setMoveSpeedIncreaseRate(5);
		classUnderTest.setAnimation(new Animation());
		moveActions = new MoveActions();
		classUnderTest.setMoveActions(moveActions);
	}
	
	
	@Test
	public void testEntityMoves1StepRight() throws Exception {
		
		moveActions.setMovingRight(true);

		classUnderTest.update();

		int expected = 5;
		int actual = classUnderTest.getX();

		assertEquals(expected, actual);
	}
	@Test
	public void testEntityMoves1Stepleft() throws Exception {
		
		moveActions.setMovingLeft(true);
		
		classUnderTest.update();
		
		int expected = -5;
		int actual = classUnderTest.getX();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEntityMoves2StepRight() throws Exception {
		
		moveActions.setMovingRight(true);

		classUnderTest.update();
		classUnderTest.update();

		int expected = 15;
		int actual = classUnderTest.getX();

		assertEquals(expected, actual);
	}
	@Test
	public void testEntityMoves2StepLeft() throws Exception {
		
		moveActions.setMovingLeft(true);
		
		classUnderTest.update();
		classUnderTest.update();
		
		int expected = -15;
		int actual = classUnderTest.getX();
		
		assertEquals(expected, actual);
	}

}
