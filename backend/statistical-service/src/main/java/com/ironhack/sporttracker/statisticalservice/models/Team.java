package com.ironhack.sporttracker.statisticalservice.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ironhack.sporttracker.statisticalservice.enums.Sport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @JsonProperty("team_id")
    private Long teamId;
    private String name;
    @JsonProperty("logo")
    private String logoUrl;
    private String founded;
    @JsonProperty("venue_name")
    private String venueName;
    @JsonProperty("venue_city")
    private String venueCity;
    @JsonProperty("venue_capacity")
    private Long venueCapacity;
    private Sport sport;
}