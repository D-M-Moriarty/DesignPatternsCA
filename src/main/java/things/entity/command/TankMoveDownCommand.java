package things.entity.command;

import things.entity.GameComponent;
import things.entity.Tank;
import things.entity.decorator.AbstractBarrel;

public class TankMoveDownCommand implements Command {
    private Tank tank;
    private AbstractBarrel barrel;

    public TankMoveDownCommand(GameComponent tank, GameComponent barrel) {
        this.tank = (Tank) tank;
        this.barrel = (AbstractBarrel) barrel;
    }

    @Override
    public void execute() {
        tank.getMovement().setDown(true);
        barrel.getMovement().setDown(true);
    }

    @Override
    public void unexecute() {
        tank.getMovement().setDown(false);
        barrel.getMovement().setDown(false);
    }
}
