package monopoly;

/*
 * Team: Group 4
 * Members: Korbin Ordiway, Ian Schoell, Kathleen Monahan, Marcos Elgueta
 * CS2430-002
 * Project 4
 * 
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for GameDriver's output formatting and simulation logic.
 */
public class GameDriverTest {

    // printResults() tests (testable now, only needs Results)

    @Test
    public void testPrintResultsDoesNotThrow() {
        int[] counts = new int[40];
        counts[0] = 100;
        counts[24] = 350; // Illinois Ave
        Results r = new Results(1000, "Strategy A", counts);
        assertDoesNotThrow(() -> GameDriver.printResults(r));
    }

    @Test
    public void testPrintResultsZeroTurns() {
        // make sure we don't get a divide by zero when turns is 0
        int[] counts = new int[40];
        Results r = new Results(0, "Strategy B", counts);
        assertDoesNotThrow(() -> GameDriver.printResults(r));
    }

    @Test
    public void testPrintTopSquaresDoesNotThrow() {
        int[] counts = new int[40];
        for (int i = 0; i < 40; i++) counts[i] = i * 10;
        Results r = new Results(7800, "Strategy B", counts);
        assertDoesNotThrow(() -> GameDriver.printTopSquares(r, 1));
    }

    @Test
    public void testPercentageCalculation() {
        // verify the percentage math is correct independently of printing
        int[] counts = new int[40];
        counts[0] = 500;
        Results r = new Results(1000, "Strategy A", counts);
        double pct = r.landingCounts()[0] * 100.0 / r.turns();
        assertEquals(50.0, pct, 0.001);
    }

    // runSimulation() tests are commented out because they need to be implemented.

    /*
     * Uncomment these once Board is fixed, turnEngine.takeTurn() is implemented,
     * and JailStrategy has a type constructor and getName().
     *
     * @Test
     * public void testRunSimulationReturnsTurns() {
     *     JailStrategy j = new JailStrategy("A");
     *     Results r = GameDriver.runSimulation(1000, j);
     *     assertEquals(1000, r.turns());
     * }
     *
     * @Test
     * public void testRunSimulationLandingCountsSumToTurns() {
     *     JailStrategy j = new JailStrategy("A");
     *     Results r = GameDriver.runSimulation(1000, j);
     *     int total = 0;
     *     for (int c : r.landingCounts()) total += c;
     *     // Each turn lands on exactly one square
     *     assertEquals(r.turns(), total,
     *             "Sum of all landing counts must equal the number of turns");
     * }
     *
     * @Test
     * public void testRunSimulationReturnsCorrectStrategyName() {
     *     JailStrategy jA = new JailStrategy("A");
     *     Results rA = GameDriver.runSimulation(1000, jA);
     *     assertEquals("Strategy A", rA.strategyName());
     *
     *     JailStrategy jB = new JailStrategy("B");
     *     Results rB = GameDriver.runSimulation(1000, jB);
     *     assertEquals("Strategy B", rB.strategyName());
     * }
     *
     * @Test
     * public void testRunSimulationResetsBoard() {
     *     JailStrategy j = new JailStrategy("A");
     *     Results r1 = GameDriver.runSimulation(100, j);
     *     Results r2 = GameDriver.runSimulation(100, j);
     *     // Each independent run should reflect exactly 100 turns, not 200
     *     assertEquals(100, r2.turns());
     *     int total = 0;
     *     for (int c : r2.landingCounts()) total += c;
     *     assertEquals(100, total);
     * }
     *
     * @Test
     * public void testGoToJailSquareCountIsZero() {
     *     // Square 30 is "Go To Jail" — the player is moved immediately, so it
     *     // should never be counted as a landing square.
     *     JailStrategy j = new JailStrategy("A");
     *     Results r = GameDriver.runSimulation(1000000, j);
     *     assertEquals(0, r.landingCounts()[30],
     *             "Go To Jail (index 30) should have 0 landings — player is redirected");
     * }
     *
     * @Test
     * public void testJailSquareHasHighCount() {
     *     // Jail (index 10) is widely reported as the most-landed square.
     *     // After 1M turns it should appear significantly above the uniform baseline (2.5%).
     *     JailStrategy j = new JailStrategy("A");
     *     Results r = GameDriver.runSimulation(1000000, j);
     *     double jailPct = r.landingCounts()[10] * 100.0 / r.turns();
     *     assertTrue(jailPct > 4.0,
     *             "Jail should be landed on more than 4% of turns; got " + jailPct + "%");
     * }
     */
}
