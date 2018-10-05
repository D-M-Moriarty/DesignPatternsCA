package things.entity.decorator;

import things.entity.Barrel;
import things.entity.GameComponent;

import java.awt.*;

public class WideBarrel extends TankBarrelDecorator {

    public WideBarrel(AbstractBarrel abstractBarrel) {
        super(abstractBarrel.getTopLeftXPos(), abstractBarrel.getTopLeftYPos(),
                abstractBarrel.getWidth(), abstractBarrel.getHeight(),
                abstractBarrel.getColor(), abstractBarrel.getHorizontalSpeed());
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(getColor());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawRect(topLeftXPos, topLeftYPos, width + 10, height);
        g.fillRect(topLeftXPos, topLeftYPos, width + 10, height);
    }

    @Override
    public AbstractBarrel getBarrel() {
        return new Barrel(topLeftXPos, topLeftYPos, width, height, color, horizontalSpeed);
    }

}
