package org.example;

import org.example.entity.Item;
import org.example.entity.JPAUtil;
import org.example.entity.NPC;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    @Test
    void testVerifyNumbers() throws Exception{
        Game game = new Game();
        assertEquals(true, game.verifyIntegers(1,1,2,3));
        assertEquals(false, game.verifyIntegers(1,5,2,3,2));
        assertEquals(false, game.verifyIntegers(0,1,2,3));
        assertEquals(true, game.verifyIntegers(5,5,2,3,5));
        assertEquals(true, game.verifyIntegers(-5,-5,2,3,5));
        assertEquals(true, game.verifyIntegers(1,1,1,3));
        assertEquals(true, game.verifyIntegers(1,5,2,3,1));
        assertEquals(true, game.verifyIntegers(0,0,2,3));
        assertEquals(false, game.verifyIntegers(-5,5,2,3,5));
        assertEquals(false, game.verifyIntegers(-3,-5,2,3,5));
    }

    @Test
    void testIsIntInRange() throws Exception{
        Game game = new Game();
        assertEquals(1, game.isIntInRange("1", 2));
        assertEquals(1, game.isIntInRange("1", 1));
        assertEquals(-1, game.isIntInRange("3", 2));
        assertEquals(2, game.isIntInRange("2", 2));
        assertEquals(-1, game.isIntInRange("0", 2));
        assertEquals(-1, game.isIntInRange("test", 2));
        assertEquals(4, game.isIntInRange("4", 5));
        assertEquals(23, game.isIntInRange("23", 23));
        assertEquals(-1, game.isIntInRange("-5", -5));
        assertEquals(-1, game.isIntInRange("@", 2));
        assertEquals(-1, game.isIntInRange("5", 2));
        assertEquals(-1, game.isIntInRange("-abcdefg", 2));
    }

    @Test
    void testIsIntInRangeGame() throws Exception{
        Game game = new Game();
        assertEquals(1, game.isIntInRange("1", 2));
        assertEquals(1, game.isIntInRange("1", 1));
        assertEquals(-1, game.isIntInRange("3", 2));
        assertEquals(2, game.isIntInRange("2", 2));
        assertEquals(-1, game.isIntInRange("0", 2));
        assertEquals(-1, game.isIntInRange("test", 2));
    }
}
