package things.entity;

import things.GameMain;
import things.Player;
import things.SpaceInvadersGUI;
import things.entity.singleton.FiredBullets;
import things.entity.strategy.movement.HorizontalMovement;
import things.entity.strategy.movement.HorizontalMovementModified;
import things.entity.strategy.movement.StandardHorizontalMovement;

import javax.swing.*;
import java.awt.*;

/**
 * This is an instantiable class called Tank for creating a Tank entity.
 * It is a sub-class of GameComponent therefore it inherits all of its
 * attributes and abstract methods
 *
 * @author Darren Moriarty
 * created on 11/11/2016.
 *
 * @version 2.0
 */
public class Tank extends GameComponent{
    private GameMain gameMain;
    private Player player;
    private int livesLeft;
    private FiredBullets alienBulls = FiredBullets.getAlienBullets();

    /**
     * 6 argument constructor method
     *
     * @param topLeftXPos The initial x coordinate of the instantiated Tank entity object
     * @param topLeftYPos The initial y coordinate of the instantiated Tank entity object
     * @param width The initial width of the entity
     * @param height The initial height of the Tank entity
     * @param color The initial colour of the Tank entity
     * @param livesLeft The initial amount of lives the Tank entity has
     * @param horizontalSpeed The initial horizontal speed of the Tank entity
     */
    public Tank(int topLeftXPos, int topLeftYPos, int width, int height,
                Color color, int livesLeft, int horizontalSpeed, GameMain gameMain) {
        super(topLeftXPos, topLeftYPos, width, height, color);
        setLivesLeft(livesLeft);
        horizontalMovement = new HorizontalMovementModified(this);
        horizontalMovement.setHorizontalSpeed(horizontalSpeed);
        this.gameMain = gameMain;
    }

    /**
     * This sets the amount of the lives for the entity
     * @param livesLeft is the amount of lives
     */
    public void setLivesLeft(int livesLeft) {
        this.livesLeft = livesLeft;
    }

    /**
     * This returns the amount of lives the entity has remaining
     * @return the amount of lives left
     */
    @Override
    public int getLivesLeft(){
        return livesLeft;
    }

    @Override
    public void draw(Graphics2D g) {

        g.setColor(getColor());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawRect(getTopLeftXPos(), getTopLeftYPos(), getWidth(), getHeight());
        g.fillRect(getTopLeftXPos(), getTopLeftYPos(), getWidth(), getHeight());

    }

    // This method decides what to do every time the screen refreshes
    @Override
    public void update(){

        moveHorizontally();

        // This insures that the entity doesn't travel outside the green line at the bottom of the screen
        if(topLeftXPos > 1000 - width - 25){
            topLeftXPos = 1000 - width - 25;
        }
        if(topLeftXPos < 25){
            topLeftXPos = 25;
        }

        // checking for collisions with the tank
        for (int k = 0; k < alienBulls.size(); k++) {
            Bullet alienBullet = alienBulls.getBullet(k);


            if (alienBullet.collidesWith(this)) {
                System.out.println(getLivesLeft());

                try{
                    playSound("sounds/explosion.wav");
                }
                catch (Exception e) { e.printStackTrace(); }


                // reducing the lives of the tank
                setLivesLeft(getLivesLeft() - 1);
                // setting the colour of the lives to black
                SpaceInvadersGUI.tankLife1.setColor(Color.BLACK);

                if(getLivesLeft() == 1){
                    SpaceInvadersGUI.tankLife2.setColor(Color.BLACK);
                }

                alienBulls.removeBullet(alienBullet);

                // if the lives run out
                if (getLivesLeft() < 1){
                    SpaceInvadersGUI.tankLife3.setColor(Color.BLACK);
                    this.setTopLeftXPos(-100);
                    this.setTopLeftYPos(-100);
                    this.setWidth(-100);
                    this.setHeight(-100);

                    //Create the player class
                    player = new Player(SpaceInvadersGUI.getPlayerScore(), JOptionPane.showInputDialog(null, "Your tank has been destroyed\nPlease enter your name: "));
                    SpaceInvadersGUI.setPlayerScore(0);


                    //Keeping track of the highScores
                    manageHighScores(gameMain, player);

                    // changing back to the welcome screen
                    gameMain.changeContentPane2();

                }


            }


        }

    }

    static void manageHighScores(GameMain gameMain, Player player) {
        if(gameMain.getHighScorersSize() < 11){
            gameMain.addToHighScorers(player);

            gameMain.sortHighScorers();

            if(gameMain.getHighScorersSize() == 11){
                gameMain.removeFirstLink();
            }

        }
    }

    public String toString(){
        return "Tank class is working";
    }


}
