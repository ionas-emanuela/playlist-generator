package com.project.services.repositories;

import com.project.entities.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

    public Playlist findByName(String name);

    public List<Playlist> findByUserUsername(String username);
}
