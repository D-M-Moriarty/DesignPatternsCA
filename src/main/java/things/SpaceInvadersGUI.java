package things;

import things.entity.Barrel;
import things.entity.Barrier;
import things.entity.Bullet;
import things.entity.GameComponent;
import things.entity.command.*;
import things.entity.decorator.AbstractBarrel;
import things.entity.decorator.DoubleBarrel;
import things.entity.decorator.MoltenBullet;
import things.entity.decorator.WideBarrel;
import things.entity.factory_method.GameComponentFactory;
import things.entity.factory_method.StandardGameComponentFactory;
import things.entity.observer.Observer;
import things.entity.singleton.FiredBullets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_W;
import static things.entity.factory_method.Type.*;

/**
 * This is the JPanel class which is just a flat container for holding components
 *
 * @author Darren Moriarty
 * Created on 11/11/2016.
 *
 * This class inherits from JPanel
 * It Interfaces with Runnable and KeyListener
 */

public class SpaceInvadersGUI extends JPanel implements Runnable, KeyListener, Observer {

    // Class Attributes

    // width of the panel
    public static final int WIDTH = 1000;
    // height of the panel
    public static final int HEIGHT = 700;
    // Buffered image
    private BufferedImage image;
    // creating a Graphics object
    private Graphics2D g;
    // Boolean to signify whether the game is running or not
    public boolean isRunning;
    // Creating an independent process
    private Thread thread;
    // initial Frames per Second
    private int fps = 60;

    // Declaring entity objects
    private GameComponent tank;
    public static GameComponent tankLife1;
    public static GameComponent tankLife2;
    public static GameComponent tankLife3;
    private AbstractBarrel barrel;
    private  GameComponent[] barrier;
    private FiredBullets alienBulls = FiredBullets.getAlienBullets();
    private FiredBullets tankBulls = FiredBullets.getTankBullets();
    private GameComponent aliens;
    private static int playerScore = 0;
    private GameMain gameMain;
    private KeyCommand keyCommand;
    private AbstractBarrel wideBarrel;
    private GameComponentFactory factory;


    // JPanel Constructor
    public SpaceInvadersGUI(GameMain gameMain){
        // Calls the default constructor
        super();
        // this instance of the game
        this.gameMain = gameMain;

        // Sets the size of the panel to the Width and Height constants
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        if(thread == null){
            // Sets the new thread to the class
            thread = new Thread(this);
            // Starts the thread
            thread.start();
        }
        // Adds the KeyListener to this class
        addKeyListener(this);

        //to make sure the SpaceInvadersGUI can be focused on
        setFocusable(true);
        //gets the focus
        requestFocus();

        alienBulls.registerObserver(this);
        tankBulls.registerObserver(this);

        factory = new StandardGameComponentFactory(gameMain);
    }

    @Override
    public void run() {
        //set is running to true
        isRunning = true;
        // Creates a new image
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        // Sets the value of the graphics object
        g = (Graphics2D) image.getGraphics();

        // Initialises the GameComponents entities
        tank = factory.getComponent(TANK);
        tankLife1 = factory.getComponent(LIFE1);
        tankLife2 = factory.getComponent(LIFE2);
        tankLife3 = factory.getComponent(LIFE3);

        barrel = (AbstractBarrel) factory.getComponent(BARREL);


        barrier = new Barrier[3];

        // creating an array of barriers to signify lives up the top right corner
        for (int i = 0; i < 3; i++) {
            barrier[i] = factory.getComponent(BARRIER1);
            if(i == 1){
                barrier[i] = factory.getComponent(BARRIER2);
            }else if (i == 2){
                barrier[i] = factory.getComponent(BARRIER3);
            }
        }

        aliens = factory.getComponent(ALIENS);

        keyCommand = new KeyCommand(
                new FireBarrelCommand(barrel),
                new DownCommand(),
                new TankMoveLeftCommand(tank, barrel),
                new TankMoveRightCommand(tank, barrel),
                new FireBarrelCommand(barrel)
        );


        // Declaring variables to determine loop length time
        long startTime;
        //UpdateRenderDrawTime in milliseconds
        long URDTimeMilliS;
        // Time spent waiting for loop to start again
        long waitTime;
        // the length of time it takes for the loop to run
        long targetTime = 1000 / fps;

        // The main game loop
        while (isRunning){

            // sets the Systems current time in nanoseconds
            startTime  = System.nanoTime();

            // Methods to re-draw the graphics to the screen each iteration of the loop
            gameUpdate();
            gameRender();
            gameDraw();

            //UpdateRenderDrawTime is set to current system time minus the startTime divided by a million since its in nanoseconds
            URDTimeMilliS = (System.nanoTime() - startTime) / 1000000;
            // time spent waiting before the loop runs again
            waitTime = targetTime - URDTimeMilliS;

            // Try and catch block
            try {
                // Try sleeping for the length of the waitTime
                Thread.sleep(waitTime);
            } catch (Exception e) {
                // If it cant it will catch the exception / error
                //e.printStackTrace();
            }
        }
    }


    // static player score, was originally made for debugging purposes
    public static int getPlayerScore(){
        return playerScore;
    }

    public static void setPlayerScore(int playerScoreS){
        playerScore = playerScoreS;
    }

    // Responsible for updating everything in the game eg.Positions, values
    private void gameUpdate() {
        tank.update();
        barrel.update();

        // updating the barriers
        for (int i = 0; i < 3; i++) {
            barrier[i].update();
        }

        aliens.update();


        // updating the bullets
        for(int i = 0; i < tankBulls.size(); i++){
            tankBulls.getBullet(i).updateEntity();
        }

        for(int i = 0; i < alienBulls.size(); i++){
            alienBulls.getBullet(i).updateEntity();
        }

    }


    // Draws to the offscreen image, ie. determines what to draw on the next frame
    private void gameRender() {

        // These are constants that will be in every frame
        g.setColor(Color.BLACK);
        g.fillRect(0, 0 , WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        g.drawString("The alien bullet count is: " + alienBulls.size(), 10, 20);
        g.drawString("The tank bullet count is: " + tankBulls.size(), 10, 40);
        g.drawString("\nSCORE: " + getPlayerScore(), 10, 20);

        g.setColor(Color.GREEN);
        //drawing the line at the bottom of the screen
        Rectangle2D bottomLine = new Rectangle2D.Float(25, 650, 950, 10);
        g.fill(bottomLine);

        // draws the updated object values
        tank.draw(g);
        tankLife1.draw(g);
        tankLife2.draw(g);
        tankLife3.draw(g);
        barrel.draw(g);

        for (int i = 0; i < 3; i++) {
            barrier[i].draw(g);
        }

        aliens.draw(g);

        // Drawing ArrayList of bullets
        for(int i = 0; i < tankBulls.size(); i++){
            tankBulls.getBullet(i).drawEntity(g);
        }

        // Drawing ArrayList of AlienBullets
        for(int i = 0; i < alienBulls.size(); i++){
            alienBulls.getBullet(i).drawEntity(g);
        }


    }

    // drawing on to the game screen
    private void gameDraw() {
        Graphics gRef = this.getGraphics();

        // stopping the game loop if the lives have run out or the aliens have come
        // down to far on the screen
        if (tank.getLivesLeft() > 0 && !aliens.isHeightReached()){
            gRef.drawImage(image, 0 ,0, null);
            gRef.dispose();
        }else {
            // Save Players score and name and store it
            isRunning = false;
        }

    }

    // KeyListener interface methods
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyCommand.keyPressed(e);
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            if (barrel.hasDecorator("WideBarrel")) {
                barrel = barrel.withoutDecorator("WideBarrel");
            } else
                barrel = new WideBarrel(barrel);
        }

        if (key == KeyEvent.VK_D) {
            if (barrel.hasDecorator("DoubleBarrel")) {
                barrel = barrel.withoutDecorator("DoubleBarrel");
            } else
                barrel = new DoubleBarrel(barrel);
        }

        if (key == KeyEvent.VK_M) {
            if (barrel.hasDecorator("MoltenBullet")) {
                barrel = barrel.withoutDecorator("MoltenBullet");
            } else
                barrel = new MoltenBullet(barrel);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyCommand.keyReleased(e);
    }

    // This method removes a bullet from the ArrayList of bullets
    // once its left the screen bounds
    @Override
    public void updateObserver(Bullet bullet) {
        int tplYPos = bullet.getTopLeftYPos();
        int bulletHeight = bullet.getHeight();
        if (tplYPos + bulletHeight < 0) {
            tankBulls.removeBullet(bullet);
        } else if (tplYPos > HEIGHT) {
            alienBulls.removeBullet(bullet);
        }
    }


}
