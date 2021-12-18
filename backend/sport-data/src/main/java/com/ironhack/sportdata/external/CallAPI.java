package com.ironhack.sportdata.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ironhack.sportdata.enums.FixtureOption;
import com.ironhack.sportdata.models.*;
import com.jayway.jsonpath.JsonPath;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class CallAPI {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ModelMapper mapper;

    private final String FOOTBALL_X_RAPIDAPI_KEY = "1d2e98d697msh3d143a3c31b7b4dp131e15jsnd5edba329bc7";
    private final String FOOTBALL_X_RAPIDAPI_HOST = "api-football-v1.p.rapidapi.com";
    private final String FOOTBALL_URL = "https://api-football-v1.p.rapidapi.com/v2/";

    private final String BASKETBALL_X_RAPIDAPI_KEY = "1d2e98d697msh3d143a3c31b7b4dp131e15jsnd5edba329bc7";
    private final String BASKETBALL_X_RAPIDAPI_HOST = "api-basketball.p.rapidapi.com";
    private final String BASKETBALL_URL = "https://api-basketball.p.rapidapi.com";

    public Team getFootballTeamById(Long id) throws JsonProcessingException {

        String uri = FOOTBALL_URL + "teams/team/" + id;
        String response = WebClient.create()
                .get()
                .uri(uri)
                .headers(httpHeaders -> {
                    httpHeaders.set("x-rapidapi-host", FOOTBALL_X_RAPIDAPI_HOST);
                    httpHeaders.set("x-rapidapi-key", FOOTBALL_X_RAPIDAPI_KEY);
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();
        String resourcePath = "$.api.teams[0]";
        Object resource = JsonPath.parse(response).read(resourcePath);
        String resourceJson = new Gson().toJson(resource, Map.class);
        return objectMapper.readValue(resourceJson, Team.class);
    }

    public List<Fixture> getFootballFixturesTeamById(Long id, FixtureOption option) throws JsonProcessingException {
        String uri = FOOTBALL_URL + "/fixtures/team/" + id + "/" + option.toString().toLowerCase() + "/10";
        String response = WebClient.create()
                .get()
                .uri(uri)
                .headers(httpHeaders -> {
                    httpHeaders.set("x-rapidapi-host", FOOTBALL_X_RAPIDAPI_HOST);
                    httpHeaders.set("x-rapidapi-key", FOOTBALL_X_RAPIDAPI_KEY);
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();
        String resourcePath = "$.api.fixtures";
        String resource = JsonPath.read(response,resourcePath).toString();
        return objectMapper.readValue(resource, new TypeReference<>() {
        });
    }

    public List<Standing> getStandingsForFootballLeagueById(Long id) throws JsonProcessingException {
        String uri = FOOTBALL_URL + "leagueTable/" + id;
        String response = WebClient.create()
                .get()
                .uri(uri)
                .headers(httpHeaders -> {
                    httpHeaders.set("x-rapidapi-host", FOOTBALL_X_RAPIDAPI_HOST);
                    httpHeaders.set("x-rapidapi-key", FOOTBALL_X_RAPIDAPI_KEY);
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();
        String jsonPathLeaguesLocation = "$.api.standings[0]";
        String filteredResponse = JsonPath.read(response,jsonPathLeaguesLocation).toString();
        return objectMapper.readValue(filteredResponse, new TypeReference<>() {
        });

    }

    public List<Standing> getStandingsForBasketballLeagueById(Long id) throws JsonProcessingException {
        String uri = BASKETBALL_URL + "/standings?season=2021-2022&league=" + id;
        String response = WebClient.create()
                .get()
                .uri(uri)
                .headers(httpHeaders -> {
                    httpHeaders.set("x-rapidapi-host", BASKETBALL_X_RAPIDAPI_HOST);
                    httpHeaders.set("x-rapidapi-key", BASKETBALL_X_RAPIDAPI_KEY);
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();
        String jsonPathLeaguesLocation = "$.response[0][?(@.group.name==\"Eastern Conference\" || @.group.name==\"Western Conference\")]";
        String filteredResponse = JsonPath.read(response,jsonPathLeaguesLocation).toString();
        List<BasketballResponseModel> responseModel = objectMapper.readValue(filteredResponse, new TypeReference<>() {
        });
        List<Standing> apiCallResponse = new ArrayList<>();
        for (BasketballResponseModel model:responseModel) {
            Standing standing = mapper.map(model,Standing.class);
            apiCallResponse.add(standing);
        }
        return apiCallResponse;
    }

    public Team getBasketballTeamById(Long id) throws JsonProcessingException {
        String uri = BASKETBALL_URL + "/teams?season=2021-2022&id=" + id + "&league=12";
        String response = WebClient.create()
                .get()
                .uri(uri)
                .headers(httpHeaders -> {
                    httpHeaders.set("x-rapidapi-host", BASKETBALL_X_RAPIDAPI_HOST);
                    httpHeaders.set("x-rapidapi-key", BASKETBALL_X_RAPIDAPI_KEY);
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();
        String resourcePath = "$.response[0]";
        Object resource = JsonPath.parse(response).read(resourcePath);
        String resourceJson = new Gson().toJson(resource, Map.class);
        return objectMapper.readValue(resourceJson, Team.class);
    }

    public List<Fixture> getBasketballFixturesTeamById(Long id, FixtureOption option) throws JsonProcessingException {
        String uri = BASKETBALL_URL + "/games?league=12&season=2021-2022&team=" + id;
        String response = WebClient.create()
                .get()
                .uri(uri)
                .headers(httpHeaders -> {
                    httpHeaders.set("x-rapidapi-host", BASKETBALL_X_RAPIDAPI_HOST);
                    httpHeaders.set("x-rapidapi-key", BASKETBALL_X_RAPIDAPI_KEY);
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();
        String resourcePath = "$.response[*].[?(@.status.short==\"FT\" || @.status.short==\"NS\")]";
        String resource = JsonPath.read(response,resourcePath).toString();
        List<BasketballFixtureResponseModel> responseModel = objectMapper.readValue(resource, new TypeReference<>() {
        });
        List<Fixture> apiCallResponse = new ArrayList<>();
        for (BasketballFixtureResponseModel model:responseModel) {
            if (option.equals(FixtureOption.LAST)){
                Fixture fixture = mapper.map(model,Fixture.class);
                if (fixture.getStatus().contains("Finished")){
                    apiCallResponse.add(fixture);
                    Collections.reverse(apiCallResponse);
                }

            }else{
                Fixture fixture = mapper.map(model,Fixture.class);
                if (fixture.getStatus().contains("Started")){
                    apiCallResponse.add(fixture);
                    Collections.reverse(apiCallResponse);
                }
            }
        }
        return apiCallResponse;
    }
}
