/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Shared.*;
import java.net.*;
import java.io.*;

/**
 *
 * @author quanmt
 */
public class Server extends SharedSocket {

    Server(Socket socket) {
        this.socket = socket;
    }
 
    @Override
    public void process() {
        
    }
}
