package com.project.services.repositories;

import com.project.TinaV2.TinAv2Application;
import com.project.entities.Artist;
import com.project.entities.Song;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TinAv2Application.class})
class ArtistRepositoryTest {

    @Autowired
    ArtistRepository artistRepository;

    @Test
    public void saveTest() {
        Artist artist = Artist.builder().name("testing").songList(new ArrayList<Song>()).build();
        System.out.println(artist.toString());
        artistRepository.save(artist);
        Assert.assertNotNull(artistRepository.findByName("testing"));
    }

}