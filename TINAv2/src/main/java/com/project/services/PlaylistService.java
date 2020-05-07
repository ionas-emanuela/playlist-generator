package com.project.services;

import com.project.entities.Playlist;
import com.project.entities.Song;
import com.project.entities.User;
import com.project.services.repositories.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

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
}
