package com.project.TINAClient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class UserService {

    public final RSocketRequester rSocketRequester;

    @Autowired
    public UserService(RSocketRequester.Builder rsocketRequesterBuilder) {
        this.rSocketRequester = rsocketRequesterBuilder
                .connectTcp("localhost", 7000)
                .block();
    }

}
