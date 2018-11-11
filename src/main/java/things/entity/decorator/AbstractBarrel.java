package things.entity.decorator;

import things.entity.Barrel;
import things.entity.Bullet;
import things.entity.GameComponent;

import java.awt.*;

public abstract class AbstractBarrel extends GameComponent {

    // Boolean to see which direction to move the entity
    protected boolean left;
    protected boolean right;
    // Boolean to show that space bar has been pressed
    protected boolean firing;
    /* The difference between the initial x position and the new x position
        This is the amount of pixels the entity will move per second/update */
    protected int deltaX;
    // The initial speed to travel horizontally
    protected int horizontalSpeed;
    private Barrel barrel;

    /**
     * 5 argument constructor method
     *
     * @param topLeftXPos value passed into the method
     * @param topLeftYPos value passed into the method
     * @param width       value passed into the method
     * @param height      value passed into the method
     * @param color       value passed into the method
     */
    public AbstractBarrel(int topLeftXPos, int topLeftYPos, int width, int height, Color color, int horizontalSpeed) {
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

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isFiring() {
        return firing;
    }


    // This method decides what to do every time the screen refreshes
    @Override
    public void update(){
        handleMovement();

        // checking to see if firing is true
        if (firing){
            fireBulletFromBarrel();
        }


    }

    protected void handleMovement() {
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
        if(isTooFarRight()){
            topLeftXPos = 1000 - width - 80;
        }
        if(isTooFarLeft()){
            topLeftXPos = 80;
        }

        // Resetting deltaX to 0
        deltaX = 0;
    }

    protected void fireBulletFromBarrel() {
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

    public int getHorizontalSpeed() {
        return horizontalSpeed;
    }

    private boolean isTooFarLeft() {
        return topLeftXPos < 80;
    }

    private boolean isTooFarRight() {
        return topLeftXPos > 1000 - width - 80;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(getColor());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawRect(topLeftXPos, topLeftYPos, width, height);
        g.fillRect(topLeftXPos, topLeftYPos, width, height);
    }

    public abstract AbstractBarrel getBarrel();

    public boolean hasDecorator(String className) {
        return false;
    }

    public AbstractBarrel withoutDecorator(String className) {
        return null;
    }
}
