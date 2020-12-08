package org.example;

import javax.persistence.*;

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

    // TODO: change this
    private ArrayList<Chest> chests;
    private ArrayList<NPC> npcs;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getNoOfNpcs() { return noOfNpcs; }
    public void setNoOfNpcs(Integer noOfNpcs) { this.noOfNpcs = noOfNpcs; }

    public Integer getNoOfChests() { return noOfChests; }
    public void setNoOfChests(Integer noOfChests) { this.noOfChests = noOfChests; }

    public Boolean getIs_boss() { return is_boss; }
    public void setIs_boss(Boolean is_boss) { this.is_boss = is_boss; }

    public void describeChests() {
        int i = 0;
        System.out.println("In the corner there is a ");
        for (Chest chest: chests) {
            i = i + 1;
            System.out.println(chests.getName());
            System.out.println(chests.getDescription());
            if (Chest[].length != i) {
                System.out.println(" In another part of the room there is a ");
            } else {
                System.out.println(". There are no other objects in the room");
            }
        }

    }
}
