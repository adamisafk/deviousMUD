package org.example;

import org.example.entity.Item;
import org.example.entity.JPAUtil;
import org.example.entity.NPC;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player {
    private String name;
    private int healthValue;
    private int armourValue;
    private int goldCarried;
    private ArrayList<Item> itemCarried;
    private int currentInventoryItem;
    private int currentRoom;
    static Scanner stdin = new Scanner(System.in);


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
                setCurrentRoom(5);
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

    /**
     * returns the damage hit against an npc
     * @param npc
     * @return
     */
    public int hit(NPC npc) {
        System.out.println("Would you like to equip a different item [y/n]?");

        String answer = stdin.nextLine();

        if(answer.equals("y") || answer.equals("Y")){
            equipItem();
        }
        // roll dice to determine the strength of the hit to the npc, separate function for this since we need it for recieve hit
        Random r = new Random();

        int result = r.nextInt(20);
        result = result + 1;
        // calculate the damage to give to NPC

        if (result >= 10) {

            //npc.setHealthValue(npc.getHealthValue() - damageDealt);

            System.out.println("You dealt damage!");
            if (itemCarried.get(currentInventoryItem).getMeleeDamage()> itemCarried.get(currentInventoryItem).getMagicDamage()) {
                r = new Random();
                int attackResult = r.nextInt(itemCarried.get(currentInventoryItem).getMeleeDamage());
                int damageDealt = attackResult + 1;
                return damageDealt;

            } else{
                r = new Random();
                int attackResult = r.nextInt(itemCarried.get(currentInventoryItem).getMagicDamage());
                int damageDealt = attackResult + 1;
                return damageDealt;
            }

        } else {
            System.out.println("You missed your attack!");
        }
        System.out.printf("You use your weapon but the %s parries your attack...\n", npc.getName());
        return 0;
    }

    public void recieveHit(NPC npc) {
        // roll dice to determine the strength of the hit from the npc (same function as required in above)
        Random r = new Random();

        int result = r.nextInt(20);
        result = result + 1;

        // calculate the damage taken + update the variable npc.heathvalue
        if (result >= 15) {
            r = new Random();
            int attackResult = r.nextInt(2);
            int damageDealt = attackResult + 1;
            this.healthValue = healthValue - damageDealt;
            System.out.println("You took damage!");
        }
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

    public int getCurrentRoom() { return currentRoom; }

    public void setCurrentRoom(int currentRoom) { this.currentRoom = currentRoom; }

    public ArrayList<Item> getItemCarried() { return itemCarried; }

    public void setItemCarried(ArrayList<Item> itemCarried) { this.itemCarried = itemCarried; }

    public void equipItem(){
        System.out.printf("You have " + goldCarried + " gold\n");
        while (true){
            System.out.println("Please select an item from your inventory: ");
            displayItems();
            String answer = stdin.nextLine();
            if(isIntInRange(answer, itemCarried.size()) > 0){
                this.currentInventoryItem = isIntInRange(answer, itemCarried.size()) - 1;
                break;
            } else {
                System.out.println("Please select a valid inventory item");
            }
        }
    }

    public void pickupItem(Item item) {
        itemCarried.add(item);
    }

    /**
     * Functiopn returns the number if it is a valid inventory slot. otherwise returns negative number
     * @param answer
     * @param numberOfItemsInInventory
     * @return number if it is positive and valid inventory slot, otherwise negative number
     */
    public int isIntInRange(String answer, int numberOfItemsInInventory){
        int result;
        try{
            result = Integer.parseInt(answer);
            if(result > 0 && result <= numberOfItemsInInventory){
                return result;
            } else{
                return -1;
            }
        } catch (Exception e){
            return -1;
        }
    }



    public void displayItems(){
        for (int i = 0; i < itemCarried.size(); i++) {
            System.out.printf("[%d] ---  %s \n",i + 1,itemCarried.get(i).getName());
        }
    }


    public Player() {
     this.currentRoom = 1;
     this.healthValue = 30;
     this.itemCarried = new ArrayList<>();
     //Make fists default weapon
     itemCarried.add(JPAUtil.getItem(9));
    }
    /**
     * The following function verifies if a number is contained within a list of integers
     *
     * @return true or false
     */
    boolean verifyIntegers(int number, int... numbers) {
        for (int element : numbers) {
            if (number == element) {
                return true;
            }
        }
        return false;
    }




}

