package things.entity.decorator;

import things.entity.Barrel;
import things.entity.GameComponent;

import java.awt.*;

public abstract class TankBarrelDecorator extends AbstractBarrel {

    protected AbstractBarrel barrel;
    private final String packageName =  this.getClass().getPackage().getName();

    TankBarrelDecorator(int topLeftXPos, int topLeftYPos, int width,
                        int height, Color color, int horizontalSpeed, AbstractBarrel barrel) {
        super(topLeftXPos, topLeftYPos, width, height, color, horizontalSpeed);
        this.barrel = barrel;

    }

    public boolean hasDecorator(String className) {
        if (isEquals(className))
            return true;
        else
            return barrel.hasDecorator(className);
    }

    public AbstractBarrel withoutDecorator(String className) {
        if (isEquals(className))
            return barrel.getBarrel();
        else
            return barrel.withoutDecorator(className);
    }

    private boolean isEquals(String className) {
        return this.getClass().getName()
                .equals(packageName + "." + className);
    }

    @Override
    public AbstractBarrel getBarrel() {
        return barrel.getBarrel();
    }

}
