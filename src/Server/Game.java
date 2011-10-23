/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.*;
import java.net.*;

/**
 *
 * @author quanmt
 */
public class Game {
    
    int port;
    ServerSocket serverSocket;

    public Game(int port) {
        this.port = port;
    }
    
    Server[] servers = new Server[2];
    int []map;
    
    void connectClient (int i) {
        try {
                System.out.println("Waiting for client " + i);
                Socket socket = serverSocket.accept();
                System.out.println("Client " + i + " connected!");
                servers[i] = new Server(socket);
                servers[i].start();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public void start () {
        // init server socket
        try {
            serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            System.err.println(e);
        }
        
        // connect clients
        this.connectClient(0);
        this.connectClient(1);
        
        this.servers[0].send("hey zero");
    }
    
    void chose (int i) {
        
    }
    
    public static void main (String[] args) {
        Game game = new Game(9999);
        game.start();
    }
}
