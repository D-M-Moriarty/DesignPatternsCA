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
    private boolean isAlienBullet;
    /**
     *
     * @param topLeftXPos
     * @param topLeftYPos
     * @param width
     * @param height
     * @param color
     */
    public Bullet(int topLeftXPos, int topLeftYPos, int width, int height, Color color,
                  boolean isAlienBullet) {
        super(topLeftXPos, topLeftYPos, width, height, color);
        this.isAlienBullet = isAlienBullet;
        setUpdateSprite(new UpdateBullet(this));
        setDrawSprite(new DrawSquareSprite(this));
    }


    @Override
    public void update(){
    }

    @Override
    public void draw(Graphics2D g) {
    }

    public boolean isAlienBullet() {
        return isAlienBullet;
    }


}
