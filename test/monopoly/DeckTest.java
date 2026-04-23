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
import java.util.Arrays;

/**
 * Tests for Deck's shuffle and card draw behavior.
 */
public class DeckTest {

    // shuffle() tests (testable now)

    @Test
    public void testShufflePreservesLength() {
        Deck deck = new Deck();
        String[] cards = {"A", "B", "C", "D", "E"};
        int originalLength = cards.length;
        deck.shuffle(cards);
        assertEquals(originalLength, cards.length,
                "shuffle() must not change the array length");
    }

    @Test
    public void testShufflePreservesAllElements() {
        Deck deck = new Deck();
        String[] cards = {"Go", "Jail", "Boardwalk", "Illinois Ave", "B&O Railroad"};
        String[] original = cards.clone();
        deck.shuffle(cards);

        // Every original element must still be present after shuffle
        Arrays.sort(cards);
        Arrays.sort(original);
        assertArrayEquals(original, cards,
                "shuffle() must preserve all elements — no duplicates or deletions");
    }

    @Test
    public void testShuffleOnSingleElement() {
        Deck deck = new Deck();
        String[] cards = {"OnlyCard"};
        assertDoesNotThrow(() -> deck.shuffle(cards));
        assertEquals("OnlyCard", cards[0]);
    }

    @Test
    public void testShuffleOnEmptyArray() {
        Deck deck = new Deck();
        String[] cards = {};
        assertDoesNotThrow(() -> deck.shuffle(cards));
        assertEquals(0, cards.length);
    }

    @Test
    public void testExchSwapsElements() {
        String[] cards = {"First", "Second", "Third"};
        Deck.exch(cards, 0, 2);
        assertEquals("Third", cards[0]);
        assertEquals("Second", cards[1]);
        assertEquals("First", cards[2]);
    }

    @Test
    public void testExchSameIndex() {
        String[] cards = {"Alpha", "Beta"};
        Deck.exch(cards, 1, 1);
        assertEquals("Alpha", cards[0]);
        assertEquals("Beta", cards[1]);
    }

    // drawChance() / drawCommChest() tests are commented out because they are waiting for deck

    /*
     * Uncomment these once Deck loads its card lists and implements draw methods.
     *
     * @Test
     * public void testDrawChanceReturnsNonNull() {
     *     Deck deck = new Deck();
     *     assertNotNull(deck.drawChance(), "drawChance() must return a card string, not null");
     * }
     *
     * @Test
     * public void testDrawCommChestReturnsNonNull() {
     *     Deck deck = new Deck();
     *     assertNotNull(deck.drawCommChest());
     * }
     *
     * @Test
     * public void testChanceDeckCyclesAllCards() {
     *     // Draw all 16 Chance cards and verify the deck reshuffles when empty
     *     Deck deck = new Deck();
     *     for (int i = 0; i < 32; i++) {  // draw twice through the deck
     *         assertNotNull(deck.drawChance());
     *     }
     * }
     *
     * @Test
     * public void testCommChestDeckCyclesAllCards() {
     *     Deck deck = new Deck();
     *     for (int i = 0; i < 32; i++) {
     *         assertNotNull(deck.drawCommChest());
     *     }
     * }
     */
}
