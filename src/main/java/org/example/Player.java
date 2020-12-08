package org.example;

import javax.persistence.*;

public class Player {
    private String name;
    private int healthValue;
    private int armourValue;
    private int goldCarried;
    private Item itemCarried;
    private int currentRoom;

    public void move(Direction direction) {
        if(moveValidate(direction))
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
    }
}
