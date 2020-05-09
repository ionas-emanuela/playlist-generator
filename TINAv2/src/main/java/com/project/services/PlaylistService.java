package com.project.services;

import com.project.entities.Playlist;
import com.project.entities.Song;
import com.project.entities.User;
import com.project.services.repositories.PlaylistRepository;
import com.project.services.repositories.SongRepository;
import com.project.services.repositories.UserRepository;
import com.project.services.utils.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;
    private final UserRepository userRepository;

    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    public Playlist findByName(String name) {
        return playlistRepository.findByName(name);
    }

    public List<Playlist> findByUsername(String username) {
        return playlistRepository.findByUserUsername(username);
    }

    public Playlist save (String name, User user) {
        return playlistRepository.save(Playlist.builder().name(name).user(user).songSet(new HashSet<Song>()).build());
    }

    public Playlist update (Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public void delete (Playlist playlist) {
        playlistRepository.delete(playlist);
    }

    public Playlist addSongToPlaylist(int songId, int playlistId) {

        Playlist playlist = playlistRepository.findById(playlistId).orElse(null);
        Song song = songRepository.findById(songId).orElse(null);

        if(null == playlist) {
            throw new EntityNotFoundException(playlistId, "playlist");
        }

        if(null == song) {
            throw new EntityNotFoundException(songId, "song");
        }

        playlist.getSongSet().add(song);

        playlistRepository.save(playlist);

        return playlist;
    }

    public Playlist createPlaylist(Playlist playlist) {
        User user = userRepository.findByUsername(playlist.getUser().getUsername());

        if(null == user) {
            throw new EntityNotFoundException(playlist.getUser().getUsername(),"user");
        }

        playlist.setUser(user);

        return playlistRepository.save(playlist);
    }

    public Playlist removeSongFromPlaylist(int playlistId, int songId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElse(null);

        if(null == playlist) {
            throw new EntityNotFoundException(playlistId, "playlist");
        }

        Song song = songRepository.findById(songId).orElse(null);

        if(null == song) {
            throw new EntityNotFoundException(songId, "song");
        }

        System.out.println(playlist.getSongSet());

        Set<Song> songs = playlist.getSongSet();
        Iterator<Song> it = songs.iterator();

        while(it.hasNext()) {
            Song songIt = it.next();
            if(songIt.getName().equals(song.getName())) {
                it.remove();
                break;
            }
        }

        Set<Playlist> playlists = song.getPlaylistSet();
        Iterator<Playlist> it2 = playlists.iterator();

        while(it2.hasNext()) {
            Playlist playlistIt = it2.next();
            if(playlistIt.getName().equals(playlist.getName())) {
                it2.remove();
                break;
            }
        }

        songRepository.save(song);

        System.out.println(playlist.getSongSet());

        return playlistRepository.save(playlist);
    }
}
