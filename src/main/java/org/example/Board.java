package org.example;

import java.util.ArrayList;

public class Board {
    private Room[] roomArray;

    public Board(){
        generateBoard();
    }

    /**
     * Function to randomly generate the dungeon board. Specify the number of rooms
     * naming convention for the rooms :
     *
     *  |     1       |       2        |
     *  |     3       |       4        |      boss room      | (for now)
     *
     *
     */
    public void generateBoard(){
        this.roomArray = new Room[5];

        for (int i = 0; i < 4; i++) {
            //TODO: set rooms to random rooms from the database
        }

        // TODO: set the final room to a boss room.

    }

}

