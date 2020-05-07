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
@Table(name = "playlist")
@Entity
public class Playlist {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(name = "name")
    String name;

    @ManyToOne()
    User user;

    @ManyToMany(mappedBy = "playlistSet", fetch = FetchType.EAGER)
    Set<Song> songSet = new HashSet<>();

    @Override
    public String toString() {
        return name;
    }
}
