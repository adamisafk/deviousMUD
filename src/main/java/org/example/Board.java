package org.example;

import org.example.entity.JPAUtil;
import org.example.entity.Room;

import java.util.ArrayList;

public class Board {
    private Room[] roomArray;

    public Board(){
        generateBoard();
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

}

