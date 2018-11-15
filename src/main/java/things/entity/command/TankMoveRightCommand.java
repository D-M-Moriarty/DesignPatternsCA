package things.entity.command;

import things.entity.GameComponent;
import things.entity.Tank;
import things.entity.decorator.AbstractBarrel;

public class TankMoveRightCommand implements Command {

    private Tank tank;
    private AbstractBarrel barrel;

    public TankMoveRightCommand(GameComponent tank, GameComponent barrel) {
        this.tank = (Tank) tank;
        this.barrel = (AbstractBarrel) barrel;
    }

    @Override
    public void execute() {
        tank.getMovement().setRight(true);
        barrel.getMovement().setRight(true);
    }

    @Override
    public void unexecute() {
        tank.getMovement().setRight(false);
        barrel.getMovement().setRight(false);
    }
}
