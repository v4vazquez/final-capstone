package com.techelevator.controller;

import com.techelevator.model.Song;
import com.techelevator.services.SpotifyService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SpotifyController {

    private SpotifyService service = new SpotifyService();

    @GetMapping("/spotify-search/{search}")
    public Song test2(@PathVariable String search) {
        return service.searchSongId(search);
    }
}
