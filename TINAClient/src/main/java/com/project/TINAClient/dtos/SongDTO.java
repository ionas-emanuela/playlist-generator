package com.project.TINAClient.dtos;

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

}
