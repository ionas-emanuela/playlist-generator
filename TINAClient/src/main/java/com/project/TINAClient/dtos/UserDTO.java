package com.project.TINAClient.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserDTO implements Serializable, IDTO {

    static final long serialVersionUID = 6529685098267757690L;
    int id;
    String username;
    String password;

}
