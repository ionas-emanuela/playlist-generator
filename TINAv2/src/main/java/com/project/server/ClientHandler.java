package com.project.server;

import com.project.dtos.MessageDTO;
import com.project.dtos.UserDTO;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {

    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;
    private final Socket socket;

    public ClientHandler(Socket socket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
        this.objectInputStream = objectInputStream;
        this.objectOutputStream = objectOutputStream;
        this.socket = socket;
    }

    @Override
    public void run() {
        MessageDTO input;
        MessageDTO output;

        while (true) {

            try {
                input = (MessageDTO) objectInputStream.readObject();

                if(input.getMessage().equals("exit")) {
                    break;
                }
                else if(input.getMessage().equals("user")) {
                    UserDTO userDTO = new UserDTO(1, "rou","pwd");

                    System.out.println(userDTO);
                    //output = input;

                    //objectOutputStream.writeObject(output);
                    //objectOutputStream.writeObject(output);
                    objectOutputStream.writeObject(userDTO);
                } else {
                    output = input;

                    objectOutputStream.writeObject(output);
                }


            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
                break;
            }

        }

        try {
            this.objectOutputStream.close();
            this.objectInputStream.close();
            this.socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
