package com.project.dtos;

import com.project.entities.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class SongDTO {

    int id;
    String name;
    String genre;
    String artist;
    int viewCount;

    public SongDTO(Song song) {
        this.id = song.getId();
        this.name = song.getName();
        this.genre = song.getGenre().getName();
        this.artist = song.getArtist().getName();
        this.viewCount = song.getViewCount();
    }

    public Song toEntity(){
        return Song.builder().id(id).name(name)
                .viewCount(viewCount)
                .artist(Artist.builder().name(artist).build())
                .genre(Genre.builder().name(genre).build())
                .build();
    }

}
