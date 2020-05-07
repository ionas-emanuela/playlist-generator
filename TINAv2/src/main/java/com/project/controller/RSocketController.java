package com.project.controller;

import com.project.dtos.IDTO;
import com.project.dtos.MessageDTO;
import com.project.dtos.MessageType;
import com.project.dtos.UserDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RSocketController {

    @MessageMapping("request-response")
    MessageDTO requestResponse(MessageDTO request) {

        System.out.println("request received");
        return new MessageDTO(MessageType.GET,0, "test");
    }

    @MessageMapping("stream")
    Flux<UserDTO> stream(MessageDTO request) {
        return Flux.interval(Duration.ofSeconds(1))
                .map(aLong ->
                    new UserDTO(Math.toIntExact(aLong), "name", "password"));
    }

    @MessageMapping("request-response-users")
    Flux<UserDTO> requestResponseUsers(MessageDTO request) {
        List<UserDTO> userDTOList = new ArrayList<>();

        userDTOList.add(new UserDTO(1, "test1", "test1"));
        userDTOList.add(new UserDTO(2, "test2", "test2"));

        return Flux.fromIterable(userDTOList);
    }

}
