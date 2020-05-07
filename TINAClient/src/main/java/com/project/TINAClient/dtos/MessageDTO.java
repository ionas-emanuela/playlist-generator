package com.project.TINAClient.dtos;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageDTO implements IDTO {

    String message;
    int numObjects;
    MessageType messageType;

}
