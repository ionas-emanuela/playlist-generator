package com.project.services;

import com.project.entities.Artist;
import com.project.services.repositories.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public Artist findById(int id) {
        return artistRepository.findById(id).orElse(null);
    }

    public Artist findByName(String name) {
        return artistRepository.findByName(name);
    }

    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    public Artist save(String name) {
        return artistRepository.save(Artist.builder().name(name).build());
    }



}
