package org.example.entity;

import java.util.Scanner;

import javax.persistence.*;

@Entity
@Table(name = "NPC")
public class NPC {
    static Scanner stdin = new Scanner(System.in);
    @Id
    @Column(name = "npc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "npc_name")
    private String name;
    @Column(name = "npc_description")
    private String description;
    @Column(name = "npc_health")
    private Integer healthValue;
    @Column(name = "npc_armour")
    private Integer armourValue;
    @Column(name = "npc_gold_carried")
    private Integer goldCarried;
    @Column(name = "npc_item_carried")
    private Integer itemCarried;
    @Column(name = "npc_is_friendly")
    private Boolean isFriendly;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHealthValue() {
        return healthValue;
    }
    public void setHealthValue(Integer healthValue) {
        this.healthValue = healthValue;
    }

    public Integer getArmourValue() {
        return armourValue;
    }
    public void setArmourValue(Integer armourValue) {
        this.armourValue = armourValue;
    }

    public Integer getGoldCarried() {
        return goldCarried;
    }
    public void setGoldCarried(Integer goldCarried) {
        this.goldCarried = goldCarried;
    }

    public Integer getItemCarried() {
        return itemCarried;
    }
    public void setItemCarried(Integer itemCarried) {
        this.itemCarried = itemCarried;
    }

    public Boolean getFriendly() {
        return isFriendly;
    }
    public void setFriendly(Boolean friendly) {
        isFriendly = friendly;
    }

    public NPC() {
    }

    public void converseWith(Room room) {
        boolean inDialogue = true;
        while (inDialogue) {
            System.out.println("What would you like to say to the character?");
            System.out.println("[1] What's your name?");
            System.out.println("[2] What's in this room?");
            System.out.println("[3] I have nothing more to say");
            int answer = Integer.parseInt(stdin.nextLine());
            if (answer == 1) {
                System.out.println("I am a " + this.name);
            } else if (answer == 2) {
                //TODO: Fix this
                System.out.println(room.describeRoom());
            } else if (answer == 3) {
                inDialogue = false;
            }
        }

    }
}
