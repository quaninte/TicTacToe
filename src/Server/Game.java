/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.Box;
import java.io.*;
import java.net.*;

/**
 *
 * @author quanmt
 */
public class Game {
    
    int port;
    int currentTurn;
    ServerSocket serverSocket;
    
    Server[] servers = new Server[2];
    int []map;

    public Game(int port) {
        this.port = port;
        this.resetMap();
    }
    
    void resetMap () {
        this.map = new int[9];
        for (int i = 0; i < 9; i++) {
            this.map[i] = -1;
        }
    }
    
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
        
        // send player state
        this.servers[0].send("playerState", String.valueOf(Box.StateCircle));
        this.servers[1].send("playerState", String.valueOf(Box.StateCross));
        
        // tell player 1 go
        this.currentTurn = 0;
        this.servers[0].send("unlock", "-");
        
        while (true) {
            String []parts = this.servers[this.currentTurn].read();
            this.processMessage(parts[0], parts[1]);
            
            // next turn
            this.currentTurn = this.nextTurn();
        }
    }
    
    void processMessage (String type, String data) {
        if (type.equals("chose")) {
            int index = Integer.valueOf(data);
            this.map[index] = this.currentTurn;
            
            this.servers[this.nextTurn()].send("opponentChose", String.valueOf(index));
            if (this.isWon()) {
                this.servers[this.currentTurn].send("result", "win");
                this.servers[this.nextTurn()].send("result", "lose");
            }
        }
    }
    
    boolean isWon() {
        // check vertical | | |
        for (int i = 0; i < 3; i++) {
            if (this.map[i] != -1 && this.map[i] == this.map[i + 3] && this.map[i] == this.map[i + 6]) {
                return true;
            }
        }
        // check horizal - - -
        for (int i = 0; i < 7; i = i + 3) {
            if (this.map[i] != -1 && this.map[i] == this.map[i + 1] && this.map[i] == this.map[i + 2]) {
                return true;
            }
        }
        // check cross 0 4 8
        if (this.map[0] != -1 && this.map[0] == this.map[4] && this.map[0] == this.map[8]) {
            return true;
        }
        // check cross 2 4 6
        if (this.map[2] != -1 && this.map[2] == this.map[4] && this.map[2] == this.map[6]) {
            return true;
        }
        
        return false;
    }
    
    int nextTurn () {
        int nextTurn;
        
        if (this.currentTurn == 0) {
            nextTurn = 1;
        } else {
            nextTurn = 0;
        }
        
        return nextTurn;
    }
    
    void chose (int i) {
        
    }
    
    public static void main (String[] args) {
        Game game = new Game(9999);
        game.start();
    }
}
