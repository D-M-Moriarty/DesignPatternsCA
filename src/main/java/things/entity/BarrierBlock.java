package things.entity;

import java.awt.*;

/**
 * This is an instantiable class called BarrierBlock for creating individual barrier blocks entities.
 * It is a sub-class of GameComponent therefore it inherits all of its
 * attributes and abstract methods
 *
 * @author Darren Moriarty
 * created on 11/11/2016.
 *
 * @version 2.0
 */
public class BarrierBlock extends GameComponent {

    // class attributes are made private so that they can not be directly accessed outside this class

    // Boolean attribute to signify whether this entity is destroyed or not
    private boolean destroyed;

    /**
     * 6 argument constructor method
     *
     * @param topLeftXPos
     * @param topLeftYPos
     * @param width
     * @param height
     * @param color
     * @param destroyed The initial value of destroyed
     */
    public BarrierBlock(int topLeftXPos, int topLeftYPos, int width, int height, Color color, boolean destroyed) {
        super(topLeftXPos, topLeftYPos, width, height, color);
        setDestroyed(destroyed);
    }

    /**
     * This sets the value of destroyed
     * @param destroyed
     */
    public void setDestroyed(boolean destroyed){
        this.destroyed = destroyed;
    }

    /**
     * This returns the value of destroyed
     * @return
     */
    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public void draw(Graphics2D g) {

        g.setColor(color);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawRect(topLeftXPos, topLeftYPos, width, height);
        g.fillRect(topLeftXPos, topLeftYPos, width, height);

    }

    @Override
    public void update() {

    }

    public String toString(){
        return "BarrierBlock class is working";
    }

}
