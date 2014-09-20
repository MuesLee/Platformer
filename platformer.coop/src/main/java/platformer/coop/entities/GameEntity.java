package platformer.coop.entities;

import java.awt.Rectangle;

import platformer.coop.tilemap.TileMap;

public abstract class GameEntity
{
	
	protected TileMap tileMap;
	protected int tileSize;
	protected int xMap;
	protected int yMap;
	
	protected int x;
	protected int y;
	protected int dx;
	protected int dy;
	
	protected int width;
	protected int height;
	
	protected int collisionBoxWidth;
	protected int collisionBoxHeight;
	
	protected int currentRow;
	protected int currentColumn;
	protected int xDestination;
	protected int yDestination;
	protected int xTemp;
	protected int yTemp;
	
	protected boolean bottomLeft;
	protected boolean bottomRight;
	protected boolean topLeft;
	protected boolean topRight;
	
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	protected boolean isFacingRight;
	
	protected boolean isMovingLeft = false;
	protected boolean isMovingRight = false;
	protected boolean isJumping = false;
	protected boolean isCrouching = false;
	protected boolean isShooting = false;
	protected boolean isFalling = false;

	protected double moveSpeed;
	protected double moveSpeedMax;
	protected double moveSpeedSlowDownRate;
	protected double fallSpeed;
	protected double jumpStart;
	protected double jumpMax;

	protected String name;

	
	
	
	public GameEntity(TileMap tileMap) {
		super();
		this.tileMap = tileMap;
		this.tileSize = tileMap.getTileSize();
	}
	
	public boolean intersects(GameEntity entity){
		Rectangle recThis = getCollisionBox();
		Rectangle recOther = entity.getCollisionBox();
		
		return recThis.intersects(recOther);
	}
	
	public Rectangle getCollisionBox()
	{
		return new Rectangle(x-collisionBoxWidth, y-collisionBoxHeight, collisionBoxWidth, collisionBoxHeight);
	}

	public boolean isMovingLeft() {
		return isMovingLeft;
	}

	public void setMovingLeft(boolean isMovingLeft) {
		this.isMovingLeft = isMovingLeft;
	}

	public boolean isMovingRight() {
		return isMovingRight;
	}

	public void setMovingRight(boolean isMovingRight) {
		this.isMovingRight = isMovingRight;
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public boolean isCrouching() {
		return isCrouching;
	}

	public void setCrouching(boolean isCrouching) {
		this.isCrouching = isCrouching;
	}

	public boolean isShooting() {
		return isShooting;
	}

	public void setShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFalling() {
		return isFalling;
	}

	public void setFalling(boolean isFalling) {
		this.isFalling = isFalling;
	}
}
