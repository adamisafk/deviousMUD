package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "Score")
public class Score {
    @Id
    @Column(name = "score_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "player_name")
    private String playerName;
    @Column(name = "gold_score")
    private Integer score;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
}
