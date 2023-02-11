package com.techelevator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Song;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class SpotifyService {

    private String oauthURL = "https://accounts.spotify.com/api/token";
    private RestTemplate restTemplate = new RestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();
    private String apiKey = "fd66a5913e534ed5a013e7c2328efb1a";
    private String apiSecret = "8b689262349f473792e7211c32057500";


    public String getToken() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(apiKey, apiSecret );
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "grant_type=client_credentials";
        HttpEntity<String> request = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(this.oauthURL, HttpMethod.POST, request, String.class);

        String token = "";
        try {
            JsonNode tokenServiceResponse = objectMapper.readTree(response.getBody());
             token = tokenServiceResponse.path("access_token").asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return token;
    }

    public Song searchSongId(String search) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(getToken());
        String url = "https://api.spotify.com/v1/search?q=";
        String url2 = "&type=track";
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(url + search + url2, HttpMethod.GET, request, String.class);

        JsonNode json = null;
        try {
            json = objectMapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
 
        assert json != null;
        JsonNode tracks = json.get("tracks");
        JsonNode items = tracks.get("items");
        JsonNode track = items.get(0);
        String trackId = track.get("id").asText();

        String songName = track.get("name").asText();

        JsonNode artists = track.get("artists");
        JsonNode artist = artists.get(0);
        String artistName = artist.get("name").asText();

        JsonNode album = track.get("album");
        JsonNode images = album.get("images");
        JsonNode image = images.get(0);
        String imageUrl = image.get("url").asText();

        return new Song(trackId, songName, artistName, imageUrl);
    }


}
