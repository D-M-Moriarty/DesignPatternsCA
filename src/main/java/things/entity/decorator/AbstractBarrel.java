package things.entity.decorator;

import things.entity.Bullet;
import things.entity.GameComponent;
import things.entity.strategy.movement.HorizontalMovementModified;
import things.entity.strategy.movement.StandardHorizontalMovement;

import java.awt.*;

public abstract class AbstractBarrel extends GameComponent {
    // Boolean to show that space bar has been pressed
    protected boolean firing;
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
        horizontalMovement = new HorizontalMovementModified(this);
        horizontalMovement.setHorizontalSpeed(horizontalSpeed);
    }

    // sets the boolean firing to the implicit value of firing
    public void setFiring(boolean firing) {
        this.firing = firing;
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
        moveHorizontally();

        // This insures that the entity doesn't travel outside the green line at the bottom of the screen
        if(isTooFarRight()){
            topLeftXPos = 1000 - width - 80;
        }
        if(isTooFarLeft()){
            topLeftXPos = 80;
        }

    }

    protected void fireBulletFromBarrel() {
        // Adding a new instance of a bullet to the arrayList of bullets
        Bullet bullet = new Bullet(this.getTopLeftXPos() + (width / 4), this.getTopLeftYPos(), 5, 10, Color.WHITE, false);
        tankBullets.addBullet(bullet);
        try{
            playSound("sounds/shoot.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // setting firing to false
        firing = false;
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
