package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.Song;

import java.util.List;

public interface SongDao {
    List<Song> getSuggestedSongs(Event event);

    List<Song> getPlaylistSongs(Event event);

    void addSongToPlaylist(String songId,int eventId);

    void addSongToSuggested(Song song, int eventId);

    void deletePlaylistSong(String songId, int eventId);

    void deleteSuggestedSong(String songId, int eventId);

    int getNumberOfLikes(String songId, int eventId);

    void incrementLikes(String songId, int eventId);

    void decrementLikes(String songId, int eventId);
}
