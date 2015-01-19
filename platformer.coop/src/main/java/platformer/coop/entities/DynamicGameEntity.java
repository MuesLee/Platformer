package platformer.coop.entities;

import java.awt.Graphics2D;

public class DynamicGameEntity extends StaticGameEntity {

	protected boolean isFacingRight;

	private MoveActions moveActions;

	protected double moveSpeed;
	protected double moveSpeedMax;
	protected double moveSpeedSlowDownRate;
	protected double moveSpeedIncreaseRate;
	protected double fallSpeed;
	protected double jumpSpeed;
	protected double jumpSpeedIncrease;
	protected double jumpMax;

	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	
	public DynamicGameEntity() {
		super();

		this.setMoveActions(new MoveActions());
		setAnimation(new Animation());
	

	}

	@Override
	public void update() {

		move();
		getAnimation().update();

	}
	
	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(animation.getImage(), null, x, y);
	}

	private void move() {

			if (getMoveActions().isMovingRight()) {
				moveSpeed = Math.min(moveSpeedMax, moveSpeed
						+ moveSpeedIncreaseRate);
				x += moveSpeed;

			} else if (getMoveActions().isMovingLeft()) {
				moveSpeed = Math.min(moveSpeedMax, moveSpeed
						+ moveSpeedIncreaseRate);
				x -= moveSpeed;

			} else {
				moveSpeed = 0;
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

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public int getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(int currentAction) {
		this.currentAction = currentAction;
	}

	public int getPreviousAction() {
		return previousAction;
	}

	public void setPreviousAction(int previousAction) {
		this.previousAction = previousAction;
	}

}
