package platformer.coop.entities;

public class Player extends GameEntity {

	public Player() {
		super();
		setMoveSpeed(5);
	}

	public void update() {

		System.out.println("player X" + x);

		if (isJumping()) {
			x = (int) Math.min(x + jumpStart, jumpMax);
		}
		if (isMovingRight) {
			System.out.println("isMoving Right");
			x += moveSpeed;
		}
		if (isMovingLeft) {
			x -= moveSpeed;
		}
	}
}
