package com.test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class MatchData {

    @Id
    private String id;

    private String name;

    @Column(name = "competition_id")
    private String competitionId;

    private String competition;

    @Column(name = "country_id")
    private String countryId;

    private String country;

    @Column(name = "time_stamp")
    private Long timestamp;

    private String date;

    private String time;

    @ManyToOne(cascade = CascadeType.ALL)
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    private Round round;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_team")
    private Team homeTeam;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "away_team")
    private Team awayTeam;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_score")
    private Score homeScore;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "away_score")
    private Score awayScore;

    @Column(name = "live_status")
    private String liveStatus;
}
