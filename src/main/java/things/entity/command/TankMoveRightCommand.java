package things.entity.command;

import things.entity.Barrel;
import things.entity.Tank;
import things.entity.decorator.AbstractBarrel;

public class TankMoveRightCommand implements Command {

    private Tank tank;
    private AbstractBarrel barrel;

    public TankMoveRightCommand(Tank tank, AbstractBarrel barrel) {
        this.tank = tank;
        this.barrel = barrel;
    }

    @Override
    public void execute() {
        tank.setRight(true);
        barrel.setRight(true);
    }

    @Override
    public void unexecute() {
        tank.setRight(false);
        barrel.setRight(false);
    }
}
