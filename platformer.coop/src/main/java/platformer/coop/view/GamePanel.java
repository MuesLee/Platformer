package platformer.coop.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import platformer.coop.controller.GameController;
import platformer.coop.controller.GameStateManager;

public class GamePanel extends JPanel
{
	private Image backBuffer;
	private Graphics bBG;

	private int width = 640;
	private int height = 320;

	private GameStateManager gameStateManager;

	public GamePanel(GameStateManager gameStateManager)
	{
		super();
		setPreferredSize(new Dimension(width, height));
		this.setGameStateManager(gameStateManager);
		this.setBackground(Color.RED);
		this.setVisible(true);

	}

	@Override
	protected void paintComponent(Graphics g)
	{

		if (backBuffer == null)
		{
			backBuffer = createImage(getWidth(), getHeight());
			backBuffer.setAccelerationPriority(1f);
			bBG = backBuffer.getGraphics();
		}
		Graphics2D g2d = (Graphics2D) backBuffer.getGraphics();

		paintGameState(bBG);

		g.drawImage(backBuffer, 0, 0, this);
	}

	private void paintGameState(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		//clear screen
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		BufferedImage image = new BufferedImage(GameController.WIDTH, GameController.HEIGHT, BufferedImage.TYPE_INT_RGB);
		image.setAccelerationPriority(0.9f);

		Graphics2D paintedArea = (Graphics2D) image.getGraphics();
		gameStateManager.draw(paintedArea);
		paintedArea.dispose();
		g2d.drawImage(image, 0, 0, this);

	}

	public GameStateManager getGameStateManager()
	{
		return gameStateManager;
	}

	public void setGameStateManager(GameStateManager gameStateManager)
	{
		this.gameStateManager = gameStateManager;
	}
}