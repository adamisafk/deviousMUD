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

    }

    public Boolean moveValidate(Direction direction) {
        return true;
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
