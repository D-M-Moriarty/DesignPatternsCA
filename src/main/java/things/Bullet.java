package things;

import things.entity.draw.DrawSquareSprite;
import things.entity.update.UpdateBullet;

import java.awt.*;

/**
 * This is an instantiable class called Bullet for creating a bullet entity.
 * It is a sub-class of GameComponent therefore it inherits all of its
 * attributes and abstract methods
 *
 * @author Darren Moriarty
 * created on 11/11/2016.
 *
 * @version 2.0
 */
public class Bullet extends GameComponent {

    // Class Attributes

    /* The difference between the initial y position and the new y position
        This is the amount of pixels the entity will move per second/update */
    private static final int DELTA_Y = 5;
    private boolean isAlienBullet;

    /**
     *
     * @param topLeftXPos
     * @param topLeftYPos
     * @param width
     * @param height
     * @param color
     */
    public Bullet(int topLeftXPos, int topLeftYPos, int width, int height, Color color, boolean isAlienBullet) {
        super(topLeftXPos, topLeftYPos, width, height, color);
        this.isAlienBullet = isAlienBullet;
        setUpdateSprite(new UpdateBullet(this));
        setDrawSprite(new DrawSquareSprite(this));
    }

    // This method fires the bullet by reducing the the y position by Delta_y each update
    public void fireBullet(){
        if (isAlienBullet)
            topLeftYPos += DELTA_Y;
        else
            topLeftYPos -= DELTA_Y;
    }

    // This method removes a bullet from the ArrayList of bullets once its hit an enemy or passed the top of the screen
    public void removeBullet(){
        if(getTopLeftYPos() + height < 0 || getTopLeftYPos()  > SpaceInvadersGUI.HEIGHT){
                SpaceInvadersGUI.bullets.remove(this);

        }
    }

    @Override
    public void update(){

        fireBullet();
        removeBullet();
    }

    @Override
    public void draw(Graphics2D g) {

        g.setColor(Color.WHITE);//getColor();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawRect(topLeftXPos, topLeftYPos, width, height);
        g.fillRect(topLeftXPos, topLeftYPos, width, height);

    }


}
