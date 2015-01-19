package platformer.coop.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public abstract class StaticGameEntity {

	private long id;

	protected int x;
	protected int y;

	protected int width;
	protected int height;

	protected int collisionBoxWidth;
	protected int collisionBoxHeight;

	protected String name;

	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;
	protected int type;

	protected BufferedImage image;
	
	public StaticGameEntity() {
		super();
		
	}

	public void update() {

		processInputs();

		System.out.println(name + ": X " + x);
		System.out.println(name + ": Y " + y);

	}

	protected void processInputs() {
		// TODO Auto-generated method stub

	}

	public void draw(Graphics2D g2d) {

		g2d.drawImage(image, null, x, y);

	}
	
	
	public Rectangle getTopLine()
	{
		Rectangle topLine = new Rectangle(x, y, width, 0);
		
		return topLine;
	}
	
	public Rectangle getBotLine()
	{
		Rectangle botLine = new Rectangle(x, y+height, width, 0);
		
		return botLine;
	}
	public Rectangle getLeftLine()
	{
		Rectangle leftLine = new Rectangle(x, y, 0, height);
		
		return leftLine;
	}
	public Rectangle getRightLine()
	{
		Rectangle rightLine = new Rectangle(x+width, y, 0, height);
		
		return rightLine;
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
}
