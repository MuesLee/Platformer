package platformer.coop.main;

import platformer.coop.controller.GameController;

public class Main
{
	public static void main(String[] args)
	{
		GameController controller = new GameController();
		Thread t1 = new Thread(controller);
		t1.start();
		controller.startGameloop();
	}
}
