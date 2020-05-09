package com.project.controller;


import com.project.dtos.PlaylistDTO;
import com.project.dtos.SongDTO;
import com.project.dtos.SuggestedSongDTO;
import com.project.dtos.UserDTO;
import com.project.entities.Playlist;
import com.project.entities.Song;
import com.project.entities.SuggestedSong;
import com.project.entities.User;
import com.project.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final SongService songService;
    private final GenreService genreService;
    private final PlaylistService playlistService;
    private final ArtistService artistService;
    private final UserService userService;

    private String currentPlaylistString;

    @MessageMapping("find-all-songs")
    public Flux<SongDTO> findAllSongs() {
        List<Song> songList = songService.findAllSongs();
        List<SongDTO> songDTOList = new ArrayList<>();

        songList.forEach(song -> {
            songDTOList.add(new SongDTO(song));
        });

        return Flux.fromIterable(songDTOList);
    }

    @MessageMapping("find-all-songs-by-playlist-id")
    public Flux<SongDTO> findAllSongsByPlaylistId(Integer id) {
        List<Song> songList = songService.findAllSongsByPlaylistId(id);
        List<SongDTO> songDTOList = new ArrayList<>();

        songList.forEach(song -> {
            songDTOList.add(new SongDTO(song));
        });

        return Flux.fromIterable(songDTOList);
    }

    @MessageMapping("add-song-to-playlist")
    public PlaylistDTO addSongToPlaylist(List<Integer> parameters) {
        Playlist playlist = playlistService.addSongToPlaylist(parameters.get(0), parameters.get(1));

        System.out.println(playlist);

        return new PlaylistDTO(playlist);
    }

    @MessageMapping("play-song")
    public SongDTO playSong(Integer songId) {
        Song song = songService.playSongById(songId);

        return new SongDTO(song);
    }

    @MessageMapping("find-all-songs-by-artist-id")
    public Flux<SongDTO> findAllSongsByArtistId(Integer artistId) {
        List<Song> songList = songService.findAllSongsByArtistId(artistId);
        List<SongDTO> songDTOList = new ArrayList<>();

        songList.forEach(song -> songDTOList.add(new SongDTO(song)));

        return Flux.fromIterable(songDTOList);
    }

    @MessageMapping("find-all-songs-by-genre-id")
    public Flux<SongDTO> findAllSongsByGenreId(Integer genreId) {
        List<Song> songList = songService.findAllSongsByGenreId(genreId);
        List<SongDTO> songDTOList = new ArrayList<>();

        songList.forEach(song -> songDTOList.add(new SongDTO(song)));

        return Flux.fromIterable(songDTOList);
    }

    @MessageMapping("find-all-songs-by-view-count")
    public Flux<SongDTO> searchByViewCount(Integer viewCount) {
        List<Song> songList = songService.findAllSongsByViewCount(viewCount);
        List<SongDTO> songDTOList = new ArrayList<>();

        songList.forEach(song -> songDTOList.add(new SongDTO(song)));

        return Flux.fromIterable(songDTOList);
    }

    @MessageMapping("create-playlist")
    public PlaylistDTO createPlaylist(PlaylistDTO playlistDTO) {

        Playlist playlist = playlistService.createPlaylist(playlistDTO.toEntity());

        return new PlaylistDTO(playlist);
    }

    @MessageMapping("remove-song-from-playlist")
    public PlaylistDTO removeSongFromPlaylist(List<Integer> parameters) {

        Playlist playlist = playlistService.removeSongFromPlaylist(parameters.get(0),parameters.get(1));

        return new PlaylistDTO(playlist);
    }

    @MessageMapping("add-friend")
    public UserDTO addFriend(List<Integer> parameters) {

        User user = userService.addFriend(parameters.get(0), parameters.get(1));

        return new UserDTO(user);
    }

    @MessageMapping("suggest-song")
    public SuggestedSongDTO suggestSong(List<Integer> parameters) {

        SuggestedSong suggestedSong = songService.suggestSong(parameters.get(0), parameters.get(1), parameters.get(2));

        return new SuggestedSongDTO(suggestedSong);
    }

}

