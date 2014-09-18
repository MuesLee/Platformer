package platformer.coop.gamestates;

public enum GameStates {

	MENU_STATE(0), LEVEL_1(1);

	private int stateNumber;

	private GameStates(int stateNumber)
	{
		this.setStateNumber(stateNumber);
	}

	public int getStateNumber()
	{
		return stateNumber;
	}

	public void setStateNumber(int stateNumber)
	{
		this.stateNumber = stateNumber;
	}

	public static GameStates getStateByNumber(int number)
	{
		GameStates result = null;

		GameStates[] values = GameStates.values();
		for (int i = 0; i < values.length; i++)
		{
			GameStates t = values[i];

			if (number == t.getStateNumber())
			{
				result = t;
				break;
			}
		}

		return result;
	}

}
