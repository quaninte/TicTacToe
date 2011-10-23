/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author quanmt
 */
public class Main {
    
    public static void main(String[] args) {
        ServerManager manager = new ServerManager(9999);
        manager.start();
    }
    
}
