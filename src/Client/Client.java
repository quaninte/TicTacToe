/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Shared.*;
import java.net.*;
import java.io.*;

/**
 *
 * @author quanmt
 */
public class Client extends SharedSocket {

    String host;
    int port;
    
    Game game;
    
    Client(String host, int port, Game game) {
        this.host = host;
        this.port = port;
        this.game = game;
        
        try {
            this.socket = new Socket(this.host, this.port);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + this.host);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }
    }
    
    public void process() {
        this.game.play();
    }
}
