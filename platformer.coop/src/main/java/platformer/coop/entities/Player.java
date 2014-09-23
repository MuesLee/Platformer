package platformer.coop.entities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Player extends GameEntity {

	private List<BufferedImage[]> sprites;
	private static final int[] NUMFRAMES = { 3, 3 };
	private static final int[] FRAMEWIDTHS = { 64, 64, 64 };
	private static final int[] FRAMEHEIGHTS = { 75, 75, 75 };
	private static final int[] SPRITETIMINGS = { 3, 3 };

	private static final int ANIMATION_MOVE_RIGHT = 0;
	private static final int ANIMATION_MOVE_LEFT = 1;

	public Player() {
		super();
		setMoveSpeedIncreaseRate(5);
		setMoveSpeedMax(15);
		setJumpSpeedIncrease(2);

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
		animation = new Animation();
		setAnimation(ANIMATION_MOVE_RIGHT);

	}

	public void update() {

		System.out.println(name + ": X " + x);
		System.out.println(name + ": Y " + y);

		if (isJumping()) {
			x = (int) Math.min(y + getJumpSpeedIncrease(), jumpMax);
		}
		if (isMovingRight) {
			moveSpeed = Math.max(moveSpeedMax, moveSpeed
					+ moveSpeedIncreaseRate);
			x += moveSpeed;
		}
		if (isMovingLeft) {
			x -= moveSpeed;
		}
	}

	private void setAnimation(int i) {
		currentAction = i;
		animation.setFrames(sprites.get(currentAction));
		animation.setFramesBetweenNextAnimation(SPRITETIMINGS[currentAction]);
		width = FRAMEWIDTHS[currentAction];
		height = FRAMEHEIGHTS[currentAction];
	}
}
