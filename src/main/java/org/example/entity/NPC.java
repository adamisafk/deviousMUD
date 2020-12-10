package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "NPC")
public class NPC {
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
        return description.replaceAll("/\\s\\s+/g", "\n");
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

    public boolean converseWithAndAttack(Room room, int dialogueOption) {
        if (dialogueOption == 1) {
            System.out.println("I am a " + this.name);
        } else if (dialogueOption == 2) {
            room.describeRoom();
        } else if (dialogueOption == 3) {
            System.out.println("The " + this.name + "says 'Goodbye'");
        } else if (dialogueOption == 4) {
            System.out.println("You have decided to attack the " + this.name + ", prepare yourself");
            return true;
        }
        return false;
    }
}
