package platformer.coop.entities;

public class DynamicGameEntity extends StaticGameEntity {

	protected boolean isFacingRight;

	private MoveActions moveActions;

	protected double moveSpeed;
	protected double moveSpeedMax;
	protected double moveSpeedSlowDownRate;
	protected double moveSpeedIncreaseRate;
	protected double fallSpeed;
	protected double jumpSpeed;
	private double jumpSpeedIncrease;
	protected double jumpMax;

	public DynamicGameEntity() {
		super();

		this.setMoveActions(new MoveActions());

		animation.update();

		System.out.println(name + ": X " + x);
		System.out.println(name + ": Y " + y);

	}

	@Override
	public void update() {

		move();
	}

	private void move() {

		if (getMoveActions().isMovingRight()) {
			moveSpeed = Math.min(moveSpeedMax, moveSpeed
					+ moveSpeedIncreaseRate);
		} else if (getMoveActions().isMovingLeft()) {
			moveSpeed = Math.min(moveSpeedMax, moveSpeed
					+ moveSpeedIncreaseRate);
			moveSpeed *= -1;
		} else {
			if (moveSpeed < 0) {
				moveSpeed = Math.min(moveSpeedSlowDownRate + moveSpeed, 0);
			} else if (moveSpeed > 0) {
				moveSpeed = Math.max(moveSpeedSlowDownRate - moveSpeed, 0);
			}
		}
	}

	public boolean isFacingRight() {
		return isFacingRight;
	}

	public void setFacingRight(boolean isFacingRight) {
		this.isFacingRight = isFacingRight;
	}

	public double getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(double moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public double getMoveSpeedMax() {
		return moveSpeedMax;
	}

	public void setMoveSpeedMax(double moveSpeedMax) {
		this.moveSpeedMax = moveSpeedMax;
	}

	public double getMoveSpeedSlowDownRate() {
		return moveSpeedSlowDownRate;
	}

	public void setMoveSpeedSlowDownRate(double moveSpeedSlowDownRate) {
		this.moveSpeedSlowDownRate = moveSpeedSlowDownRate;
	}

	public double getFallSpeed() {
		return fallSpeed;
	}

	public void setFallSpeed(double fallSpeed) {
		this.fallSpeed = fallSpeed;
	}

	public double getJumpMax() {
		return jumpMax;
	}

	public void setJumpMax(double jumpMax) {
		this.jumpMax = jumpMax;
	}

	public double getJumpSpeed() {
		return jumpSpeed;
	}

	public void setJumpSpeed(double jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
	}

	public double getMoveSpeedIncreaseRate() {
		return moveSpeedIncreaseRate;
	}

	public void setMoveSpeedIncreaseRate(double moveSpeedIncreaseRate) {
		this.moveSpeedIncreaseRate = moveSpeedIncreaseRate;
	}

	public double getJumpSpeedIncrease() {
		return jumpSpeedIncrease;
	}

	public void setJumpSpeedIncrease(double jumpSpeedIncrease) {
		this.jumpSpeedIncrease = jumpSpeedIncrease;
	}

	public MoveActions getMoveActions() {
		return moveActions;
	}

	public void setMoveActions(MoveActions moveActions) {
		this.moveActions = moveActions;
	}

}
