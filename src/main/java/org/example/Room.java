package org.example;

import javax.persistence.*;

@Entity
@Table(name = "Room")
public class Room {
    private Integer roomId;
    private String roomName;
    private String roomDescription;
    private Integer noOfNpcs;
    private Integer noOfChests;
    private Boolean is_boss;

    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
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
}
