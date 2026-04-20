package monopoly;

import java.util.Random;

public class TurnEngine {

	private int die1;
	private int die2;
	private Board board;
	private Deck deck;
	private boolean inJail;
	private int consectiveDoubles;
	private Random random;
	private int jailCardCount;
	
	/*
	 * go to jail
	 */
	public void goToJail() {
		inJail = true;
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
	 * @return true if the player is in jail; false otherwise
	 */
	public boolean isInJail() {
		return inJail;
	}
	
	/*
	 * Update whether the player is in jail
	 * @param inJail the new jail state
	 */
	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}
	
	/*
	 * Returns the number of Get Out of Jail Free cards the player has.
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
	
	
	/*
	 * Simulates one complete player turn in Monopoly
	 */
	public void takeTurn(JailStrategy j) {
		boolean takeAnotherTurn;
		
		if (inJail) {	//If the player starts the turn in jail, apply the selected jail strategy
			j.strategyB(this);
			
			if (isInJail())	//If the player still in jail after the strategy, the turns ends
				return;
		}
				
		consectiveDoubles = 0;	//Resets the count of consecutive doubles at the start of the turn
		
		do {
			rollDice();	//roll dice
			
			if(isDoubles())		//Checks if the roll is doubles
				consectiveDoubles++;
			else
				consectiveDoubles = 0;
			
			if (consectiveDoubles == 3) {	//If the player rolls double three times in a row, send them to jail
				goToJail();
				return;
			}
			
			
			//TODO: Move the player based on dice total (die1 + die2)
			
			//TODO: Resolve the square landed on (board effects, cards, etc.
			
			takeAnotherTurn = isDoubles() && !isInJail();	//Player gets another turn only if they roll doubles and they were not sent to jail
			
		} while(takeAnotherTurn);
	}
	
}
