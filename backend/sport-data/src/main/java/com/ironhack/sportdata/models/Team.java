package com.ironhack.sportdata.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @JsonAlias({"team_id","id"})
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
}
