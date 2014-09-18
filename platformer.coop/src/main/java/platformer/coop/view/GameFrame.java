package platformer.coop.view;

import javax.swing.JFrame;

import platformer.coop.util.Clock;
import platformer.coop.util.ClockListener;

public class GameFrame extends JFrame implements ClockListener
{
	private Clock clock;

	public GameFrame(String title)
	{
		super(title);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	public Clock getClock()
	{
		return clock;
	}

	public void setClock(Clock clk)
	{
		if (this.clock != null)
		{
			this.clock.removeClockListener(this);
		}

		this.clock = clk;
		this.clock.addClockListener(this);
	}

	@Override
	public void tick()
	{
		repaint();
	}

}
