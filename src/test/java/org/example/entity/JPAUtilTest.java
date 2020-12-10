package org.example.entity;

import org.example.Game;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Tests to check that the JPAUtil class is working as expected.
 */
public class JPAUtilTest {
    @Test
    void testGetRoom() throws Exception{
        assertEquals("Wizard Room", JPAUtil.getRoom(1).getName());
        assertEquals("Goblin Room", JPAUtil.getRoom(2).getName());
        assertEquals("Hooded Room", JPAUtil.getRoom(3).getName());
        assertEquals("Witch Room", JPAUtil.getRoom(4).getName());
        assertEquals("Dwarf Room", JPAUtil.getRoom(5).getName());
        assertEquals("Tabaxi Room", JPAUtil.getRoom(6).getName());
        assertEquals("Boss Room", JPAUtil.getRoom(7).getName());
    }

    @Test
    void testGetListOfRooms() throws Exception{
        // geenrate array of room names
        ArrayList<String> roomNames = new ArrayList<>();
        roomNames.add("Wizard Room");
        roomNames.add("Goblin Room");
        roomNames.add("Hooded Room");
        roomNames.add("Witch Room");
        roomNames.add("Dwarf Room");
        roomNames.add("Tabaxi Room");
        roomNames.add("Boss Room");
        Object[] expected = roomNames.toArray();

        Object[] input = JPAUtil.getListOfRooms().stream().map(room -> room.getName()).toArray();

        assertArrayEquals(expected,input);
    }

    @Test
    void testGetNPC() throws Exception{
        assertEquals("Scruffy Wizard", JPAUtil.getNPC(1).getName());
        assertEquals("Hooded Figure", JPAUtil.getNPC(2).getName());
        assertEquals("Old Witch", JPAUtil.getNPC(3).getName());
        assertEquals("Goblin", JPAUtil.getNPC(4).getName());
        assertEquals("Demonic Dwarf", JPAUtil.getNPC(5).getName());
        assertEquals("Tabaxi Female", JPAUtil.getNPC(6).getName());
        assertEquals("Skeleton", JPAUtil.getNPC(7).getName());
        assertEquals("Knight", JPAUtil.getNPC(8).getName());
        assertEquals("Black Furry Creature", JPAUtil.getNPC(9).getName());
    }

    @Test
    void testGetItem() throws Exception{
        assertEquals("Black Scythe", JPAUtil.getItem(1).getName());
        assertEquals("Rusted Flail", JPAUtil.getItem(2).getName());
        assertEquals("Magical Fists", JPAUtil.getItem(3).getName());
        assertEquals("Machete", JPAUtil.getItem(4).getName());
        assertEquals("Katana", JPAUtil.getItem(5).getName());
        assertEquals("Fire Bolt Spell", JPAUtil.getItem(6).getName());
        assertEquals("Fire Blast Spell", JPAUtil.getItem(7).getName());
        assertEquals("Fire Ball Spell", JPAUtil.getItem(8).getName());
        assertEquals("Long Wooden Staff", JPAUtil.getItem(9).getName());
        assertEquals("Aging Spell", JPAUtil.getItem(10).getName());
    }

    @Test
    void testGetChest() throws Exception{
        assertEquals("Wooden Barrel", JPAUtil.getChest(1).getName());
        assertEquals("Pile of broken shipping crates", JPAUtil.getChest(2).getName());
        assertEquals("Wooden Chest", JPAUtil.getChest(3).getName());
        assertEquals("Wooden Barrel", JPAUtil.getChest(4).getName());
        assertEquals("Pile of broken shipping crates", JPAUtil.getChest(5).getName());
        assertEquals("Wooden Chest", JPAUtil.getChest(6).getName());
    }


    @Test
    void testScores() throws Exception{
        // enter a new score
        JPAUtil.setScore("FinchleyH", 10);

        // get the score array
        ArrayList<Score> scores = JPAUtil.getScore();
        int indexOfAddedScore = -1;
        for (int i = 0; i < scores.size(); i++) {
            if(scores.get(i).getPlayerName().equals("FinchleyH")){
                indexOfAddedScore = i;
                break;
            }
        }
        assertEquals(10, scores.get(indexOfAddedScore).getScore());

        // enter a new score
        JPAUtil.setScore("Elliot", 100);

        // get the score array
        scores = JPAUtil.getScore();
        indexOfAddedScore = -1;
        for (int i = 0; i < scores.size(); i++) {
            if(scores.get(i).getPlayerName().equals("Elliot")){
                indexOfAddedScore = i;
                break;
            }
        }
        assertEquals(100, scores.get(indexOfAddedScore).getScore());

        // enter a new score
        JPAUtil.setScore("Harley", -20);

        // get the score array
        scores = JPAUtil.getScore();
        indexOfAddedScore = -1;
        for (int i = 0; i < scores.size(); i++) {
            if(scores.get(i).getPlayerName().equals("Harley")){
                indexOfAddedScore = i;
                break;
            }
        }
        assertEquals(-20, scores.get(indexOfAddedScore).getScore());

        // enter a new score
        JPAUtil.setScore("Adam", 10000000);

        // get the score array
        scores = JPAUtil.getScore();
        indexOfAddedScore = -1;
        for (int i = 0; i < scores.size(); i++) {
            if(scores.get(i).getPlayerName().equals("Adam")){
                indexOfAddedScore = i;
                break;
            }
        }
        assertEquals(10000000, scores.get(indexOfAddedScore).getScore());
    }

    @Test
    void testGetNumberOfEntries() throws Exception {
        assertEquals(7, JPAUtil.getNoOfEntries("Room"));
        assertEquals(9, JPAUtil.getNoOfEntries("NPC"));
        assertEquals(10, JPAUtil.getNoOfEntries("Item"));
        assertEquals(6, JPAUtil.getNoOfEntries("Chest"));
    }


}
