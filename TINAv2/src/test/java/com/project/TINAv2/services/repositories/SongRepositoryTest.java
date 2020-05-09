package com.project.services.repositories;

import com.project.entities.Artist;
import com.project.entities.Genre;
import com.project.entities.Song;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
class SongRepositoryTest {

    @Autowired
    private SongRepository songRepository;

    @Test
    public void saveTest() {

        Song song = Song.builder().name("testing").artist(new Artist()).genre(new Genre()).build();
        songRepository.save(song);
        Assert.assertNotNull(songRepository.findByName("testing"));

    }

    @Test
    void findByName() {
    }

    @Test
    void findAllByViewCount() {
    }

    @Test
    void findAllByGenre_Name() {
    }

    @Test
    void findAllByArtist_Name() {
    }

    @Test
    void findAllByPlaylistSetContaining() {
    }
}