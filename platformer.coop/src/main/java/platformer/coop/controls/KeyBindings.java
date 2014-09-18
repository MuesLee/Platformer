package platformer.coop.controls;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import platformer.coop.entities.Player;

public class KeyBindings
{

	private Player playerOne = null;
	private Player playerTwo = null;

	public KeyBindings(JComponent gp)
	{
		// PLAYER TWO

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false),
			"W pressed");
		gp.getActionMap().put("W pressed", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				playerTwo.setJumping(true);
			}
		});

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false),
			"S pressed");
		gp.getActionMap().put("S pressed", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				playerTwo.setCrouching(true);
			}
		});
		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false),
			"A pressed");
		gp.getActionMap().put("A pressed", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				playerTwo.setMovingLeft(true);
			}
		});
		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false),
			"D pressed");
		gp.getActionMap().put("D pressed", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				playerTwo.setMovingRight(true);
			}
		});

		// PLAYER ONE

		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false),
			"down pressed");
		gp.getActionMap().put("down pressed", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				playerOne.setCrouching(true);
			}
		});
		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false),
			"up pressed");
		gp.getActionMap().put("up pressed", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				playerOne.setJumping(true);
			}
		});
		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false),
			"left pressed");
		gp.getActionMap().put("left pressed", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				playerOne.setMovingLeft(true);
			}
		});
		gp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false),
			"right pressed");
		gp.getActionMap().put("right pressed", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				playerOne.setMovingRight(true);
			}
		});
	}

	public Player getPlayerOne()
	{
		return playerOne;
	}

	public void setPlayerOne(Player playerOne)
	{
		this.playerOne = playerOne;
	}

	public Player getPlayerTwo()
	{
		return playerTwo;
	}

	public void setPlayerTwo(Player playerTwo)
	{
		this.playerTwo = playerTwo;
	}
}