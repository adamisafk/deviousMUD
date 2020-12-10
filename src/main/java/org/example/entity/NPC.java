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
    @ManyToOne
    @JoinColumn(name = "npc_item_carried", referencedColumnName = "item_id")
    private Item itemCarried;
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

    public Item getItemCarried() {
        return itemCarried;
    }
    public void setItemCarried(Item itemCarried) {
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
}
