package things.entity.decorator;

import things.GameComponent;

import java.awt.*;

public abstract class TankBarrelDecorator extends GameComponent {

    /**
     * 5 argument constructor method
     *
     * @param topLeftXPos value passed into the method
     * @param topLeftYPos value passed into the method
     * @param width       value passed into the method
     * @param height      value passed into the method
     * @param color       value passed into the method
     */
    public TankBarrelDecorator(int topLeftXPos, int topLeftYPos, int width, int height, Color color) {
        super(topLeftXPos, topLeftYPos, width, height, color);
    }
    public abstract void draw(Graphics2D g);


    public abstract void update();
}
