package things.entity.command;

import things.entity.GameComponent;
import things.entity.Tank;
import things.entity.decorator.AbstractBarrel;

public class TankMoveUpCommand implements Command {
    private Tank tank;
    private AbstractBarrel barrel;

    public TankMoveUpCommand(GameComponent tank, GameComponent barrel) {
        this.tank = (Tank) tank;
        this.barrel = (AbstractBarrel) barrel;
    }

    @Override
    public void execute() {
        tank.getMovement().setUp(true);
        barrel.getMovement().setUp(true);
    }

    @Override
    public void unexecute() {
        tank.getMovement().setUp(false);
        barrel.getMovement().setUp(false);
    }
}
