package monopoly;

import java.util.Stack;

public class Deck {
	private Stack<String> chanceCard;
	private Stack<String> commChestCards;
	
	/*
	 * Constructs both decks and fills them with shuffled cards.
	 */
	public Deck() {
		chanceCard = new Stack<String>();
		commChestCards = new Stack<String>();
		
		resetChanceDeck();
		resetCommChestDeck();
	}
	
	/*
	 * Draws the top Chance card.
	 */
	public String drawChance() {
		if (chanceCard.isEmpty())
			resetChanceDeck();
		
		return chanceCard.pop();
		
	}
	
	/*
	 * Draws the community Card
	 */
	public String drawCommChest() {
		if (commChestCards.isEmpty())
			resetCommChestDeck();
		
		return commChestCards.pop();
		
	}
	
	/*
	 * Rebuilds and shuffle the Chance deck
	 */
	private void resetChanceDeck() {
		String[] cards = {
				"Advance to GO",
				"Advance to Illinois Avenue",
				"Advance to St. Charles Place",
				"Advance to nearest Utility",
				"Advance to nearest Railroad",
				"Advance to nearest Railroad",
				"Bank pays you dividend of $50",
				"Get Out of Jail Free",
				"Go to Jail",
				"Make general repairs",
				"Take a trip to Reading Railroad",
				"Take a walk on Boardwalk",
				"You have been elected Chairman",
				"Your building loan matures",
				"Crossword competition",
				"Go Back 3 Spaces"
		};
		
		shuffle(cards);
		chanceCard.clear();
		
		for (String card : cards) {
			chanceCard.push(card);
		}
		
	}
	
	/*
	 * Rebuilds and shuffle the Community Chest deck
	 */
	private void resetCommChestDeck() {
		String[] cards = {
				"Advance to GO",
				"Bank error in your favor",
				"Doctor's fee",
				"From sale of stock you get $50",
				"Get Out of Jail Free",
				"Go to Jail",
				"Holiday fund matures",
				"Income tax refund",
				"It is your birthday",
				"Life insurance matures",
				"Hospital fees",
				"School fees",
				"Receive $25 consultancy fee",
				"You are assessed for Street repair",
				"You have won second prize in a beauty contest",
				"You inherit $100"
		};
		
		shuffle(cards);
		commChestCards.clear();
		
		for (String card : cards) {
			commChestCards.push(card);
		}
	}
	
	public void shuffle(String[] a) {
		int n = a.length;
		for(int i = 0; i < n; i++) {
			int r = i + (int) (Math.random() * (n-i));
			exch(a, i, r);
		}
	}
	
	/*
	 * helper method for shuffle method
	 */
	public static void exch(String[] a, int i, int j) {
		String swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
}
