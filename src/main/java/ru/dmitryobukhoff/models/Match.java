package ru.dmitryobukhoff.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "matches")
@Setter
@Getter
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player1_id", nullable = false)
    private Player player1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player2_id", nullable = false)
    private Player player2;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "winner_id", nullable = false)
    private Player winner;

    @Transient
    private Score score = new Score();

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", player1=" + player1 +
                ", player2=" + player2 +
                ", winner=" + winner +
                '}';
    }
}
