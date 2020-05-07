package com.project.services;

import com.project.entities.Artist;
import com.project.entities.Genre;
import com.project.entities.Playlist;
import com.project.entities.Song;
import com.project.services.repositories.ArtistRepository;
import com.project.services.repositories.GenreRepository;
import com.project.services.repositories.PlaylistRepository;
import com.project.services.repositories.SongRepository;
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

    public Song findById(int id){
        return songRepository.findById(id).orElse(null);
    }

    public List<Song> findByArtist(String artistName) {
        return songRepository.findAllByArtist_Name(artistName);
    }

    public List<Song> findByGenre(String genre) {
        return songRepository.findAllByGenre_Name(genre);
    }

    public List<Song> findByViewCount(int viewCount) {
        return songRepository.findAllByViewCount(viewCount);
    }

    public List<Song> findByPlaylist(Playlist playlist) {
            return songRepository.findAllByPlaylistSetContaining(playlist);
    }

    public Song findByName(String name) {
        return songRepository.findByName(name);
    }

    public Song save(String name, Genre genre, Artist artist) {
        return songRepository.save(Song.builder().name(name).genre(genre).artist(artist).viewCount(0).playlistSet(new HashSet<Playlist>()).build());
    }

    public void delete(Song song) {
        songRepository.delete(song);
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

}
