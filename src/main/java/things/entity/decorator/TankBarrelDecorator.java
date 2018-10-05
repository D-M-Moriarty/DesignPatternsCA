package things.entity.decorator;

import things.entity.Barrel;
import things.entity.GameComponent;

import java.awt.*;

public abstract class TankBarrelDecorator extends AbstractBarrel {

    protected AbstractBarrel barrel;
    private final String packageName =  "things.entity.decorator.";

    public TankBarrelDecorator(int topLeftXPos, int topLeftYPos, int width,
                               int height, Color color, int horizontalSpeed, AbstractBarrel barrel) {
        super(topLeftXPos, topLeftYPos, width, height, color, horizontalSpeed);
        this.barrel = barrel;

    }

    public boolean hasDecorator(String className) {
        if (barrel.getClass().getName()
                .equals(packageName + className))
            return true;
        else
            return barrel.hasDecorator(className);
    }

    public AbstractBarrel withoutDecorator(String className) {
        String name = barrel.getClass().getName();
        if (barrel.getClass().getName()
                .equals(packageName + className)) {
            name = barrel.getClass().getName();
            barrel = barrel.getBarrel();
            name = barrel.getClass().getName();
            return barrel;
        }
        else
            return barrel.withoutDecorator(className);
    }

    @Override
    public AbstractBarrel getBarrel() {
        return barrel.getBarrel();
    }

}
