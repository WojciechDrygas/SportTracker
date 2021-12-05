package com.ironhack.sportdata.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.sportdata.leagues.models.Team;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

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
}
