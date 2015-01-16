package platformer.coop.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import platformer.coop.tilemap.TileMap;

public abstract class StaticGameEntity {

	private long id;

	protected TileMap tileMap;

	protected int x;
	protected int y;

	protected int width;
	protected int height;

	protected int collisionBoxWidth;
	protected int collisionBoxHeight;

	protected String name;
	private int tileWidth;
	private int tileHeight;

	protected Animation animation;
	protected int currentAction;
	protected int previousAction;

	public StaticGameEntity() {
		super();
		animation = new Animation();
	}

	public void update() {

		processInputs();

		animation.update();

		System.out.println(name + ": X " + x);
		System.out.println(name + ": Y " + y);

	}

	protected void processInputs() {
		// TODO Auto-generated method stub

	}

	public void draw(Graphics2D g2d) {

		g2d.drawImage(animation.getImage(), null, x, y);

	}

	public boolean intersects(StaticGameEntity entity) {
		Rectangle recThis = getCollisionBox();
		Rectangle recOther = entity.getCollisionBox();

		return recThis.intersects(recOther);
	}

	public Rectangle getCollisionBox() {
		return new Rectangle(x - collisionBoxWidth, y - collisionBoxHeight,
				collisionBoxWidth, collisionBoxHeight);
	}

	public boolean intersects(Rectangle r) {
		return getCollisionBox().intersects(r);
	}

	public boolean contains(StaticGameEntity o) {
		Rectangle r1 = getCollisionBox();
		Rectangle r2 = o.getCollisionBox();
		return r1.contains(r2);
	}

	public boolean contains(Rectangle r) {
		return getCollisionBox().contains(r);
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
		StaticGameEntity other = (StaticGameEntity) obj;
		if (id != other.id)
			return false;
		return true;
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
