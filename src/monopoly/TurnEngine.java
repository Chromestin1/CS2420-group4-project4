package monopoly;

import java.util.Random;

public class TurnEngine {

	private int die1;
	private int die2;
	private Deck deck;
	private boolean inJail;
	private int consectiveDoubles;
	private Random random = new Random();

	public int getDie1() {
		return die1;
	}

	public int getDie2() {
		return die2;
	}

	private int jailCardCount;
	private int playerPosition;

	/*
	 * constructor
	 */
	public TurnEngine() {
		random = new Random();
		deck = new Deck();
		inJail = false;
		consectiveDoubles = 0;
		jailCardCount = 0;
		playerPosition = 0;
	}

	/*
	 * go to jail
	 */
	public void goToJail() {
		inJail = true;
		playerPosition = 10;
	}

	/*
	 * roll dice
	 */
	public void rollDice() {
		die1 = random.nextInt(6) + 1;
		die2 = random.nextInt(6) + 1;
	}

	/*
	 * Checks if both dice have doubles
	 */
	public boolean isDoubles() {
		return die1 == die2;
	}

	/*
	 * checks if the player is in Jail
	 * 
	 * @return true if the player is in jail; false otherwise
	 */
	public boolean isInJail() {
		return inJail;
	}

	/*
	 * Update whether the player is in jail
	 * 
	 * @param inJail the new jail state
	 */
	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}

	/*
	 * Returns the number of Get Out of Jail Free cards the player has.
	 * 
	 * @return the jail card card count
	 */
	public int getJailCardCount() {
		return jailCardCount;
	}

	/*
	 * Uses one Get Out Of Jail Free card if available
	 * 
	 * @throws IllegalStateException if the player has no jail card to use
	 */
	public void useJailCard() {
		if (jailCardCount <= 0)
			throw new IllegalStateException("No Get Out of Jail Free card available");

		jailCardCount--;
	}

	public void addJailCard() {
		jailCardCount++;
	}

	public int getPLayerPosition() {
		return playerPosition;
	}

	public void setPlayerPosition(int playerPosition) {
		this.playerPosition = playerPosition;
	}

	/*
	 * Nearest Railroad
	 */
	private int nearestRailroad(int position) {
		if (position < 5 || position >= 35)
			return 5;
		else if (position < 15)
			return 15;
		else if (position < 25)
			return 25;
		else
			return 35;
	}

	/*
	 * Nearest Utility
	 */
	private int nearestUtility(int position) {
		if (position < 12 || position >= 28)
			return 12;
		else
			return 28;
	}

	/*
	 * Simulates one complete player turn in Monopoly
	 */
	public void takeTurn(JailStrategy j) {
		boolean takeAnotherTurn;
		int moveAmount;

		if (inJail) { // If the player starts the turn in jail, apply the selected jail strategy
			if (j.getName().equals("Strategy A")) {
				j.strategyA(this);
			} else {
				j.strategyB(this);
			}

			if (isInJail()) // If the player still in jail after the strategy, the turns ends
				return;
		}

		consectiveDoubles = 0; // Resets the count of consecutive doubles at the start of the turn

		do {
			rollDice(); // roll dice

			if (isDoubles()) // Checks if the roll is doubles
				consectiveDoubles++;
			else
				consectiveDoubles = 0;

			if (consectiveDoubles == 3) { // If the player rolls double three times in a row, send them to jail
				goToJail();
				return;
			}

			moveAmount = die1 + die2;
			playerPosition = (playerPosition + moveAmount) % 40;
			resolveSquare();
			Board.land(playerPosition);

			takeAnotherTurn = isDoubles() && !isInJail(); // Player gets another turn only if they roll doubles and they
															// were not sent to jail

		} while (takeAnotherTurn);
	}

	/*
	 * Resolve square method
	 */
	public void resolveSquare() {
		boolean moved;
		String square;
		int oldPosition;
		String card;

		do {
			moved = false;
			square = Board.getSquare(playerPosition);

			if (square.equals("Go To Jail"))
				goToJail(); // Go to jail
			else if (square.equals("Chance")) {
				oldPosition = playerPosition;
				card = deck.drawChance(); // Call deck logic

				if (card.equals("Advance to GO")) // Card effects
					playerPosition = 0;
				else if (card.equals("Advance to Illinois Avenue"))
					playerPosition = 24;
				else if (card.equals("Advance to St. Charles Place"))
					playerPosition = 11;
				else if (card.equals("Advance to nearest Utility"))
					playerPosition = nearestUtility(playerPosition);
				else if (card.equals("Advance to nearest Railroad"))
					playerPosition = nearestRailroad(playerPosition);
				else if (card.equals("Go to Jail")) {
					goToJail();
					return;
				} else if (card.equals("Take a trip to Reading Railroad"))
					playerPosition = 5;
				else if (card.equals("Take a walk on Boardwalk"))
					playerPosition = 39;
				else if (card.equals("Get Out of Jail Free"))
					addJailCard();
				else if (card.equals("Go Back 3 Spaces"))
					playerPosition = (playerPosition + 37) % 40;

				if (playerPosition != oldPosition)
					moved = true;

			} else if (square.equals("Community Chest")) {
				oldPosition = playerPosition;
				card = deck.drawCommChest(); // Call deck logic

				if (card.equals("Advance to GO"))
					playerPosition = 0;
				else if (card.equals("Go to Jail")) {
					goToJail();
					return;
				} else if (card.equals("Get Out of Jail Free"))
					addJailCard();

				if (playerPosition != oldPosition)
					moved = true;
			}
		} while (moved);

	}

}
