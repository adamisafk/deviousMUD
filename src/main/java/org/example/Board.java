package org.example;

import org.example.entity.JPAUtil;
import org.example.entity.Room;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private Room[] roomArray;
    private ArrayList<ArrayList<Integer>> roomNpcIds;
    private ArrayList<ArrayList<Integer>> roomChestIds;

    public Board(){
        generateBoard();
        roomNpcIds = new ArrayList<ArrayList<Integer>>();
        roomChestIds = new ArrayList<ArrayList<Integer>>();
        generateRoomNPCIDs();
        generateRoomChestIDs();
    }

    /**
     * Function to generate the board.
     */
    public void generateBoard(){
        ArrayList<Room> roomsInDatabase = JPAUtil.getListOfRooms();
        this.roomArray = new Room[5];
        for (int i = 0; i < 5; i++) {
            roomArray[i] = (Room) roomsInDatabase.toArray()[i];
        }
    }

    public Room getRoomAtIndex(int i){
        return roomArray[i];
    }

    /**
     * Function to generate a random selection of NPC IDs. randomly generate between 1 and 3 npcs
     */
    public void generateRoomNPCIDs(){
        for (int i = 0; i < roomArray.length; i++) {
            // generate random number between 1 and 3
            int numNPCs = ThreadLocalRandom.current().nextInt(1, 3 + 1);
            // make this entry of the roomNPCid's be this length
            roomNpcIds.add(new ArrayList<>(numNPCs));
            for (int j = 0; j < numNPCs; j++) {
                roomNpcIds.get(i).add(ThreadLocalRandom.current().nextInt(1, JPAUtil.getNoOfEntries("NPC") + 1));
            }
        }
    }

    /**
     * Function to generate a random selection of chest IDs. randomly generate between 1 and 2 chests
     */
    public void generateRoomChestIDs(){
        for (int i = 0; i < roomArray.length; i++) {
            // generate random number between 1 and 3
            int numChests = ThreadLocalRandom.current().nextInt(1, 2 + 1);
            // make this entry of the roomChestIDs's be this length
            roomChestIds.add(new ArrayList<>(numChests));
            for (int j = 0; j < numChests; j++) {
                roomChestIds.get(i).add(ThreadLocalRandom.current().nextInt(1, JPAUtil.getNoOfEntries("Chest") + 1));
            }
        }
    }

    public ArrayList<ArrayList<Integer>> getRoomNpcIds() {
        return roomNpcIds;
    }

    public ArrayList<ArrayList<Integer>> getRoomChestIds() {
        return roomChestIds;
    }
}

