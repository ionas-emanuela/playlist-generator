package com.project.dtos;

import com.project.entities.Playlist;
import com.project.entities.Song;
import com.project.entities.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class PlaylistDTO {

    int id;
    String name;
    String userName;
    Set<SongDTO> songDTOSet;

    public PlaylistDTO(Playlist playlist) {
        this.id = playlist.getId();
        this.name = playlist.getName();
        this.songDTOSet = new HashSet<>();
        this.userName = playlist.getUser().getUsername();

        if(null == playlist.getSongSet()) {
            playlist.setSongSet(null);
        } else {
            playlist.getSongSet().forEach(song -> this.songDTOSet.add(new SongDTO(song)));
        }
    }

    public Playlist toEntity() {

        Set<Song> songSet = new HashSet<>();
        Playlist playlist = new Playlist();

        if(null != songDTOSet){
            this.songDTOSet.forEach(songDTO -> songSet.add(songDTO.toEntity()));
        } else {
            playlist.setSongSet(null);
        }

        playlist.setId(this.id);
        playlist.setUser(User.builder().username(this.userName).build());
        playlist.setName(this.name);

        return playlist;
    }

}
