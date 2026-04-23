package monopoly;

/*
 * Team: Group 4
 * Members: Korbin Ordiway, Ian Schoell, Kathleen Monahan, Marcos Elgueta
 * CS2430-002
 * Project 4
 * 
 *
 * NOTE: this won't fully run until teammates finish these:
 *   - turnEngine needs a takeTurn(JailStrategy) method
 *   - JailStrategy needs a constructor that takes "A" or "B" and a getName() method
 *   - Board.landingCounts needs to be initialized (it's null right now and will crash)
 */

import java.util.HashMap;

/**
 * Entry point and simulation controller for the Monopoly probability simulator.
 * Runs 10 independent simulations for each combination of jail-exit strategy
 * and turn count, then prints landing frequencies per square.
 */
public class GameDriver {

    /*
     * Square names in board order (indices 0-39).
     * Kept here for output formatting since Board.boardSquares is private.
     */
    private static final String[] BOARD_SQUARES = {
        "Go", "Mediterranean Avenue", "Community Chest", "Baltic Avenue",
        "Income Tax", "Reading Railroad", "Oriental Avenue", "Chance",
        "Vermont Avenue", "Connecticut Avenue", "Just Visiting/Jail",
        "St. Charles Place", "Electric Company", "States Avenue",
        "Virginia Avenue", "Pennsylvania Railroad", "St. James Place",
        "Community Chest", "Tennessee Avenue", "New York Avenue",
        "Free Parking", "Kentucky Avenue", "Chance", "Indiana Avenue",
        "Illinois Avenue", "B&O Railroad", "Atlantic Avenue",
        "Ventnor Avenue", "Water Works", "Marvin Gardens", "Go To Jail",
        "Pacific Avenue", "North Carolina Avenue", "Community Chest",
        "Pennsylvania Avenue", "Short Line Railroad", "Chance",
        "Park Place", "Luxury Tax", "Boardwalk"
    };

    /**
     * Runs a single simulation of n turns using the given jail strategy.
     * Resets the board before each simulation so runs are independent.
     *
     * @param n the number of turns to simulate
     * @param j the JailStrategy controlling how the player exits jail
     * @return a Results record with the turn count, strategy name, and per-square landing counts
     */
    public static Results runSimulation(int n, JailStrategy j) {
        Board.restartGame();
        turnEngine engine = new turnEngine();

        for (int i = 0; i < n; i++) {
            engine.takeTurn(j);
        }

        // convert Board's HashMap to an int[] in board order for the Results record
        HashMap<String, Integer> counts = Board.getLandingCounts();
        int[] landingCounts = new int[40];
        for (int i = 0; i < BOARD_SQUARES.length; i++) {
            landingCounts[i] = counts.getOrDefault(BOARD_SQUARES[i], 0);
        }

        return new Results(n, j.getName(), landingCounts);
    }

    /**
     * Prints a formatted table showing landing count and percentage for each square.
     *
     * @param r the Results record to display
     */
    public static void printResults(Results r) {
        System.out.printf("%nStrategy: %s | Turns: %,d%n", r.strategyName(), r.turns());
        System.out.printf("%-5s %-30s %10s %12s%n", "Idx", "Square", "Landings", "Percentage");
        System.out.println("-".repeat(60));
        for (int i = 0; i < BOARD_SQUARES.length; i++) {
            double pct = r.turns() > 0 ? r.landingCounts()[i] * 100.0 / r.turns() : 0.0;
            System.out.printf("%-5d %-30s %10d %11.4f%%%n",
                    i, BOARD_SQUARES[i], r.landingCounts()[i], pct);
        }
    }

    /**
     * Prints the top 5 most-landed squares from a single simulation result.
     * Used to quickly compare convergence across runs.
     *
     * @param r      the Results record to summarize
     * @param runNum the run number label (1-10) for display
     */
    public static void printTopSquares(Results r, int runNum) {
        // find the top 5 squares by scanning through all 40 each time
        boolean[] used = new boolean[40];
        System.out.printf("  Run %2d top 5:", runNum);
        for (int rank = 0; rank < 5; rank++) {
            int best = -1;
            for (int i = 0; i < 40; i++) {
                if (!used[i] && (best == -1 || r.landingCounts()[i] > r.landingCounts()[best])) {
                    best = i;
                }
            }
            used[best] = true;
            System.out.printf("  [%d] %s (%.2f%%)",
                    best, BOARD_SQUARES[best],
                    r.landingCounts()[best] * 100.0 / r.turns());
        }
        System.out.println();
    }

    /**
     * Runs all 80 required simulations:
     *   2 strategies × 4 turn counts × 10 independent runs.
     * Prints the full landing table for the final run of each (strategy, n) pair,
     * and prints the top-5 summary for all 10 runs to show convergence.
     */
    public static void main(String[] args) {
        int[] turnCounts = {1000, 10000, 100000, 1000000};

        /*
         * Requires JailStrategy to accept a type string ("A" or "B") in its constructor
         * and to implement getName(). Teammates must add these before main() will compile.
         */
        JailStrategy[] strategies = {
            new JailStrategy("A"),
            new JailStrategy("B")
        };

        for (JailStrategy strategy : strategies) {
            System.out.println("\n" + "=".repeat(62));
            System.out.println("RUNNING: " + strategy.getName());
            System.out.println("=".repeat(62));

            for (int n : turnCounts) {
                System.out.printf("%n--- %s | n = %,d ---%n", strategy.getName(), n);

                Results[] runs = new Results[10];
                for (int run = 0; run < 10; run++) {
                    runs[run] = runSimulation(n, strategy);
                }

                // Full table for the last run so the report has a clean dataset
                printResults(runs[9]);

                // Top-5 across all 10 runs to visualize convergence
                System.out.printf("%nTop-5 convergence summary (all 10 runs, n=%,d):%n", n);
                for (int run = 0; run < 10; run++) {
                    printTopSquares(runs[run], run + 1);
                }
            }
        }
    }
}
