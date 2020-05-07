package com.project.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
@Getter
@Setter
@Table(name = "song")
@Entity
public class Song {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(name = "name")
    String name;

    @ManyToOne
    Genre genre;

    @ManyToOne
    Artist artist;

    @Column(name = "view_count")
    int viewCount;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "song_playlist",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    Set<Playlist> playlistSet = new HashSet<>();

    @Override
    public String toString() {
        return "title: " + name + " artist: " + artist.getName() + " genre: " + genre.getName();
    }
}
