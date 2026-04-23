package monopoly;

/*
 * Team: Group 4
 * Members: Korbin Ordiway, Ian Schoell, Kathleen Monahan, Marcos Elgueta
 * CS2430-002
 * Project 4
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Results record.
 * Results is a pure data container with no external dependencies,
 * so all tests here are self contained and should pass immediately.
 */
public class ResultsTest {

    @Test
    public void testConstructorAndAccessors() {
        int[] counts = new int[40];
        counts[0] = 5;
        counts[39] = 12;

        Results r = new Results(1000, "Strategy A", counts);

        assertEquals(1000, r.turns());
        assertEquals("Strategy A", r.strategyName());
        assertArrayEquals(counts, r.landingCounts());
    }

    @Test
    public void testLandingCountsLength() {
        int[] counts = new int[40];
        Results r = new Results(500, "Strategy B", counts);
        assertEquals(40, r.landingCounts().length);
    }

    @Test
    public void testZeroTurns() {
        int[] counts = new int[40];
        Results r = new Results(0, "Strategy A", counts);
        assertEquals(0, r.turns());
        // All counts should remain zero
        for (int c : r.landingCounts()) {
            assertEquals(0, c);
        }
    }

    @Test
    public void testStrategyNamePreserved() {
        int[] counts = new int[40];
        Results rA = new Results(1000, "Strategy A", counts);
        Results rB = new Results(1000, "Strategy B", counts);

        assertEquals("Strategy A", rA.strategyName());
        assertEquals("Strategy B", rB.strategyName());
        assertNotEquals(rA.strategyName(), rB.strategyName());
    }

    @Test
    public void testLargeN() {
        int[] counts = new int[40];
        counts[24] = 1000000; // Illinois Ave — known high-frequency square
        Results r = new Results(1000000, "Strategy A", counts);

        assertEquals(1000000, r.turns());
        assertEquals(1000000, r.landingCounts()[24]);
    }
}
