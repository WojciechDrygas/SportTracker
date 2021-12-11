package com.ironhack.sportdata.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ironhack.sportdata.enums.FixtureOption;
import com.ironhack.sportdata.models.Fixture;
import com.ironhack.sportdata.models.Standing;
import com.ironhack.sportdata.models.Team;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class CallAPI {

    @Autowired
    ObjectMapper objectMapper;

    private final String X_RAPIDAPI_KEY = "1d2e98d697msh3d143a3c31b7b4dp131e15jsnd5edba329bc7";
    private final String X_RAPIDAPI_HOST = "api-football-v1.p.rapidapi.com";
    private final String urlV2 = "https://api-football-v1.p.rapidapi.com/v2/";

    public List<Team> getTeamsForLeague(Long id) throws JsonProcessingException {
        String uri = urlV2 + "teams/league/" + id;
        String response = WebClient.create()
                .get()
                .uri(uri)
                .headers(httpHeaders -> {
                    httpHeaders.set("x-rapidapi-host",X_RAPIDAPI_HOST);
                    httpHeaders.set("x-rapidapi-key",X_RAPIDAPI_KEY);
                })
                .retrieve()
                .bodyToMono(String.class)
                        .block();
        String jsonPathLeaguesLocation = "$.['api'].['teams']";
        String filteredResponse = JsonPath.read(response,jsonPathLeaguesLocation).toString();
        return objectMapper.readValue(filteredResponse, new TypeReference<>() {
        });
    }

    public Team getTeamById(Long id) throws JsonProcessingException {
        String uri = urlV2 + "teams/team/" + id;
        String response = WebClient.create()
                .get()
                .uri(uri)
                .headers(httpHeaders -> {
                    httpHeaders.set("x-rapidapi-host",X_RAPIDAPI_HOST);
                    httpHeaders.set("x-rapidapi-key",X_RAPIDAPI_KEY);
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();
        String resourcePath = "$.api.teams[0]";
        Object resource = JsonPath.parse(response).read(resourcePath);
        String resourceJson = new Gson().toJson(resource, Map.class);
        return objectMapper.readValue(resourceJson, Team.class);
    }

    public List<Fixture> getFixturesTeamById(Long id, FixtureOption option) throws JsonProcessingException {
        String uri = urlV2 + "/fixtures/team/" + id + "/" + option.toString().toLowerCase() + "/10";
        String response = WebClient.create()
                .get()
                .uri(uri)
                .headers(httpHeaders -> {
                    httpHeaders.set("x-rapidapi-host",X_RAPIDAPI_HOST);
                    httpHeaders.set("x-rapidapi-key",X_RAPIDAPI_KEY);
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();
        String resourcePath = "$.api.fixtures";
        String resource = JsonPath.read(response,resourcePath).toString();
        return objectMapper.readValue(resource, new TypeReference<>() {
        });
    }

    public List<Standing> getStandingsForLeagueById(Long id) throws JsonProcessingException {
        String uri = urlV2 + "leagueTable/" + id;
        String response = WebClient.create()
                .get()
                .uri(uri)
                .headers(httpHeaders -> {
                    httpHeaders.set("x-rapidapi-host",X_RAPIDAPI_HOST);
                    httpHeaders.set("x-rapidapi-key",X_RAPIDAPI_KEY);
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();
        String jsonPathLeaguesLocation = "$.api.standings[0]";
        String filteredResponse = JsonPath.read(response,jsonPathLeaguesLocation).toString();
        return objectMapper.readValue(filteredResponse, new TypeReference<>() {
        });

    }
}
