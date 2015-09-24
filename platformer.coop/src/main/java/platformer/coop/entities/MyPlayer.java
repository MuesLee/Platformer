package platformer.coop.entities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import de.ts.gameengine.controller.GameController;
import de.ts.gameengine.entities.Player;

public class MyPlayer extends Player {


	public MyPlayer(GameController gameController, int playerID) {
		super(gameController, playerID);
	}

	@Override
	protected void fillSprites() {
		BufferedImage spritesheet;
		try {
			spritesheet = ImageIO.read(getClass().getResourceAsStream(
					"/Sprites/Player/nerdyguy_192_150.png"));

			int count = 0;
			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < NUMFRAMES.length; i++) {
				BufferedImage[] bi = new BufferedImage[NUMFRAMES[i]];
				for (int j = 0; j < NUMFRAMES[i]; j++) {
					bi[j] = spritesheet.getSubimage(j * FRAMEWIDTHS[i], count,
							FRAMEWIDTHS[i], FRAMEHEIGHTS[i]);
				}
				sprites.add(bi);
				count += FRAMEHEIGHTS[i];
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setAnimation(ANIMATION_MOVE_RIGHT);
	}
}
