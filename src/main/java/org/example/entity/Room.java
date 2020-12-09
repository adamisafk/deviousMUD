package org.example.entity;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.jar.JarEntry;

@Entity
@Table(name = "Room")
public class Room {
    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "room_name")
    private String name;
    @Column(name = "room_description")
    private String description;
    @Column(name = "room_no_of_npcs")
    private Integer noOfNpcs;
    @Column(name = "room_no_of_chests")
    private Integer noOfChests;
    @Column(name = "room_is_boss")
    private Boolean is_boss;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description.replaceAll("  ", "\n");
    }

    // TODO: change this, so we can get the array of npcs in the room
    public NPC getNPC(int id){
        return JPAUtil.getNPC(id);
    }

    public Chest getChest(int id){
        return JPAUtil.getChest(id);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNoOfNpcs() {
        return noOfNpcs;
    }

    public void setNoOfNpcs(Integer noOfNpcs) {
        this.noOfNpcs = noOfNpcs;
    }

    public Integer getNoOfChests() {
        return noOfChests;
    }

    public void setNoOfChests(Integer noOfChests) {
        this.noOfChests = noOfChests;
    }

    public Boolean getIs_boss() {
        return is_boss;
    }

    public void setIs_boss(Boolean is_boss) {
        this.is_boss = is_boss;
    }

    /**
     * Loop over the array of RoomNPCIDs, created in the board class. Print out the description
     * corresponding to the entries in the database
     * @param roomNpcIds
     * @param currentRoom
     */
    public void describeNPCs(ArrayList<ArrayList<Integer>> roomNpcIds, int currentRoom) {
        //TODO: loop over all NPCs in the room
        System.out.println("NPCs in the room: ");
        for (int npcID = 0; npcID < roomNpcIds.get(currentRoom).size(); npcID++) {
            System.out.print("Name: ");
            System.out.println(getNPC(roomNpcIds.get(currentRoom).get(npcID)).getName());
            System.out.print("Description: ");
            System.out.println(getNPC(roomNpcIds.get(currentRoom).get(npcID)).getDescription());
        }
    }

    /**
     * Loop over the roomChestIDs in the current room and print out the chests names and descriptions.
     * @param roomChestIds
     * @param currentRoom
     */
    public void describeChests(ArrayList<ArrayList<Integer>> roomChestIds, int currentRoom) {
        //TODO: loop over all Chests in the room
        System.out.println("Chests in the room: ");
        for (int chestID = 0; chestID < roomChestIds.get(currentRoom).size(); chestID++) {
            System.out.print("Name: ");
            System.out.println(getChest(roomChestIds.get(currentRoom).get(chestID)).getName());
            System.out.print("Description: ");
            System.out.println(getChest(roomChestIds.get(currentRoom).get(chestID)).getDescription());
        }
    }
    public void describeRoom(int currentRoom) {
        System.out.print("Name: ");
        System.out.println(getName());
        System.out.print("Description: ");
        System.out.println(getDescription());
    }
}
