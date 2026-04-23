package monopoly;

/*
 * Team: Group 4
 * Members: Korbin Ordiway, Ian Schoell, Kathleen Monahan, Marcos Elgueta
 * CS2430-002
 * Project 4
 *
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

/**
 * Tests for Board's land() and restartGame() methods.
 */
public class BoardTest {

    @BeforeEach
    public void resetBoard() {
        // Reset state before every test so tests are independent
        Board.restartGame();
    }

    @Test
    public void testLandIncrementsCount() {
        Board.land(0); // Go
        HashMap<String, Integer> counts = Board.getLandingCounts();
        assertTrue(counts.getOrDefault("Go", 0) >= 1,
                "Landing on index 0 should increment the Go counter");
    }

    @Test
    public void testLandMultipleTimes() {
        Board.land(39); // Boardwalk
        Board.land(39);
        Board.land(39);
        HashMap<String, Integer> counts = Board.getLandingCounts();
        assertEquals(3, counts.getOrDefault("Boardwalk", 0));
    }

    @Test
    public void testRestartGameResetsCount() {
        Board.land(0);
        Board.land(0);
        Board.restartGame();
        HashMap<String, Integer> counts = Board.getLandingCounts();
        assertEquals(0, counts.getOrDefault("Go", 0),
                "restartGame() should reset all landing counts to zero");
    }

    @Test
    public void testLandValidIndices() {
        // Verify land() accepts all 40 valid board indices without throwing
        assertDoesNotThrow(() -> {
            for (int i = 0; i < 40; i++) {
                Board.land(i);
            }
        });
    }

    @Test
    public void testMultipleSquaresIndependent() {
        Board.land(0);  // Go
        Board.land(39); // Boardwalk
        Board.land(39); // Boardwalk again
        HashMap<String, Integer> counts = Board.getLandingCounts();
        assertEquals(1, counts.getOrDefault("Go", 0));
        assertEquals(2, counts.getOrDefault("Boardwalk", 0));
    }
}
