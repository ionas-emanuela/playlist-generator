package com.project.dtos;

import com.project.entities.Song;
import com.project.entities.SuggestedSong;
import com.project.entities.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class SuggestedSongDTO {

    int id;
    String songName;
    String userName;
    String friendName;

    public SuggestedSongDTO(SuggestedSong suggestedSong) {
        this.id = suggestedSong.getId();
        this.songName = suggestedSong.getSong().getName();
        this.userName = suggestedSong.getUser().getUsername();
        this.friendName = suggestedSong.getFriend().getUsername();
    }

    public SuggestedSong toEntity() {
        return SuggestedSong.builder()
                .id(id)
                .song(Song.builder().name(songName).build())
                .user(User.builder().username(userName).build())
                .friend(User.builder().username(friendName).build())
                .build();
    }

}
