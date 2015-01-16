package platformer.coop.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import platformer.coop.tilemap.TileMap;

public abstract class GameEntity {

	private long id;
	
	protected TileMap tileMap;

	protected int x;
	protected int y;

	protected int width;
	protected int height;

	protected int collisionBoxWidth;
	protected int collisionBoxHeight;

	protected int currentRow;
	protected int currentColumn;

	protected boolean bottomLeft;
	protected boolean bottomRight;
	protected boolean topLeft;
	protected boolean topRight;

	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
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

	protected String name;
	private int tileWidth;
	private int tileHeight;

	public GameEntity() {
		super();
		animation = new Animation();
		this.setMoveActions(new MoveActions());
	}

	public void update() {

		processInputs();

		move();

		animation.update();

		System.out.println(name + ": X " + x);
		System.out.println(name + ": Y " + y);

	}

	protected void processInputs() {
		// TODO Auto-generated method stub

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

		for (int i = 0; i < moveSpeed; i++) {
			
			checkTileMapCollision();
			
			x += i;
		}
		
	}

	public void draw(Graphics2D g2d) {

		g2d.drawImage(animation.getImage(), null, x, y);

	}

	public boolean intersects(GameEntity entity) {
		Rectangle recThis = getCollisionBox();
		Rectangle recOther = entity.getCollisionBox();

		return recThis.intersects(recOther);
	}

	public Rectangle getCollisionBox() {
		return new Rectangle(x - collisionBoxWidth, y - collisionBoxHeight,
				collisionBoxWidth, collisionBoxHeight);
	}

	public boolean intersects(Rectangle r) {
		return getRectangle().intersects(r);
	}

	public boolean contains(GameEntity o) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.contains(r2);
	}

	public boolean contains(Rectangle r) {
		return getRectangle().contains(r);
	}

	public Rectangle getRectangle() {
		return new Rectangle(x - collisionBoxWidth / 2, y - collisionBoxHeight
				/ 2, collisionBoxWidth, collisionBoxHeight);
	}

	public void checkTileMapCollision() {
		currentRow = y / tileHeight;
		currentColumn = x / tileWidth;

	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TileMap getTileMap() {
		return tileMap;
	}

	public void setTileMap(TileMap tileMap) {
		this.tileMap = tileMap;
		this.tileWidth = tileMap.getTileWidth();
		this.tileHeight = tileMap.getTileHeight();

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getCollisionBoxWidth() {
		return collisionBoxWidth;
	}

	public void setCollisionBoxWidth(int collisionBoxWidth) {
		this.collisionBoxWidth = collisionBoxWidth;
	}

	public int getCollisionBoxHeight() {
		return collisionBoxHeight;
	}

	public void setCollisionBoxHeight(int collisionBoxHeight) {
		this.collisionBoxHeight = collisionBoxHeight;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	public int getCurrentColumn() {
		return currentColumn;
	}

	public void setCurrentColumn(int currentColumn) {
		this.currentColumn = currentColumn;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameEntity other = (GameEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
