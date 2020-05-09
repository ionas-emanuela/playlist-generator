package com.project.TINAClient.dtos;

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


}
