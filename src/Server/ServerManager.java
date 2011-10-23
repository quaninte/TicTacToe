/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.net.*;

/**
 *
 * @author quanmt
 */
public class ServerManager {
    
    int port;

    public ServerManager(int port) {
        this.port = port;
    }
    
    void start () {
        
        try {
            ServerSocket serverSocket = new ServerSocket(this.port);

            while (true) {
                System.out.println("Start listening ...");
                Socket socket = serverSocket.accept();
                System.out.println("Socket accepted!");
                new Server(socket).start();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public static void main(String[] args) {
        ServerManager manager = new ServerManager(9999);
        manager.start();
    }
    
}
