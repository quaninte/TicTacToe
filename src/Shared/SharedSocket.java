/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Shared;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quanmt
 */
public abstract class SharedSocket {
    
    public Socket socket;
    public DataOutputStream os;
    public DataInputStream is;
    
    public void process() {
        
    }
    
    public void start() {
        
        try {
            os = new DataOutputStream(this.socket.getOutputStream());
            is = new DataInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }
        
        // start processing
        this.process();
    }
    
    public String[] read() {
        
        System.out.println("Waiting for input ...");
        
        String line;
        while (true) {
            try {
                try {
                    line = is.readUTF();
                } catch (EOFException e) {
                    line = "";
                }
                
                if (!line.isEmpty()) {
                    System.out.println("Received: " + line);
                    
                    String[] parts = line.split("\\|");
                    return parts;
                }
                
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
    
    public void send(String type, String data) {
        try {
            String message = type + "|" + data;
            this.os.writeUTF(message);
            System.out.println("Sent: " + message);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public void close()
    {
        try {
            this.is.close();
            this.os.close();
            this.socket.close();
            
            System.out.println("socket closed");
            
        } catch (IOException ex) {
            Logger.getLogger(SharedSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
