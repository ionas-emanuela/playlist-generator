package com.project.dtos;

import com.project.entities.User;
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

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public User toEntity() {
        return User.builder().id(id)
                .username(username)
                .password(password)
                .build();
    }

}
