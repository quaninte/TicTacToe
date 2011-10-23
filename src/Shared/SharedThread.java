/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Shared;

import java.net.*;
import java.io.*;

/**
 *
 * @author quanmt
 */
public abstract class SharedThread extends Thread {
    
    public Socket socket;
    public DataOutputStream os;
    public DataInputStream is;
    public String line;
    
    public void process() {
        
    }
    
    @Override
    public void run() {
        
        try {
            os = new DataOutputStream(this.socket.getOutputStream());
            is = new DataInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }
        
        // start processing
        this.process();
    }
    
    public void waitForInput() {
        
        System.out.println("Waiting for input ...");
        
        this.line = "";
        while (true) {
            try {
                try {
                    this.line = is.readUTF();
                } catch (EOFException e) {
                    this.line = "";
                }
                
                if (this.line.isEmpty()) {
                    this.sleep(10);
                } else {
                    System.out.println("Received: " + this.line);
                    break;
                }
                
            } catch (InterruptedException e) {
                System.err.println(e);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
    
    public void send(String message) {
        try {
            this.os.writeUTF(message);
            System.out.println("Sent: " + message);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
}
