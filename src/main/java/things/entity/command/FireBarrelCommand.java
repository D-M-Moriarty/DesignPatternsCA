package things.entity.command;

import things.entity.Barrel;
import things.entity.GameComponent;
import things.entity.Tank;
import things.entity.decorator.AbstractBarrel;

public class FireBarrelCommand implements Command {
    private AbstractBarrel barrel;

    public FireBarrelCommand(GameComponent barrel) {
        this.barrel = (AbstractBarrel) barrel;
    }

    @Override
    public void execute() {
        barrel.setFiring(false);
    }

    @Override
    public void unexecute() {
        barrel.setFiring(true);
    }
}
