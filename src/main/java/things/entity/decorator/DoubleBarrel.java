package things.entity.decorator;

import things.Barrel;
import things.GameComponent;

import java.awt.*;

public class DoubleBarrel extends TankBarrelDecorator {

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
    public DoubleBarrel(int topLeftXPos, int topLeftYPos, int width,
                        int height, Color color, GameComponent gameComponent) {
        super(topLeftXPos, topLeftYPos, width, height, color);
        this.barrel = (Barrel) gameComponent;
    }

    public void setBarrel() {
        barrel.setTopLeftXPos(barrel.getTopLeftXPos() - 20);
        this.topLeftXPos = barrel.getTopLeftXPos() + 40;
    }

    public void draw(Graphics2D g) {
        g.setColor(getColor());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawRect(topLeftXPos, topLeftYPos, width, height);
        g.fillRect(topLeftXPos, topLeftYPos, width, height);
    }

    public void update() {
        this.topLeftXPos = barrel.getTopLeftXPos() + 40;
        if (barrelIsTooFarRight()) {
            barrel.setTopLeftXPos(1000 - barrel.getWidth() - 100);
        }
        if (barrelIsTooFarLeft()) {
            barrel.setTopLeftXPos(60);
        }
    }

    public void resetBarrel() {
        barrel.setTopLeftXPos(barrel.getTopLeftXPos() + 20);
    }

    private boolean barrelIsTooFarRight() {
        return barrel.getTopLeftXPos() > 1000 - barrel.getWidth() - 100;
    }

    private boolean barrelIsTooFarLeft() {
        return barrel.getTopLeftXPos() < 100;
    }
}
