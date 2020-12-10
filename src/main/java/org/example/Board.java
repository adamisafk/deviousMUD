package org.example;

import org.example.entity.Item;
import org.example.entity.JPAUtil;
import org.example.entity.Room;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private Room[] roomArray;
    private ArrayList<ArrayList<Integer>> roomNpcIds;
    private ArrayList<ArrayList<Integer>> correspondingNPCHealth;
    private ArrayList<ArrayList<Integer>> roomChestIds;

    public Board(){
        generateBoard();
        roomNpcIds = new ArrayList<ArrayList<Integer>>();
        correspondingNPCHealth = new ArrayList<ArrayList<Integer>>();
        roomChestIds = new ArrayList<ArrayList<Integer>>();
        generateRoomNPCIDs();
        generateNPCHealth();
        generateRoomChestIDs();
    }

    /**
     * Alternative constructor used for testing
     * @param i any integer
     */
    public Board(int i){
        generateBoard();
        roomNpcIds = new ArrayList<ArrayList<Integer>>();
        correspondingNPCHealth = new ArrayList<ArrayList<Integer>>();
        roomChestIds = new ArrayList<ArrayList<Integer>>();
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
     * Function to generate a random selection of NPC IDs. randomly generate between 1 and 3 npcs per room
     * The first index corresponds to the room, second to the id of the npc
     */
    public ArrayList<ArrayList<Integer>> generateRoomNPCIDs(){
        for (int i = 0; i < roomArray.length; i++) {
            // generate random number between 1 and 3
            int numNPCs = ThreadLocalRandom.current().nextInt(1, 3 + 1);
            // make this entry of the roomNPCid's be this length
            roomNpcIds.add(new ArrayList<>(numNPCs));
            for (int j = 0; j < numNPCs; j++) {
                roomNpcIds.get(i).add(ThreadLocalRandom.current().nextInt(1, JPAUtil.getNoOfEntries("NPC") + 1));
            }
        }
        return roomNpcIds;
    }


    /**
     * Function to store NPC health values in the correspond correspondingNPCHealth array. first index corresponds to the room
     * second index refers to the index of the npc in the npc array
     */
    public ArrayList<ArrayList<Integer>> generateNPCHealth(){
        int counter1 = 0;
        for (ArrayList<Integer> npcsInRoom: roomNpcIds) {
            correspondingNPCHealth.add(new ArrayList<>(npcsInRoom.size()));
            int counter2 = 0;
            for (Integer npcID: npcsInRoom) {
                correspondingNPCHealth.get(counter1).add(JPAUtil.getNPC(npcsInRoom.get(counter2)).getHealthValue());
                counter2++;
            }
            counter1++;
        }
        return correspondingNPCHealth;
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

    public ArrayList<ArrayList<Integer>> getCorrespondingNPCHealth() {
        return correspondingNPCHealth;
    }

    public void setElementCorrespondingNPCHealth(int value, int npcIndex, int currentRoom){
        this.correspondingNPCHealth.get(currentRoom-1).set(npcIndex, value);
    }

    public void destroyChestAtIndex(int currentRoom, int chestIndex) {
        roomChestIds.get(currentRoom-1).remove(chestIndex);
    }

    /**
     * Function used to count the number of alive npcs in the boss room.
     * @param roomID the ID of the boss room
     * @return true if they're all dead
     */
    public boolean allNpcsInRoomAreDead(int roomID){
        // set counter for number of alive npcs
        int numberOfAliveNPCs = 0;

        for (int i = 0; i < correspondingNPCHealth.get(roomID-1).size(); i++) {
            if(correspondingNPCHealth.get(roomID-1).get(i) > 0){
                numberOfAliveNPCs++;
            }
        }

        return numberOfAliveNPCs == 0;
    }
}

