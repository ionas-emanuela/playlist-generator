package com.project.services;

import com.project.entities.*;
import com.project.services.repositories.*;
import com.project.services.utils.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    private final SuggestedSongRepository suggestedSongRepository;

    public Song findById(int id){
        return songRepository.findById(id).orElse(null);
    }

    public List<Song> findByPlaylist(Playlist playlist) {
            return songRepository.findAllByPlaylistSetContaining(playlist);
    }

    public List<Song> findAllSongsByPlaylistId(int id) {
        Playlist playlist = playlistRepository.findById(id).orElse(null);

        if(null == playlist) {
            throw new EntityNotFoundException(id, "playlist");
        }

        return songRepository.findAllByPlaylistSetContaining(playlist);
    }

    public Song saveSong(Song toSave) {

        if(null == artistRepository.findByName(toSave.getArtist().getName())) {
            artistRepository.save(Artist.builder().name(toSave.getArtist().getName()).build());
            toSave.setArtist(artistRepository.findByName(toSave.getArtist().getName()));
        }
        if(null == genreRepository.findByName(toSave.getGenre().getName())) {
            genreRepository.save(Genre.builder().name(toSave.getGenre().getName()).build());
            toSave.setGenre(genreRepository.findByName(toSave.getGenre().getName()));
        }

        return songRepository.save(toSave);
    }

    public Song updateSong(Song toUpdate) {

        Song oldSong = songRepository.findById(toUpdate.getId()).orElse(null);

        if(null == oldSong) {
            throw new EntityNotFoundException(toUpdate.getId(), "song");
        }
        if(null == toUpdate.getName()) {
            toUpdate.setName(oldSong.getName());
        }
        if(null == toUpdate.getArtist()) {
            toUpdate.setArtist(oldSong.getArtist());
        }
        if(null == toUpdate.getGenre()) {
            toUpdate.setGenre(oldSong.getGenre());
        }
        if(0 == toUpdate.getViewCount()) {
            toUpdate.setViewCount(oldSong.getViewCount());
        }

        if(null == artistRepository.findByName(toUpdate.getArtist().getName())) {
            artistRepository.save(Artist.builder().name(toUpdate.getArtist().getName()).build());
            toUpdate.setArtist(artistRepository.findByName(toUpdate.getArtist().getName()));
        }
        if(null == genreRepository.findByName(toUpdate.getGenre().getName())) {
            genreRepository.save(Genre.builder().name(toUpdate.getGenre().getName()).build());
            toUpdate.setGenre(genreRepository.findByName(toUpdate.getGenre().getName()));
        }

        return songRepository.save(toUpdate);
    }

    public void deleteSongById(int id) {
        songRepository.deleteById(id);
    }

    public List<Song> findAllSongs() {
        return songRepository.findAll();
    }

    public Song playSongById(int songId) {

        Song song = songRepository.findById(songId).orElse(null);

        if(null == song) {
            throw new EntityNotFoundException(songId,"song");
        }

        song.setViewCount(song.getViewCount() + 1);

        return songRepository.save(song);
    }

    public List<Song> findAllSongsByArtistId(int artistId) {

        if(null == artistRepository.findById(artistId).orElse(null)) {
            throw new EntityNotFoundException(artistId, "artist");
        }

        List<Song> songList = songRepository.findAllByArtist_Id(artistId);

        if(null == songList) {
            throw new EntityNotFoundException(artistId, "songs by artist");
        }

        return songList;
    }

    public List<Song> findAllSongsByGenreId(int genreId) {

        if(null == genreRepository.findById(genreId).orElse(null)) {
            throw new EntityNotFoundException(genreId, "artist");
        }

        List<Song> songList = songRepository.findAllByGenre_Id(genreId);

        if(null == songList) {
            throw new EntityNotFoundException(genreId, "songs by genre");
        }

        return songList;
    }

    public List<Song> findAllSongsByViewCount(int viewCount) {

        List<Song> songList = songRepository.findAllByViewCount(viewCount);

        if(null == songList) {
            throw new EntityNotFoundException(viewCount, "songs by view count");
        }

        return songList;

    }

    public SuggestedSong suggestSong(int songId, int userId, int friendId) {

        User user = userRepository.findById(userId).orElse(null);

        if(null == user) {
            throw new EntityNotFoundException(userId, "user");
        }

        User friend = userRepository.findById(friendId).orElse(null);

        if(null == friend) {
            throw new EntityNotFoundException(friendId, "user");
        }

        Song song = songRepository.findById(songId).orElse(null);

        if(null == song) {
            throw new EntityNotFoundException(songId, "song");
        }

        SuggestedSong suggestedSong = SuggestedSong.builder()
                .song(song).friend(friend).user(user).build();

        return suggestedSongRepository.save(suggestedSong);
    }

}
