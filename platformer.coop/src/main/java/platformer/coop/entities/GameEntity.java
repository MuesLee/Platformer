package platformer.coop.entities;

import java.awt.Rectangle;

import platformer.coop.tilemap.Tile;
import platformer.coop.tilemap.TileMap;

public abstract class GameEntity {

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

	public boolean intersects(GameEntity entity) {
		Rectangle recThis = getCollisionBox();
		Rectangle recOther = entity.getCollisionBox();

		return recThis.intersects(recOther);
	}

	public Rectangle getCollisionBox() {
		return new Rectangle(x - collisionBoxWidth, y - collisionBoxHeight,
				collisionBoxWidth, collisionBoxHeight);
	}

	public void checkTileMapCollision() {
		currentRow = y / tileSize;
		currentColumn = x / tileSize;

		xDestination = x + dx;
		yDestination = y + dy;

		xTemp = x;
		yTemp = y;

		calculateCorners(x, yDestination);

		if (dy < 0) {
			if (topLeft || topRight) {
				dy = 0;
				yTemp = currentRow * tileSize + collisionBoxHeight / 2;
			} else {
				yTemp += dy;
			}
		} else if (dy > 0) {
			if (bottomLeft || bottomRight) {
				dy = 0;
				isFalling = false;
				yTemp = (currentRow + 1) * tileSize - collisionBoxHeight / 2;
			} else {
				yTemp += dy;
			}
		}

		calculateCorners(xDestination, y);

		if (dx < 0) {
			if (topLeft || bottomLeft) {
				dx = 0;
				xTemp = currentColumn * tileSize + collisionBoxWidth / 2;
			} else {
				xTemp += dx;
			}
		} else if (dx > 0) {
			if (topRight || bottomRight) {
				dx = 0;
				xTemp = (currentColumn + 1) * tileSize - collisionBoxWidth / 2;
			}
		}

		if (!isFalling()) {
			calculateCorners(x, yDestination + 1);
			if (!(bottomLeft || bottomRight)) {
				isFalling = true;
			}

		}
	}

	public void calculateCorners(double x, double y) {
		int leftTile = (int) (x - collisionBoxWidth / 2) / tileSize;
		int rightTile = (int) (x + collisionBoxWidth / 2 - 1) / tileSize;
		int topTile = (int) (y - collisionBoxHeight / 2) / tileSize;
		int bottomTile = (int) (y + collisionBoxHeight / 2 - 1) / tileSize;
		if (topTile < 0 || bottomTile >= tileMap.getNumRows() || leftTile < 0
				|| rightTile >= tileMap.getNumCols()) {
			topLeft = topRight = bottomLeft = bottomRight = false;
			return;
		}
		int tl = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int bl = tileMap.getType(bottomTile, leftTile);
		int br = tileMap.getType(bottomTile, rightTile);
		topLeft = tl == Tile.BLOCKED;
		topRight = tr == Tile.BLOCKED;
		bottomLeft = bl == Tile.BLOCKED;
		bottomRight = br == Tile.BLOCKED;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setMapPosition(int x, int y) {
		xMap = tileMap.getX();
		yMap = tileMap.getY();
	}

	public void setVector(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
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

	public TileMap getTileMap() {
		return tileMap;
	}

	public void setTileMap(TileMap tileMap) {
		this.tileMap = tileMap;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	public int getxMap() {
		return xMap;
	}

	public void setxMap(int xMap) {
		this.xMap = xMap;
	}

	public int getyMap() {
		return yMap;
	}

	public void setyMap(int yMap) {
		this.yMap = yMap;
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

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
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

	public int getxDestination() {
		return xDestination;
	}

	public void setxDestination(int xDestination) {
		this.xDestination = xDestination;
	}

	public int getyDestination() {
		return yDestination;
	}

	public void setyDestination(int yDestination) {
		this.yDestination = yDestination;
	}

	public int getxTemp() {
		return xTemp;
	}

	public void setxTemp(int xTemp) {
		this.xTemp = xTemp;
	}

	public int getyTemp() {
		return yTemp;
	}

	public void setyTemp(int yTemp) {
		this.yTemp = yTemp;
	}

	public boolean isBottomLeft() {
		return bottomLeft;
	}

	public void setBottomLeft(boolean bottomLeft) {
		this.bottomLeft = bottomLeft;
	}

	public boolean isBottomRight() {
		return bottomRight;
	}

	public void setBottomRight(boolean bottomRight) {
		this.bottomRight = bottomRight;
	}

	public boolean isTopLeft() {
		return topLeft;
	}

	public void setTopLeft(boolean topLeft) {
		this.topLeft = topLeft;
	}

	public boolean isTopRight() {
		return topRight;
	}

	public void setTopRight(boolean topRight) {
		this.topRight = topRight;
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

	public double getJumpStart() {
		return jumpStart;
	}

	public void setJumpStart(double jumpStart) {
		this.jumpStart = jumpStart;
	}

	public double getJumpMax() {
		return jumpMax;
	}

	public void setJumpMax(double jumpMax) {
		this.jumpMax = jumpMax;
	}

}
