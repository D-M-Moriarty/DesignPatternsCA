package things;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Darren Moriarty on 24/11/2016.
 */
public class HighScores extends JPanel{

    private JTextArea textArea;

    // JPanel Constructor
    public HighScores(GameMain gameMain){
        // Calls the default constructor
        super();
        // Sets the size of the panel to the Width and Height constants
        setPreferredSize(new Dimension(SpaceInvadersGUI.WIDTH, SpaceInvadersGUI.HEIGHT));
        setBackground(Color.BLACK);

        gameMain.printHighScorers();
        // setting the text area font
        Font textAreaFont = new Font("monospaced", Font.PLAIN,25);

        textArea = new JTextArea(30, 30);

        // setting the format of the text area
        textArea.setText(String.format("%9s%12s%12s%12s%n","Name","Score", "ABulls", "TBulls"));

        textArea.setFont(textAreaFont);

        // appending the text area
        for (int i = gameMain.getHighScorersSize()-1; i >= 0; i--) {
            textArea.append(String.format("%-5d%-12s%-10d%-9s%5d%n",(gameMain.getHighScorersSize() - i) ,
                    gameMain.highScorers.get(i).getName(),
                    gameMain.highScorers.get(i).getPlayerScore(),
                    gameMain.getAlienBulletsFired().size(),
                    gameMain.getTankBulletsFired().size()));
        }

        add(textArea);
        setFocusable(true);
        //gets the focus
        requestFocus();
    }


}
