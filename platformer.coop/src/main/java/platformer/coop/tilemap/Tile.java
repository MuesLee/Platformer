package platformer.coop.tilemap;

import java.awt.image.BufferedImage;

public class Tile {

	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;

	private BufferedImage image;
	private int type;

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
