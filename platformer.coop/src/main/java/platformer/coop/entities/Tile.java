package platformer.coop.entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tile extends StaticGameEntity {

	private Rectangle collisionBox;

	public Tile(BufferedImage image, int type) {
		super();
		this.setImage(image);
		this.setType(type);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {

		this.image = image;
	}

	@Override
	public Rectangle getCollisionBox() {
		return collisionBox;
	}

	public void setCollisionBox(Rectangle collisionBox) {
		this.collisionBox = collisionBox;
	}

}
