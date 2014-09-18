package platformer.coop.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import platformer.coop.controller.GameController;
import platformer.coop.controller.GameStateManager;
import platformer.coop.tilemap.Background;

public class MenuState extends AbstractGameState
{

	private Font titleFont;
	private Font font;

	private Color titleColor;
	private Color color;

	private String[] menuItems = { "Start", "Exit" };

	private int selectedMenuItem = 0;

	public MenuState(GameStateManager manager)
	{
		super(manager);

		titleFont = new Font("Showcard Gothic", Font.BOLD, 64);
		font = new Font("Verdana", Font.PLAIN, 18);

		titleColor = Color.DARK_GRAY;
		color = Color.LIGHT_GRAY;
	}

	@Override
	public void update()
	{
		super.getBackground().update();

	}

	@Override
	public void draw(Graphics2D g)
	{
		super.getBackground().draw(g);

		drawTitle(g);
		drawMenu(g);

	}

	private void drawTitle(Graphics2D g)
	{
		g.setFont(titleFont);
		g.setColor(titleColor);
		g.drawString(GameController.GAME_TITLE, 10, 50);
	}

	private void drawMenu(Graphics2D g)
	{
		g.setFont(font);

		for (int i = 0; i < menuItems.length; i++)
		{
			g.setColor(color);

			if (i == selectedMenuItem)
			{
				g.setColor(Color.red);
			}

			g.drawString(menuItems[i], 100, 100 + i * 20);
		}
	}

	@Override
	public void init()
	{
		final Background background = new Background("menuBackground.jpg", 0.5);
		background.setVector(5, 0);
		setBackground(background);

	}
}
