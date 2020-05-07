package com.project.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Socket socket = null;
    private ServerSocket serverSocket = null;

    public Server(int port) {

        try {

            serverSocket = new ServerSocket(port);

            try {
                runServer();
            } catch (IOException ex) {
                socket.close();
            }


        }catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void runServer() throws IOException {

        while(true) {

            socket = serverSocket.accept();
            System.out.println("new client!");

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            //DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            Thread thread = new ClientHandler(socket, objectInputStream, objectOutputStream);

            thread.start();
        }

    }
}
