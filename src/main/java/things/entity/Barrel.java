package things.entity;

import things.entity.decorator.AbstractBarrel;
import things.entity.decorator.TankBarrelDecorator;

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
public class Barrel extends AbstractBarrel {


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
        super(topLeftXPos, topLeftYPos, width, height, color, horizontalSpeed);
    }

    @Override
    public AbstractBarrel getBarrel() {
        return this;
    }



    public String toString(){
        return "Barrel class is working";
    }


}
