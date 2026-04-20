package monopoly;
	/**
	 * Handles the two different strategies for getting out of jail in game play
	 * 
	 * @author KatM
	 */

	//TurnEngine must implement these for this class to work: getJailCardCount(), setInJail(), useJailCard(), isDoubles(), rollDice()
	public class JailStrategy {

		/*
		 * TODO
		 */
		private int doublesAttempts;

		/**
		 * If the player has a get out jail free card, use it. Otherwise assume paid $50
		 * and exit
		 * 
		 * @param engine
		 */
		
		public void strategyA(TurnEngine engine) {

			if (engine.getJailCardCount() > 0) {
				engine.setInJail(false);
				engine.useJailCard(); // a method that should return the card to Deck
			} else
				engine.setInJail(false);
			// player still needs to take their turn after exiting jail - handled by
			// TurnEngine?
		}
		
		/*
		 * TODO

		/**
		 * If the player has a get out jail free card, use it. Otherwise must roll
		 * doubles to get out If after 3 rolls there are no doubles, exit.
		 * 
		 * @param engine
		 */
		
		public void strategyB(TurnEngine engine) {

			if (engine.getJailCardCount() > 0) {
				engine.setInJail(false);
				engine.useJailCard(); // a method that should return the card to Deck
			} else {
				// player exits after rolling doubles OR after 3 failed attempts (exits on 4th
				// turn)
				while (!engine.isDoubles() && doublesAttempts < 3) {
					engine.rollDice();
					doublesAttempts++;
				}
				engine.setInJail(false);
				doublesAttempts = 0;

			}

		}
		

		/*
		 * TODO
		 */
}