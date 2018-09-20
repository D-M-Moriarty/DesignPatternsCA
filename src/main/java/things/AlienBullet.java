package things;

import things.entity.draw.DrawSquareSprite;
import things.entity.update.UpdateAlienBullet;

import java.awt.*;

/**
 * Created by Darren Moriarty on 21/11/2016.
 */
public class AlienBullet extends GameComponent {


    // Class Attributes

    // The initial Y coordinate of the entity
    private int originalTopLeftYPos;

    /* The difference between the initial y position and the new y position
        This is the amount of pixels the entity will move per second/update */
    private final int DELTA_Y = 5;

    /**
     *
     * @param topLeftXPos
     * @param topLeftYPos
     * @param width
     * @param height
     * @param color
     */
    public AlienBullet(int topLeftXPos, int topLeftYPos, int width, int height, Color color) {
        super(topLeftXPos, topLeftYPos, width, height, color);
        setUpdateSprite(new UpdateAlienBullet(this));
        setDrawSprite(new DrawSquareSprite(this));
    }

    // This method fires the bullet by reducing the the y position by Delta_y each update
    public void fireBullet(){
        topLeftYPos += DELTA_Y;
    }

    // This method removes a bullet from the ArrayList of bullets once its hit an enemy or passed the top of the screen
    public void removeBullet(){
        if(getTopLeftYPos() + height < 0 || getTopLeftYPos()  > SpaceInvadersGUI.HEIGHT){
            SpaceInvadersGUI.alienBullets.remove(this);
        }
    }

    @Override
    public void update(){

        fireBullet();
        removeBullet();

    }

    @Override
    public void draw(Graphics2D g) {

        g.setColor(Color.RED);//getColor();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawRect(topLeftXPos, topLeftYPos, width, height);
        g.fillRect(topLeftXPos, topLeftYPos, width, height);

    }

}
