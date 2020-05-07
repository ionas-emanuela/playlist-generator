package com.project.TINAClient.controllers;

import com.project.TINAClient.dtos.MessageDTO;
import com.project.TINAClient.dtos.MessageType;
import com.project.TINAClient.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final RSocketRequester rSocketRequester;

    @GetMapping("hibitch")
    public MessageDTO test() {
        System.out.println("sending request..");
        //return new MessageDTO("hello",0,MessageType.GET);

        return rSocketRequester
                .route("request-response")
                .data(new MessageDTO("hello",0, MessageType.GET))
                .retrieveMono(MessageDTO.class)
                .block();
    }


    @ShellMethod("Send one request. One response will be printed.")
    public void requestResponse() throws InterruptedException {

        System.out.println("here");

        MessageDTO messageDTO = this.rSocketRequester
                .route("request-response")
                .data(new MessageDTO("client test", 0, MessageType.GET))
                .retrieveMono(MessageDTO.class)
                .block();

        System.out.println(messageDTO);
    }

}
