package things;

import things.entity.Bullet;
import things.entity.GameComponent;
import things.entity.observer.Observer;
import things.entity.singleton.FiredBullets;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * This is the main JFrame class with the main method to run the game
 *
 * @author Darren Moriarty
 * Created on 11/11/2016.
 *
 * This class inherits from JFrame
 */
public class GameMain implements Observer {

    private JFrame frame = new JFrame();

    private JPanel welcomeGUI = new WelcomeGUI();

    private SpaceInvadersGUI spaceInvadersGUI;
    private HighScores highscores;

    ClassLoader classLoader = getClass().getClassLoader();

    String startImage = classLoader.getResource("images/1280x960-space-invaders-press-start-wallpaper.jpg").getFile();
    private ImageIcon imageIcon = new ImageIcon(startImage);
    private JButton startGame = new JButton(imageIcon);

    String startImage2 = classLoader.getResource("images/high-scores.png").getFile();
    private ImageIcon imageIcon2 = new ImageIcon(startImage2);
    private JButton startGame2 = new JButton(imageIcon2);

    private JMenuBar  jmenuBar;
    private JMenu jmenu;
    private JMenu jmenuBack;
    private JMenuItem itemBack;
    private JMenuItem jmenuItem;


    public LinkedList<Player> highScorers =  new LinkedList<Player>();


    private Set<Bullet> alienBulletsFired;
    private Set<Bullet> tankBulletsFired;

    public Set<Bullet> getAlienBulletsFired() {
        return alienBulletsFired;
    }

    public Set<Bullet> getTankBulletsFired() {
        return tankBulletsFired;
    }

    // JFrame GUI constructor method
    public GameMain(){
        // Super calls the JFrame constructor and sets the title
        frame.setTitle("Space Invaders");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocation(200,0);

        // Loads the highScores.dat file
        loadScores();

        alienBulletsFired = new HashSet<>();
        tankBulletsFired = new HashSet<>();

        FiredBullets.getAlienBullets().registerObserver(this);
        FiredBullets.getTankBullets().registerObserver(this);

        try{
            GameComponent.playSound("sounds/spaceinvaders1.wav");
        }
        catch (Exception e) {e.printStackTrace();}

        // JMenuBar components
        jmenuBar = new JMenuBar();
        jmenu = new JMenu("Info");
        jmenuBack = new JMenu(("Back"));
        itemBack = new JMenuItem("Back");
        jmenuItem = new JMenuItem("History");

        // adding Jmenu
        jmenu.add(jmenuItem);
        jmenuBack.add(itemBack);
        jmenuBar.add(jmenu);
        jmenuBar.add(jmenuBack);
        frame.setJMenuBar(jmenuBar);

        // listener for the history menu item
        jmenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Space Invaders " +
                        "is an arcade video game created by Tomohiro Nishikado and released in 1978.\n" +
                        "It was originally manufactured and sold by Taito in Japan, and was later licensed\n" +
                "for production in the United States by the Midway division of Bally. Space Invaders is one of\n" +
                        "the earliest shooting games and the aim is to defeat waves of aliens with a laser\n" +
                        "cannon to earn as many points as possible. In designing the game, Nishikado drew\n" +
                        "inspiration from popular media: Breakout, The War of the Worlds, and Star Wars.\n" +
                                "To complete it, he had to design custom hardware and development tools.\n" +
                        "\nIt was one of the forerunners of modern video gaming and helped expand the\n" +
                        "video game industry from a novelty to a global industry (see golden age of video arcade games).\n" +
                        "When first released, Space Invaders was very successful.\n");
            }
        });

        // listener for the back item to change the content pane
        itemBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeContentPane2();
            }
        });


        // adding the two buttons to the welcome screen
        welcomeGUI.add(startGame);
        welcomeGUI.add(startGame2);

        // colouring the button borders black to make them look invisible
        startGame.setBackground(Color.black);
        startGame.setBorder(new LineBorder(Color.BLACK));
        startGame2.setBackground(Color.BLACK);
        startGame2.setBorder(new LineBorder(Color.BLACK));

        // setting the content pane
        frame.setContentPane(welcomeGUI);

        // button to load the game
        startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // calling start game method
                startGame();
                System.out.println("new panel created");//for debugging purposes
                // resetting the container
                frame.validate();
                frame.setVisible(true);
            }
        });

        // Button for loading the high scores
        startGame2.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
                viewHighScores();
            }
        });

        // setting focus to the current pane
        frame.getContentPane().setFocusable(true);
        frame.getContentPane().requestFocusInWindow();
        // This sets the size of the JFrame to whatever the size of the Component inside it is
        frame.pack();
        frame.setVisible(true);

        //Changing the window closing, anonymous inner class
        frame.addWindowListener(
                new WindowAdapter(){
                    @Override
                    public void windowClosing(WindowEvent e){
                        int option = JOptionPane.showConfirmDialog(null,"Are you sure you want to quit the game?");

                        if(option == JOptionPane.YES_OPTION){
                            try{// saving the high scores when the window is closed
                                saveScores();
                                System.out.println("The save worked");
                            }
                            catch (IOException f){
                                f.printStackTrace();
                            }

                            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        }else{
                            frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        }
                    }//end windowClosing

                    public void windowIconified(WindowEvent e){
                        JOptionPane.showMessageDialog(null,"Minimising the window");
                    }

                    public void windowDeiconified(WindowEvent e){
                        JOptionPane.showMessageDialog(null,"Restoring the window");
                    }
                });


    }

    // adding a link to the highScorers
    public void addToHighScorers(Player highScorers){
        this.highScorers.add(highScorers);
    }

    // returns the size of the list
    public int getHighScorersSize(){

        return highScorers.size();
    }

    // prints the contents of the list
    public void printHighScorers(){

        for (Player player: highScorers) {
            System.out.println(player);
        }
    }

    // sorting the highScorers list
    public void sortHighScorers(){

        // using an insertion sort
        int smallest;
        for (int i = 0; i < highScorers.size(); i++) {
            smallest = i;
            for (int j = i + 1; j < highScorers.size(); j++) {
                if (highScorers.get(j).getPlayerScore() < highScorers.get(smallest).getPlayerScore()) {
                    smallest = j;

                }
            }
            if (smallest != i) {
                final int tempScore = highScorers.get(i).getPlayerScore();
                final String tempName = highScorers.get(i).getName();
                highScorers.get(i).setPlayerScore(highScorers.get(smallest).getPlayerScore());
                highScorers.get(i).setName(highScorers.get(smallest).getName());
                highScorers.get(smallest).setPlayerScore(tempScore);
                highScorers.get(smallest).setName(tempName);
            }

        }

        for (Player player: highScorers) {
            System.out.println(player.getName() + " has a score of " + player.getPlayerScore());
        }

    }

    public void removeFirstLink(){
        highScorers.remove(0);
    }

    // creates the instance of the GUI
    public void startGame() {
        spaceInvadersGUI = new SpaceInvadersGUI(this);
        jmenuBar.setVisible(false);
        changeScreen(spaceInvadersGUI);
        frame.pack();
    }

    public void viewHighScores() {
        highscores = new HighScores(this);
        changeScreen(highscores);
        frame.pack();
    }

    public void changeContentPane2() {
        changeScreen(welcomeGUI);
        jmenuBar.setVisible(true);
    }

    private void changeScreen(JPanel screen) {
        frame.setContentPane(screen);
        screen.requestFocusInWindow();
    }

    // method for saving scores
    public void saveScores() throws IOException {
            ObjectOutputStream oob = new ObjectOutputStream(new FileOutputStream("HighScores.dat"));
            oob.writeObject(highScorers);
            oob.close();
    }

    public void loadScores() {
        try{
            URL startImage = classLoader.getResource("HighScores.dat");
            ObjectInputStream oobIn = new ObjectInputStream(new FileInputStream(startImage.getFile()));
             highScorers = (LinkedList<Player>) oobIn.readObject();
            oobIn.close();
        } catch (Exception e){ e.printStackTrace(); }

    }

    // main method creates a new JFrame called GameMain
    public static void main(String[] args) {
        new GameMain();
    }

    @Override
    public void updateObserver(Bullet bullet) {
        if (bullet.isAlienBullet())
            alienBulletsFired.add(bullet);
        else
            tankBulletsFired.add(bullet);
    }
}
