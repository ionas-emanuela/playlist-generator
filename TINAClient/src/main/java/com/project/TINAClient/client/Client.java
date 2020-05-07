package com.project.TINAClient.client;

import com.project.TINAClient.dtos.IDTO;
import com.project.TINAClient.dtos.MessageDTO;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private Socket socket = null;
    private ObjectOutputStream objectOutputStream = null;
    private ObjectInputStream objectInputStream = null;
    private Scanner scanner = null;

    public Client(String address, int port){

        try {
            socket = new Socket(address, port);

            scanner = new Scanner(System.in);

            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            try {
                runClient();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
                socket.close();
                scanner.close();
                objectOutputStream.close();
                objectInputStream.close();
            }



        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void runClient() throws IOException, ClassNotFoundException {

        while (true) {

            String output = scanner.nextLine();

            IDTO toSend = handleUserInput();

            objectOutputStream.writeObject(toSend);

            IDTO received = (IDTO) objectInputStream.readObject();

            handleServerInput(received);

            System.out.println(received);
        }

    }

    private MessageDTO handleUserInput() {
        return null;
    }

    private void handleServerInput(IDTO received) {

    }

}
