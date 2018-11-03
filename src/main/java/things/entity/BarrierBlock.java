package things.entity;

import things.entity.template_method.DestroyableObject;

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
public class BarrierBlock extends DestroyableObject {

    // class attributes are made private so that they can not be directly accessed outside this class



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

    @Override
    protected void performDestroyableObjectCollisionAction(DestroyableObject destroyableObject, Bullet tankBullet) {

    }

    public String toString(){
        return "BarrierBlock class is working";
    }

}
