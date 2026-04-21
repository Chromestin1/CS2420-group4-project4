package monopoly;

import java.util.Stack;

public class Deck {
	private Stack<String> chanceCard;
	private Stack<String> commChestCards;
	
	public String drawChance() {
		//TODO
		return null;
		
	}
	
	public String drawCommChest() {
		//TODO
		return null;
		
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
