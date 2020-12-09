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
        // roll dice to determine the strength of the hit to the npc, separate function for this since we need it for recieve hit
        Random r = new Random();

        int result = r.nextInt(20);
        result = result + 1;
        // calculate the damage to give to NPC

        if (result >= 10) {
            System.out.println("Choose how would you like to attack: ");
            System.out.printf("[1] Equipped Weapon");
            System.out.println("[2] Equipped Spell");
            int answer;

            while (true) {
                System.out.println("Please enter 1, 2");
                answer = Integer.parseInt(stdin.nextLine());
                if (verifyIntegers(answer, 1, 2)) {
                    break;
                }
            }

            if (answer == 1) {
            //    useEquippedWeapon();
            } else if (answer == 2) {
                //  useEquippedSpell();
            }
            int damageDealt = 2;
            npc.setHealthValue(npc.getHealthValue() - damageDealt);
        }

        //TODO Implement Items in database


        // update the variable this.healthValue
    }

    public void recieveHit(NPC npc) {
        // roll dice to determine the strength of the hit from the npc (same function as required in above)
        Random r = new Random();

        int result = r.nextInt(20);
        result = result + 1;

        // calculate the damage taken + update the variable npc.heathvalue
        if (result >= 15) {
            int damageDealt = 2;
            this.healthValue = healthValue - damageDealt;
            //TODO Implement Items in database
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

    public void equipItem(){
        while (true){
            System.out.println("Please select an item from your inventory");
            displayItems();
            int answer = Integer.parseInt(stdin.nextLine());
            if (verifyIntegers(answer,1,2,3,4,5,6,7,8,9,10)){
                break;
            }
        }
    }

    public void displayItems(){
        for (int i = 0; i < itemCarried.size(); i++) {
            System.out.printf("[%d] + %s",i + 1,itemCarried.get(i).getName());
        }
    }


    public Player() {
         this.currentRoom = 1;
         this.healthValue = 10;
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

