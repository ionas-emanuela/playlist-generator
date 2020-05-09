package com.project.services.repositories;

import com.project.entities.Playlist;
import com.project.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

    Song findByName(String name);

    List<Song> findAllByViewCount(int viewCount);

    List<Song> findAllByGenre_Name(String genreName);

    List<Song> findAllByArtist_Name(String artistName);

    List<Song> findAllByPlaylistSetContaining(Playlist playlist);

    List<Song> findAllByArtist_Id(int id);

    List<Song> findAllByGenre_Id(int id);

}
