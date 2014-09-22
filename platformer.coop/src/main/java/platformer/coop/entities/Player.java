package platformer.coop.entities;

public class Player extends GameEntity {

	public Player() {
		super();
		setMoveSpeed(5);
	}

	public void update() {
		if (isJumping()) {
			x = (int) Math.min(x + jumpStart, jumpMax);
		}
		if (isMovingRight) {
			x += moveSpeed;
		}
		if (isMovingLeft) {
			x -= moveSpeed;
		}
	}
}
