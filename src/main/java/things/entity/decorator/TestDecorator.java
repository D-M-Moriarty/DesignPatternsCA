package things.entity.decorator;

import things.entity.GameComponent;

import java.awt.*;

public class TestDecorator extends GameComponent {

    private GameComponent gameComponent;

    /**
     * 5 argument constructor method
     *
     * @param topLeftXPos value passed into the method
     * @param topLeftYPos value passed into the method
     * @param width       value passed into the method
     * @param height      value passed into the method
     * @param color       value passed into the method
     */
    public TestDecorator(int topLeftXPos, int topLeftYPos, int width, int height, Color color) {
        super(topLeftXPos, topLeftYPos, width, height, color);
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void update() {

    }
}
