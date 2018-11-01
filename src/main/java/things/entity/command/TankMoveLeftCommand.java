package things.entity.command;

import things.entity.Barrel;
import things.entity.GameComponent;
import things.entity.Tank;
import things.entity.decorator.AbstractBarrel;

public class TankMoveLeftCommand implements Command {
    private Tank tank;
    private AbstractBarrel barrel;

    public TankMoveLeftCommand(GameComponent tank, GameComponent barrel) {
        this.tank = (Tank) tank;
        this.barrel = (AbstractBarrel) barrel;
    }

    @Override
    public void execute() {
        tank.setLeft(true);
        barrel.setLeft(true);
    }

    @Override
    public void unexecute() {
        tank.setLeft(false);
        barrel.setLeft(false);
    }
}
