package platformer.coop.entities;

public abstract class GameEntity
{

	public boolean isMovingLeft()
	{
		return isMovingLeft;
	}

	public void setMovingLeft(boolean isMovingLeft)
	{
		this.isMovingLeft = isMovingLeft;
	}

	public boolean isMovingRight()
	{
		return isMovingRight;
	}

	public void setMovingRight(boolean isMovingRight)
	{
		this.isMovingRight = isMovingRight;
	}

	public boolean isJumping()
	{
		return isJumping;
	}

	public void setJumping(boolean isJumping)
	{
		this.isJumping = isJumping;
	}

	public boolean isCrouching()
	{
		return isCrouching;
	}

	public void setCrouching(boolean isCrouching)
	{
		this.isCrouching = isCrouching;
	}

	public boolean isShooting()
	{
		return isShooting;
	}

	public void setShooting(boolean isShooting)
	{
		this.isShooting = isShooting;
	}

	public int getMoveSpeed()
	{
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed)
	{
		this.moveSpeed = moveSpeed;
	}

	public int getJumpSpeed()
	{
		return jumpSpeed;
	}

	public void setJumpSpeed(int jumpSpeed)
	{
		this.jumpSpeed = jumpSpeed;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	private boolean isMovingLeft = false;
	private boolean isMovingRight = false;
	private boolean isJumping = false;
	private boolean isCrouching = false;
	private boolean isShooting = false;

	private int moveSpeed;
	private int jumpSpeed;

	private String name;
}
