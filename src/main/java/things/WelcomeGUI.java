package things;

import javax.swing.*;
import java.awt.*;

/**
 * This is the JPanel class which is just a flat container for holding components
 *
 * @author Darren Moriarty
 * Created on 11/11/2016.
 *
 * This class inherits from JPanel
 * It Interfaces with Runnable and KeyListener
 */
public class WelcomeGUI extends JPanel {

    // JPanel Constructor
    public WelcomeGUI(){
        // Calls the default constructor
        super();
        // Sets the size of the panel to the Width and Height constants
        setPreferredSize(new Dimension(SpaceInvadersGUI.WIDTH, SpaceInvadersGUI.HEIGHT));
        setBackground(Color.BLACK);
        //to make sure the SpaceInvadersGUI can be focused on
        setFocusable(true);
        //gets the focus
        requestFocus();
    }



}
