package org.example;

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
}
