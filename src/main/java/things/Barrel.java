package things;

import things.entity.singleton.FiredBullets;

import java.awt.*;

/**
 * This is an instantiable class called Barrel for creating a barrel entity.
 * It is a sub-class of GameComponent therefore it inherits all of its
 * attributes and abstract methods
 *
 * @author Darren Moriarty
 * created on 11/11/2016.
 *
 * @version 2.0
 */
public class Barrel extends GameComponent {

    // class attributes are made private so that they can not be directly accessed outside this class

    // Boolean to see which direction to move the entity
    private boolean left;
    private boolean right;
    // Boolean to show that space bar has been pressed
    private boolean firing;
    /* The difference between the initial x position and the new x position
        This is the amount of pixels the entity will move per second/update */
    private int deltaX;
    // The initial speed to travel horizontally
    private int horizontalSpeed;
    private FiredBullets tankBullets = FiredBullets.getTankBullets();


    /**
     * 6 argument constructor method
     *
     * @param topLeftXPos The initial x coordinate of the instantiated entity object
     * @param topLeftYPos The initial y coordinate of the instantiated entity object
     * @param width The initial width of the entity
     * @param height The initial height of the entity
     * @param color The initial colour of the entity
     * @param horizontalSpeed The initial horizontal speed of the object of the entity
     *
     * Call to super passes argument back to the super class GameComponent
     * One extra class individual attribute called horizontalSpeed
     */
    public Barrel(int topLeftXPos, int topLeftYPos, int width, int height, Color color, int horizontalSpeed) {
        super(topLeftXPos, topLeftYPos, width, height, color);
        setHorizontalSpeed(horizontalSpeed);
    }

    /**
     * This boolean sets the direction foe the entity to travel
     * @param direction sets the direction that is being currently pressed
     */
    public void setLeft(boolean direction){
        left = direction;
    }

    public void setRight(boolean direction){
        right = direction;
    }

    /**
     * This method sets the horizontal speed of the entity
     * @param horizontalSpeed
     */
    public void setHorizontalSpeed(int horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
    }

    // sets the boolean firing to the implicit value of firing
    public void setFiring(boolean firing) {
        this.firing = firing;
    }



    // This method decides what to do every time the screen refreshes
    @Override
    public void update(){

        // If the left attribute has the value of true
        if(left){
            // The next position of deltaX is the value of the horizontal speed ie. 5 pixels to the left
            deltaX = horizontalSpeed;
            // The original value of topLeftXPos is now the original value minus the next positions value deltaX
            topLeftXPos -= deltaX;
        }
        // If the right attribute has the value of true
        if(right){
            // The next position of x is the value of the horizontal speed ie. 5 pixels to the right
            deltaX  = horizontalSpeed;
            // The original value of x is now the original value plus the next positions value
            topLeftXPos += deltaX;
        }

        // This insures that the entity doesn't travel outside the green line at the bottom of the screen
        if(topLeftXPos > 1000 - width - 80){
            topLeftXPos = 1000 - width - 80;
        }
        if(topLeftXPos < 80){
            topLeftXPos = 80;
        }

        // Resetting deltaX to 0
        deltaX = 0;

        // checking to see if firing is true
        if (firing){
            // Adding a new instance of a bullet to the arrayList of bullets
            Bullet bullet = new Bullet(this.getTopLeftXPos() + (width / 4), 570, 5, 10, Color.WHITE, false);
            tankBullets.addBullet(bullet);
            try{
                playSound("sounds/shoot.wav");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // setting firing to false
            firing = false;
       }


    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(getColor());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawRect(topLeftXPos, topLeftYPos, width, height);
        g.fillRect(topLeftXPos, topLeftYPos, width, height);
    }

    public String toString(){
        return "Barrel class is working";
    }


}
