package monopoly;

import java.util.HashMap;

public class Board {

	private static String [] boardSquares = {"Go", "Mediterranean Avenue", "Community Chest", "Baltic Avenue", "Income Tax", "Reading Railroad", 
			"Oriental Avenue", "Chance", "Vermont Avenue", "Connecticut Avenue", "Just Visiting/Jail", "St. Charles Place", 
			"Electric Company", "States Avenue", "Virginia Avenue", "Pennsylvania Railroad", "St. James Place", "Community Chest", 
			"Tennessee Avenue", "New York Avenue", "Free Parking", "Kentucky Avenue", "Chance", "Indiana Avenue", "Illinois Avenue",
			"B&O Railroad", "Atlantic Avenue", "Ventnor Avenue", "Water Works", "Marvin Gardens", "Go To Jail", "Pacific Avenue",
			"North Carolina Avenue", "Community Chest", "Pennsylvania Avenue", "Short Line Railroad", "Chance", "Park Place",
			"Luxury Tax", "Boardwalk"};

	private static HashMap<String, Integer> landingCounts = new HashMap<>(); 
	
	private static int totalMoves = 0;

	public static HashMap<String, Integer> getLandingCounts(){
		return landingCounts;
	}
	
	public static void land(int index) {
		String space = boardSquares[index];
		landingCounts.put(space, landingCounts.getOrDefault(space, 0) + 1);
		totalMoves ++;	
	}
	
	public static void restartGame() {
		totalMoves = 0;
		landingCounts.clear();
	}

	public static String getSquare(int index) {	//getSquare method to help with TurnEngine
		return boardSquares[index];
	}
}
