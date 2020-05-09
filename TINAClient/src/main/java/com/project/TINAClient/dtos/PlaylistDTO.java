package com.project.TINAClient.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class PlaylistDTO {

    int id;
    String name;
    String userName;
    Set<SongDTO> songDTOSet;

}
