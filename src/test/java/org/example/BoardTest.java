package org.example;

import org.example.entity.JPAUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    /**
     * This test is designed to check to see if the health array corresponds correctly
     * to the health of the npcs.
     * @throws Exception
     */
    @Test
    void testNpcIDandHealthArray() throws Exception{
        // use the alternative constructor for board.
        Board board = new Board(1);

        ArrayList<ArrayList<Integer>> npcIDArray = board.generateRoomNPCIDs();
        ArrayList<ArrayList<Integer>> npcHealthArray = board.generateNPCHealth();

        for (int room = 0; room < npcIDArray.size(); room++) {
            for (int npcNumber = 0; npcNumber < npcIDArray.get(room).size(); npcNumber++) {
                int npcID = npcIDArray.get(room).get(npcNumber);
                int expectedHealth = JPAUtil.getNPC(npcID).getHealthValue();
                int actualHealth = npcHealthArray.get(room).get(npcNumber);
                assertEquals(expectedHealth,actualHealth);
            }
        }
    }

    /**
     * This tests that when Npc health is correctly reduced when the function setElementCorrespondingNPCHealth
     * is called
     * @throws Exception
     */
    @Test
    void testSetCorrespondingNPCHealth() throws Exception {
        // generate a normal board
        Board board = new Board();

        for (int room = 0; room < board.getRoomNpcIds().size(); room++) {
            for (int npcNumber = 0; npcNumber < board.getRoomNpcIds().get(room).size(); npcNumber++) {

                int npcID = board.getRoomNpcIds().get(room).get(npcNumber);
                // subtract 2 from the health of the npc's database entry
                int expectedHealth = JPAUtil.getNPC(npcID).getHealthValue() - 2;
                //get the current health
                int preHitHealth = board.getCorrespondingNPCHealth().get(room).get(npcNumber);

                board.setElementCorrespondingNPCHealth(preHitHealth - 2, npcNumber, room+1);

                //get the current health
                int postHitHealth = board.getCorrespondingNPCHealth().get(room).get(npcNumber);

                assertEquals(expectedHealth,postHitHealth);
            }
        }
    }


}
