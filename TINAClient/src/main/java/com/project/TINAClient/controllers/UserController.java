package com.project.TINAClient.controllers;

import com.project.TINAClient.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final RSocketRequester rSocketRequester;

    @GetMapping("songs")
    public List<SongDTO> getAllSongs() {

        List<SongDTO> songDTOList = new ArrayList();

        for (SongDTO songDTO : rSocketRequester
                .route("find-all-songs")
                .retrieveFlux(SongDTO.class).toIterable()) {
            songDTOList.add(songDTO);
        }

        return songDTOList;
    }

    @GetMapping("songsInPlaylist/{playlistId}")
    public List<SongDTO> getAllSongsByPlaylistId(@PathVariable int playlistId) {

        List<SongDTO> songDTOList = new ArrayList();

        for (SongDTO songDTO : rSocketRequester
                .route("find-all-songs-by-playlist-id")
                .data(playlistId)
                .retrieveFlux(SongDTO.class).toIterable()) {
            songDTOList.add(songDTO);
        }

        return songDTOList;
    }

    @GetMapping("songsByArtist/{artistId}")
    public List<SongDTO> getAllSongsByArtistId(@PathVariable int artistId) {

        List<SongDTO> songDTOList = new ArrayList();

        for (SongDTO songDTO : rSocketRequester
                .route("find-all-songs-by-artist-id")
                .data(artistId)
                .retrieveFlux(SongDTO.class).toIterable()) {
            songDTOList.add(songDTO);
        }

        return songDTOList;
    }

    @GetMapping("songsByGenre/{genreId}")
    public List<SongDTO> getAllSongsByGenreId(@PathVariable int genreId) {

        List<SongDTO> songDTOList = new ArrayList();

        for (SongDTO songDTO : rSocketRequester
                .route("find-all-songs-by-genre-id")
                .data(genreId)
                .retrieveFlux(SongDTO.class).toIterable()) {
            songDTOList.add(songDTO);
        }

        return songDTOList;
    }

    @GetMapping("songsByViewCount/{viewCount}")
    public List<SongDTO> getAllSongsByViewCount(@PathVariable int viewCount) {

        List<SongDTO> songDTOList = new ArrayList();

        for (SongDTO songDTO : rSocketRequester
                .route("find-all-songs-by-view-count")
                .data(viewCount)
                .retrieveFlux(SongDTO.class).toIterable()) {
            songDTOList.add(songDTO);
        }

        return songDTOList;
    }

    @PostMapping("songs/{songId}/{playlistId}")
    public PlaylistDTO addSongToPlaylist(@PathVariable int songId, @PathVariable int playlistId) {

        ArrayList<Integer> parameters = new ArrayList<>();
        parameters.add(songId);
        parameters.add(playlistId);

        return rSocketRequester
                .route("add-song-to-playlist")
                .data(parameters)
                .retrieveMono(PlaylistDTO.class)
                .block();

    }

    @PostMapping("playlist")
    public PlaylistDTO createPlaylist(@RequestBody PlaylistDTO playlistDTO) {

        return rSocketRequester
                .route("create-playlist")
                .data(playlistDTO)
                .retrieveMono(PlaylistDTO.class)
                .block();
    }

    @PostMapping("songs/{songId}/{userId}/{friendId}")
    public SuggestedSongDTO suggestSong(@PathVariable int songId, @PathVariable int userId, @PathVariable int friendId) {

        ArrayList<Integer> parameters = new ArrayList<>();
        parameters.add(songId);
        parameters.add(userId);
        parameters.add(friendId);

        return rSocketRequester
                .route("suggest-song")
                .data(parameters)
                .retrieveMono(SuggestedSongDTO.class)
                .block();
    }

    @PutMapping("playlists/{playlistId}/{songId}")
    public PlaylistDTO removeSongFromPlaylist(@PathVariable int playlistId, @PathVariable int songId) {

        ArrayList<Integer> parameters = new ArrayList<>();
        parameters.add(playlistId);
        parameters.add(songId);

        return rSocketRequester
                .route("remove-song-from-playlist")
                .data(parameters)
                .retrieveMono(PlaylistDTO.class)
                .block();
    }

    @PutMapping("songs/{id}")
    public SongDTO playSong(@PathVariable int songId) {

        return rSocketRequester
                .route("play-song")
                .data(songId)
                .retrieveMono(SongDTO.class)
                .block();

    }

    @PutMapping("users/{userId}/{friendId}")
    public void addFriend(@PathVariable int userId, @PathVariable int friendId) {

        ArrayList<Integer> parameters = new ArrayList<>();
        parameters.add(userId);
        parameters.add(friendId);

        rSocketRequester
                .route("add-friend")
                .data(parameters)
                .retrieveMono(UserDTO.class)
                .block();

    }

}
