package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test
    void testMoveValidate() throws Exception{
        Player player = new Player();
        assertEquals(true, player.moveValidate(Direction.S, 1));
        assertEquals(true, player.moveValidate(Direction.S, 2));
        assertEquals(false, player.moveValidate(Direction.S, 3));
        assertEquals(false, player.moveValidate(Direction.S, 4));
        assertEquals(false, player.moveValidate(Direction.S, 5));
        assertEquals(false, player.moveValidate(Direction.N, 1));
        assertEquals(false, player.moveValidate(Direction.N, 2));
        assertEquals(true, player.moveValidate(Direction.N, 3));
        assertEquals(true, player.moveValidate(Direction.N, 4));
        assertEquals(false, player.moveValidate(Direction.N, 5));
        assertEquals(true, player.moveValidate(Direction.E, 1));
        assertEquals(false, player.moveValidate(Direction.E, 2));
        assertEquals(true, player.moveValidate(Direction.E, 3));
        assertEquals(true, player.moveValidate(Direction.E, 4));
        assertEquals(false, player.moveValidate(Direction.E, 5));
        assertEquals(false, player.moveValidate(Direction.W, 1));
        assertEquals(true, player.moveValidate(Direction.W, 2));
        assertEquals(false, player.moveValidate(Direction.W, 3));
        assertEquals(true, player.moveValidate(Direction.W, 4));
        assertEquals(true, player.moveValidate(Direction.W, 5));
    }

    @Test
    void testIsIntInRange() throws Exception{
        Player player = new Player();
        assertEquals(1, player.isIntInRange("1", 2));
        assertEquals(1, player.isIntInRange("1", 1));
        assertEquals(-1, player.isIntInRange("3", 2));
        assertEquals(2, player.isIntInRange("2", 2));
        assertEquals(-1, player.isIntInRange("test", 2));
    }
}
