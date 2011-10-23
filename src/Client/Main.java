/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

/**
 *
 * @author quanmt
 */
public class Main {
    
    public static void main(String[] args) {
        Client client = new Client("localhost", 9999);
        client.start();
    }
}
