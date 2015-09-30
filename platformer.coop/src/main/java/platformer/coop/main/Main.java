package platformer.coop.main;

import de.ts.gameengine.controller.GameController;
import platformer.coop.controller.MyGameController;
import platformer.coop.gamestates.MyGameStateManagerFactory;

public class Main
{
	public static void main(String[] args)
	{
		GameController controller = new MyGameController();
		controller.setGameStateManagerFactory(new MyGameStateManagerFactory());
		controller.init();
		Thread t1 = new Thread(controller);
		t1.start();
	}
}
