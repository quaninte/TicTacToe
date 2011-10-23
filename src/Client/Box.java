/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author quanmt
 */
public class Box {
    
    static final int StateBlank = 0;
    static final int StateCircle = 1;
    static final int StateCross = 2;
    private int state;
    
    JButton button;

    public Box(JButton button) {
        this.button = button;
        this.changeState(Box.StateBlank);
    }
    
    public void changeState (int state) {
        this.state = state;
        
        String img;
        
        switch (state) {
            case Box.StateCircle:
                img = "circle.gif";
                break;
            case Box.StateCross:
                img = "cross.gif";
                break;
            case Box.StateBlank:
            default:
                img = "blank.gif";
                break;
        }
        this.button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/" + img)));
    }
    
    public int getState() {
        return this.state;
    }
    
}
