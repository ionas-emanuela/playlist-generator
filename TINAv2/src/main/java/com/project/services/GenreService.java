package com.project.services;

import com.project.entities.Genre;
import com.project.services.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public Genre findById(int id) {
        return genreRepository.findById(id).orElse(null);
    }

    public Genre findByName(String name) {
        return genreRepository.findByName(name);
    }

    public List<Genre> findAll() {
        return genreRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Genre save(String name){
        return genreRepository.save(Genre.builder().name(name).build());
    }

}
