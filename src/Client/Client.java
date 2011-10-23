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
public class Client extends SharedThread {

    String host;
    int port;
    
    Client(String host, int port) {
        this.host = host;
        this.port = port;
        
        try {
            this.socket = new Socket(this.host, this.port);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + this.host);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }
    }
    
    public void process() {
        this.send("test message");
        this.waitForInput();
    }
    
    public static void main(String[] args) {
        Client client = new Client("localhost", 9999);
        client.start();
    }
}
