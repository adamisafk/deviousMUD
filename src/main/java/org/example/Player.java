package org.example;

import org.example.entity.Item;
import org.example.entity.NPC;

public class Player {
    private String name;
    private int healthValue;
    private int armourValue;
    private int goldCarried;
    private Item itemCarried;
    private int currentRoom;

    public void move(Direction direction) {
        if(moveValidate(direction, this.currentRoom)){
            System.out.println("You see a door in front of you and try to open it. You turn an old rusty handle and with little effort the door opens.");
            if (this.currentRoom == 1 && direction == Direction.E){
                setCurrentRoom(2);
            } else if (this.currentRoom == 1 && direction == Direction.S){
                setCurrentRoom(3);
            } else if (this.currentRoom == 2 && direction == Direction.S){
                setCurrentRoom(4);
            } else if (this.currentRoom == 2 && direction == Direction.W){
                setCurrentRoom(1);
            } else if (this.currentRoom == 3 && direction == Direction.N){
                setCurrentRoom(1);
            } else if (this.currentRoom == 3 && direction == Direction.E){
                setCurrentRoom(4);
            } else if (this.currentRoom == 4 && direction == Direction.E){
                setCurrentRoom(4);
            } else if (this.currentRoom == 4 && direction == Direction.N){
                setCurrentRoom(2);
            } else if (this.currentRoom == 4 && direction == Direction.W){
                setCurrentRoom(3);
            } else if (this.currentRoom == 5 && direction == Direction.W){
                setCurrentRoom(4);
            }
        } else {
            System.out.println("You see a door in front of you and try to open it. A flash of light appears and you find \n " +
                    "yourself being pushed back by some sort of invisible force, you must find another way to get to your desired destination.");
        }
    }


    /**
     * Function to validate whether the player can move into a room.
     * @param direction
     * @param currentRoom
     * @return true or false, depending on whether the move is valid
     */
    public Boolean moveValidate(Direction direction, int currentRoom) {
        if(currentRoom == 1){
            return direction == Direction.E || direction == Direction.S;
        } else if (currentRoom == 2){
            return direction == Direction.W || direction == Direction.S;
        } else if (currentRoom == 3){
            return direction == Direction.E || direction == Direction.N;
        } else if (currentRoom == 4){
            return direction == Direction.W || direction == Direction.N || direction == Direction.E;
        } else if (currentRoom == 5){
            return direction == Direction.W;
        }
        return false;
    }

    public void hit(NPC npc) {

    }

    public void recieveHit(NPC npc) {


    }

    public void swapItem(Item newItem) {

    }

    public void setName(String name) { this.name = name; }

    public int getHealthValue() { return healthValue; }

    public void setHealthValue(int healthValue) { this.healthValue = healthValue; }

    public int getArmourValue() { return armourValue; }

    public void setArmourValue(int armourValue) { this.armourValue = armourValue; }

    public int getGoldCarried() { return goldCarried; }

    public void setGoldCarried(int goldCarried) { this.goldCarried = goldCarried; }

    public Item getItemCarried() { return itemCarried; }

    public void setItemCarried(Item itemCarried) { this.itemCarried = itemCarried; }

    public int getCurrentRoom() { return currentRoom; }

    public void setCurrentRoom(int currentRoom) { this.currentRoom = currentRoom; }

    public Player() {
     this.currentRoom = 1;
     this.healthValue = 10;
    }
}
